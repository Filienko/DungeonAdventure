package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

    private final Exit myExit = Exit.getInstance(entityFactory);
    @Test
    void testGetInstance()
    {
        assertEquals(myExit.getType(), "exit");
    }

    @Test
    void testCheckFinishGameTrue()
    {
        assertTrue(myExit.checkFinishGame()); //no worm has been added to entityFactory
    }

    @Test
    void testCheckFinishGameFalse()
    {
        myExit.getMyEntityFactory().addEntity(new Worm(new Vec2(), entityFactory));

        myExit.getMyEntityFactory().update();

        System.out.println(myExit.getMyEntityFactory().getEntities());

        assertFalse(myExit.checkFinishGame());
    }

    @Test
    void testActivateDestroysHero()
    {
        Warrior myWarrior = new Warrior(entityFactory);

        for (int i = 0; i < 4; i++)
        {
            myWarrior.incrementPillars();
        }

        myExit.activate(myWarrior);

        assertTrue(Exit.isExited());

        assertTrue(myWarrior.getMyPos().equals(myWarrior.getHomePosition()));
        assertEquals(myWarrior.getHitPoints(), 10);
        assertEquals(myWarrior.getDamage(), 1);
        assertEquals(myWarrior.getMaxSpeed(), 5);
    }

    @Test
    void testActivateHeroLives()
    {
        Exit newExit = Exit.getInstance(entityFactory);

        Warrior myWarrior = new Warrior(entityFactory);

        myWarrior.setMyPos(new Vec2(25,25));
        myWarrior.setHitPoints(2);
        myWarrior.setDamage(5);
        myWarrior.setMaxSpeed(3);

        newExit.getMyEntityFactory().addEntity(new Worm(new Vec2(), entityFactory));

        newExit.getMyEntityFactory().update();

        assertFalse(newExit.checkFinishGame());

        newExit.activate(myWarrior);

        assertFalse(myWarrior.getMyPos().equals(myWarrior.getHomePosition()));
        assertNotEquals(myWarrior.getHitPoints(), 10);
        assertNotEquals(myWarrior.getDamage(), 1);
        assertNotEquals(myWarrior.getMaxSpeed(), 5);
    }

    @Test
    void testIsExited()
    {
        assertFalse(Exit.isExited());
    }

}