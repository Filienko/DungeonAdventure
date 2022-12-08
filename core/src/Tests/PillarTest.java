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
        Pillar myPillar = new Pillar("Inheritance", new EntityFactory());

        assertTrue(myPillar.getName().equals("Inheritance"));
    }

    @Test
    void testGetName()
    {
        Pillar myPillar = new Pillar("Abstraction", new EntityFactory());

        assertTrue(myPillar.getName().equals("Abstraction"));
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