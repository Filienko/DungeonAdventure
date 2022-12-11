package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.Exit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest
{
    @Test
    void testGetInstance()
    {
        Exit myExit = Exit.getInstance(new EntityFactory());

        assertEquals(myExit.getType(), "Exit");
    }

    @Test
    void testCheckFinishGameTrue()
    {
        Exit myExit = Exit.getInstance( new EntityFactory());
        Warrior myWarrior = new Warrior(new EntityFactory());

        for (int i = 0; i < 4; i++)
        {
            myWarrior.incrementPillars();
        }

        assertTrue(myExit.checkFinishGame(myWarrior));
    }

    @Test
    void testCheckFinishGameFalse()
    {
        Exit myExit = Exit.getInstance(new EntityFactory());
        Warrior myWarrior = new Warrior(new EntityFactory()); //initialized with 0 Pillars.

        assertFalse(myExit.checkFinishGame(myWarrior));
    }

    @Test
    void testActivateSetsFalse()
    {
        Exit myExit = Exit.getInstance(new EntityFactory());

        Warrior myWarrior = new Warrior(new EntityFactory());

        for (int i = 0; i < 4; i++)
        {
            myWarrior.incrementPillars();
        }

        myExit.activate(myWarrior);

        assertFalse(myWarrior.getActiveStatus());
    }

    @Test
    void testActivateNotFalse()
    {
        Exit myExit = Exit.getInstance(new EntityFactory());

        Warrior myWarrior = new Warrior(new EntityFactory());

        myExit.activate(myWarrior);

        assertTrue(myWarrior.getActiveStatus());
    }

}