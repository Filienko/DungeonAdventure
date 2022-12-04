package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
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
        Exit myExit = Exit.getInstance(new Vec2(10,10), new EntityFactory());

        //assertInstanceOf(Exit, myExit);
        assertNotNull(myExit);
    }

    @Test
    void testCheckFinishGameTrue()
    {
        Exit myExit = Exit.getInstance(new Vec2(10,10), new EntityFactory());
        Warrior myWarrior = new Warrior(new EntityFactory());

        myWarrior.setPillars(Arrays.asList(new Pillar("Abstraction", new Vec2(), new EntityFactory()),
                new Pillar("Encapsulation", new Vec2(), new EntityFactory()),
                new Pillar("Inheritance", new Vec2(), new EntityFactory()),
                new Pillar("Polymorphism", new Vec2(), new EntityFactory())));

        assertTrue(myExit.checkFinishGame(myWarrior));
    }

    @Test
    void testCheckFinishGameFalse()
    {
        Exit myExit = Exit.getInstance(new Vec2(10,10), new EntityFactory());
        Warrior myWarrior = new Warrior(new EntityFactory()); //initialized with 0 Pillars.

        assertFalse(myExit.checkFinishGame(myWarrior));
    }
}