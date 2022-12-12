package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.DungeonItems.Items.Lava;
import MVC.Model.DungeonItems.Room;
import MVC.View.Assets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EntityFactoryTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

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
        expectedList.add(new Lava(entityFactory));
        expectedList.add(new HealingPotion(entityFactory));
        expectedList.add(new AttackPotion(entityFactory));

        expectedList.add(entityFactory.generateOgre());
        expectedList.add(entityFactory.generateRats());
        expectedList.add(entityFactory.generateKnight());
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
        assertTrue(roomEntities.get(5).getType().contentEquals("Knight"));
        assertTrue(roomEntities.get(6).getType().contentEquals("Swarm of Rats"));
    }

    @Test
    void testGenerateOgre()
    {
        assertEquals("ogre",entityFactory.generateOgre().getMonsterType());
    }
    @Test
    void testGenerateGremlin()
    {
        assertEquals("gremlin",entityFactory.generateGremlin().getMonsterType());
    }
    @Test
    void testGenerateKnight()
    {
        assertEquals("knight",entityFactory.generateKnight().getMonsterType());
    }
    @Test
    void testGenerateRats()
    {
        assertEquals("rat",entityFactory.generateRats().getMonsterType());
    }
    @Test
    void testGenerateMonsters()
    {
        assertTrue(entityFactory.generateMonsters("rat").getMonsterType().contentEquals("rat"));
        assertTrue(entityFactory.generateMonsters("ogre").getMonsterType().contentEquals("ogre"));
        assertTrue(entityFactory.generateMonsters("knight").getMonsterType().contentEquals("knight"));
        assertTrue(entityFactory.generateMonsters("gremlin").getMonsterType().contentEquals("gremlin"));
    }

    @Test
    void testAddItem()
    {
        assertEquals("speedPotion",entityFactory.generateItems("speedPotion").getType());
    }

    @Test
    void testGenerateLava()
    {
        assertEquals("lava",entityFactory.generateLava().getType());
    }

    @Test
    void testGenerateHero()
    {
        assertEquals("Warrior",entityFactory.generateHero("Warrior").getType());
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
        assertEquals("Sword",entityFactory.generateSword().getType());
    }

}
