package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.Exit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    @Test
    void testGetInstance()
    {
        Exit myExit = Exit.getInstance(entityFactory);

        assertEquals(myExit.getType(), "exit");
    }

    @Test
    void testCheckFinishGameTrue()
    {
        Exit myExit = Exit.getInstance(entityFactory);
        Warrior myWarrior = new Warrior(entityFactory);

        for (int i = 0; i < 4; i++)
        {
            myWarrior.incrementPillars();
        }

        assertTrue(myExit.checkFinishGame());
    }

    @Test
    void testCheckFinishGameFalse()
    {
        Exit myExit = Exit.getInstance(entityFactory);
        Warrior myWarrior = new Warrior(entityFactory); //initialized with 0 Pillars.

        assertFalse(myExit.checkFinishGame());
    }

    @Test
    void testActivateSetsFalse()
    {
        Exit myExit = Exit.getInstance(entityFactory);

        Warrior myWarrior = new Warrior(entityFactory);

        for (int i = 0; i < 4; i++)
        {
            myWarrior.incrementPillars();
        }
        System.out.println(myWarrior.getPillars());
        myExit.activate(myWarrior);

        System.out.println(myWarrior.getActiveStatus());

        assertFalse(myWarrior.getActiveStatus());
    }

    @Test
    void testActivateNotFalse()
    {
        Exit myExit = Exit.getInstance(entityFactory);

        Warrior myWarrior = new Warrior(entityFactory);

        myExit.activate(myWarrior);

        assertTrue(myWarrior.getActiveStatus());
    }

}