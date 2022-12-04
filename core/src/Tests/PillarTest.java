package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarTest
{
    @Test
    void testPillarConstructor()
    {
        Pillar myPillar = new Pillar("Inheritance", new Vec2(), new EntityFactory());

        assertTrue(myPillar.getType().equals("Inheritance"));
    }

    @Test
    void testGetMyName()
    {
        Pillar myPillar = new Pillar("Abstraction", new Vec2(), new EntityFactory());

        assertTrue(myPillar.getMyName().equals("Abstraction"));
    }

    @Test
    void testSetMyName()
    {
        Pillar myPillar = new Pillar("Encapsulation", new Vec2(), new EntityFactory());

        myPillar.setMyName("Polymorphism");

        assertTrue(myPillar.getMyName().equals("Polymorphism"));
    }

    @Test
    void testItemBehavior()
    {

    }

    @Test
    void testUpdate()
    {
        //write tests for update
    }
}