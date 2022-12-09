package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarTest
{
    @Test
    void testPillarConstructor()
    {
        Pillar myPillar = new Pillar("Inheritance", new EntityFactory());

        assertTrue(myPillar.getName().equals("Inheritance"));
    }

    //write another method for 2nd constructor?

    @Test
    void testGetName()
    {
        Pillar myPillar = new Pillar("Abstraction", new EntityFactory());

        assertTrue(myPillar.getName().equals("Abstraction"));
    }

    @Test
    void testActivate()
    {
        Pillar myPillar = new Pillar("Encapsulation", new EntityFactory());
        Thief t1 = new Thief(new EntityFactory());

        myPillar.activate(t1);

        assertEquals(t1.getPillars(), 1);

        assertFalse(myPillar.getActiveStatus());
    }

}