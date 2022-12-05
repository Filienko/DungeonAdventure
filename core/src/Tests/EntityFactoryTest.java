package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.DungeonItems.Items.Pit;
import MVC.Model.DungeonItems.Room;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EntityFactoryTest
{
    EntityFactory entityFactory = new EntityFactory();

    @Test
    void testUpdate()
    {
        var room = new Room();
        room.populateTheRoom(true);
        entityFactory.generateRoomEntities(room);
        var priorLength = entityFactory.getEntities().size();
        entityFactory.update();
        assertNotEquals(priorLength, entityFactory.getEntities().size());
    }

    @Test
    void testGenerateGameEntities()
    {
        assertTrue(128>entityFactory.generateGameEntities(new Dungeon()).size());
    }

    @Test
    void testGenerateRoomEntities()
    {
        var expectedList = new ArrayList<Entity>();
        expectedList.add(new Pit(new EntityFactory())); //added new EntityFactory()
        expectedList.add(new HealingPotion(new EntityFactory()));
        expectedList.add(new AttackPotion(new EntityFactory()));

        expectedList.add(entityFactory.generateOgre());
        expectedList.add(entityFactory.generateRats());
        expectedList.add(entityFactory.generateElf());
        expectedList.add(entityFactory.generateGremlin());

        var room = new Room();
        room.populateTheRoom(true);
        var roomEntities = entityFactory.generateRoomEntities(room);
        assertEquals(expectedList.size(), roomEntities.size());

        assertTrue(roomEntities.get(0).getType().contains("Potion"));
        assertTrue(roomEntities.get(1).getType().contains("Potion"));
        assertTrue(roomEntities.get(2).getType().contains("Potion"));
        assertTrue(roomEntities.get(3).getType().contentEquals("Ogre"));
        assertTrue(roomEntities.get(4).getType().contentEquals("Gremlin"));
        assertTrue(roomEntities.get(5).getType().contentEquals("Elf"));
        assertTrue(roomEntities.get(6).getType().contentEquals("Swarm of Rats"));
    }

    @Test
    void testGenerateOgre()
    {
        assertEquals("Ogre",entityFactory.generateOgre());
    }
    @Test
    void testGenerateGremlin()
    {
        assertEquals("Gremlin",entityFactory.generateGremlin());
    }
    @Test
    void testGenerateElf()
    {
        assertEquals("Elf",entityFactory.generateElf());
    }
    @Test
    void testGenerateRats()
    {
        assertEquals("Swarm of Rats",entityFactory.generateRats());
    }
    @Test
    void testGenerateMonsters()
    {
        var monsters = entityFactory.generateMonsters(1);
        assertEquals("Ogre",monsters.get(0).getType());
        assertEquals("Gremlin",monsters.get(1).getType());
        assertEquals("Elf",monsters.get(2).getType());
        assertEquals("Swarm of Rats",monsters.get(3).getType());
    }

    @Test
    void testAddItem()
    {
        assertEquals("Pit",entityFactory.generateItem("Pit").getType());
    }

    @Test
    void testGeneratePit()
    {
        assertEquals("Pit",entityFactory.generatePit().getType());
    }

    @Test
    void testGenerateHero()
    {
        assertEquals("Thief",entityFactory.generateHero("Warrior").getType());
        assertEquals("Thief",entityFactory.generateHero("Thief").getType());
        assertEquals("Thief",entityFactory.generateHero("Priestess").getType());
    }

    @Test
    void testGenerateThief()
    {
        assertEquals("Thief",entityFactory.generateThief().getType());
    }

    @Test
    void testGenerateWarrior()
    {
        assertEquals("Warrior",entityFactory.generateWarrior().getType());
    }
    @Test
    void testGeneratePriestess()
    {
        assertEquals("Priestess",entityFactory.generatePriestess().getType());
    }
    @Test
    void testGeneratePillars()
    {
        var pillars = entityFactory.generatePillars();
        assertEquals("Encapsulation",pillars.get(0).getType());
        assertEquals("Inheritance",pillars.get(1).getType());
        assertEquals("Abstraction",pillars.get(2).getType());
        assertEquals("Polymorphism",pillars.get(3).getType());
    }
    @Test
    void testGenerateSword()
    {
        //assertEquals("Sword",entityFactory.generateSword(new Thief()).getType());
        assertEquals("Sword",entityFactory.generateSword());
    }

}
