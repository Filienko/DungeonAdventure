package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarTest
{
    private final EntityFactory theEntityFactory = new EntityFactory(null, "Mock");
    @Test
    void testPillarConstructor()
    {
        Pillar myPillar = new Pillar("Inheritance", theEntityFactory);

        assertTrue(myPillar.getName().equals("Inheritance"));
    }

    @Test
    void testGetName()
    {
        Pillar myPillar = new Pillar("Abstraction", theEntityFactory);

        assertTrue(myPillar.getName().equals("Abstraction"));
    }

    @Test
    void testActivate()
    {
        Pillar myPillar = new Pillar("Encapsulation", theEntityFactory);
        Hero mock = theEntityFactory.generateMockHero();
        mock.setHitPoints(5);

        myPillar.activate(mock);
        assertEquals(mock.getHitPoints(), 6);
    }

}