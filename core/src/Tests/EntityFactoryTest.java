package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityFactoryTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

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
    void testGenerateItems()
    {
        assertTrue(entityFactory.generateItems("healthPotion").getType().contentEquals("healthPotion"));
        assertTrue(entityFactory.generateItems("attackPotion").getType().contentEquals("attackPotion"));
        assertTrue(entityFactory.generateItems("speedPotion").getType().contentEquals("speedPotion"));
        assertTrue(entityFactory.generateItems("lava").getType().contentEquals("lava"));
        assertTrue(entityFactory.generateItems("pillar").getType().contentEquals("pillar"));
        assertTrue(entityFactory.generateItems("exit").getType().contentEquals("exit"));
        assertTrue(entityFactory.generateItems("bomb").getType().contentEquals("bomb"));
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
        assertEquals("Hero",entityFactory.generateHero("Mock").getType());
    }

    @Test
    void testGenerateMockHero()
    {
        assertEquals("Hero",entityFactory.generateMockHero().getType());
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
