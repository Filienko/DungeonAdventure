package MVC.Model.UnitTests;

import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarTest {

    @Test
    void testPillarConstructor()
    {
        Pillar myPillar = new Pillar("Inheritance", new Vec2());

        assertTrue(myPillar.getType().equals("Inheritance"));
    }

    @Test
    void testGetMyName()
    {
        Pillar myPillar = new Pillar("Abstraction", new Vec2());

        assertTrue(myPillar.getMyName().equals("Abstraction"));
    }

    @Test
    void testSetMyName()
    {
        Pillar myPillar = new Pillar("Encapsulation", new Vec2());

        myPillar.setMyName("Polymorphism");

        assertTrue(myPillar.getMyName().equals("Polymorphism"));
    }
}