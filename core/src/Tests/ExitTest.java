package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.Physics.Vec2;
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
    void testExitCondition()
    {
        Exit.setExitCondition(false);
        assertFalse(Exit.isExitCondition());
    }

    @Test
    void testSetExitCondition()
    {
        assertFalse(Exit.isExitCondition());

        Exit.setExitCondition(true);

        assertTrue(Exit.isExitCondition());
    }

    @Test
    void testIsExited()
    {
        assertFalse(Exit.isExited());
    }

    @Test
    void testActivateDestroysHero()
    {
        Warrior myWarrior = new Warrior(entityFactory);

        Exit.setExitCondition(true);
        myWarrior.setPillars(4);

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
        Exit.setExitCondition(false);

        myWarrior.setMyPos(new Vec2(25,25));
        myWarrior.setHitPoints(2);
        myWarrior.setDamage(5);
        myWarrior.setMaxSpeed(3);

        newExit.activate(myWarrior);

        assertFalse(Exit.isExitCondition());

        assertFalse(myWarrior.getMyPos().equals(myWarrior.getHomePosition()));
        assertNotEquals(myWarrior.getHitPoints(), 10);
        assertNotEquals(myWarrior.getDamage(), 1);
        assertNotEquals(myWarrior.getMaxSpeed(), 5);
    }

    @Test
    void testClean()
    {
        Exit.setExitCondition(true);
        Exit.clean();
        assertFalse(Exit.isExitCondition());
        assertFalse(Exit.isExited());
    }
}