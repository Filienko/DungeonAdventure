package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest
{
//    @Test
//    void testExitConstructor()
//    {
//        Exit myExit = new Exit(new Vec2(10,10));
//
//        assertTrue(myExit.getType().equals("Exit"));
//        assertTrue(myExit.getMyPos().equals(new Vec2(10,10)));
//    }

    @Test
    void testGetInstance()
    {
        Exit myExit = Exit.getInstance(new Vec2(10,10));

        //assertInstanceOf(Exit, myExit);
        assertNotNull(myExit);
    }

    @Test
    void testCheckFinishGameTrue()
    {
        Exit myExit = Exit.getInstance(new Vec2(10,10));
        Warrior myWarrior = new Warrior();

        myWarrior.setPillars(Arrays.asList(new Pillar("Abstraction", new Vec2()),
                new Pillar("Encapsulation", new Vec2()), new Pillar("Inheritance", new Vec2()),
                new Pillar("Polymorphism", new Vec2())));

        assertTrue(myExit.checkFinishGame(myWarrior));
    }

    @Test
    void testCheckFinishGameFalse()
    {
        Exit myExit = Exit.getInstance(new Vec2(10,10));
        Warrior myWarrior = new Warrior(); //initialized with 0 Pillars.

        assertFalse(myExit.checkFinishGame(myWarrior));
    }
}