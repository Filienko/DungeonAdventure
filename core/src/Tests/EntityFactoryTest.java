package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

import MVC.Model.DungeonItems.Items.SpeedPotion;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityFactoryTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

    /**
     * Test method for {@link EntityFactory#generateOgre()}
     */
    @Test
    void testGenerateOgre()
    {
        assertEquals("ogre",entityFactory.generateOgre().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateGremlin()}
     */
    @Test
    void testGenerateGremlin()
    {
        assertEquals("gremlin",entityFactory.generateGremlin().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateKnight()}
     */
    @Test
    void testGenerateKnight()
    {
        assertEquals("knight",entityFactory.generateKnight().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateRats()}
     */
    @Test
    void testGenerateRats()
    {
        assertEquals("rat",entityFactory.generateRats().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateMonsters(String)}
     */
    @Test
    void testGenerateMonsters()
    {
        assertTrue(entityFactory.generateMonsters("rat").getMonsterType().contentEquals("rat"));
        assertTrue(entityFactory.generateMonsters("ogre").getMonsterType().contentEquals("ogre"));
        assertTrue(entityFactory.generateMonsters("knight").getMonsterType().contentEquals("knight"));
        assertTrue(entityFactory.generateMonsters("gremlin").getMonsterType().contentEquals("gremlin"));
    }

    /**
     * Test method for {@link EntityFactory#generateItems(String)}
     */
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

    /**
     * Test method for {@link EntityFactory#addEntity(Entity)}
     */
    @Test
    void testAddEntity()
    {
        assertEquals("speedPotion",entityFactory.generateItems("speedPotion").getType());
    }

    /**
     * Test method for {@link EntityFactory#generateLava()}
     */
    @Test
    void testGenerateLava()
    {
        assertEquals("lava",entityFactory.generateLava().getType());
    }

    /**
     * Test method for {@link EntityFactory#generateHero(String)}
     */
    @Test
    void testGenerateHero()
    {
        assertEquals("Hero",entityFactory.generateHero("Mock").getType());
    }

    /**
     * Test method for {@link EntityFactory#generateMockHero()}
     */
    @Test
    void testGenerateMockHero()
    {
        assertEquals("Hero",entityFactory.generateMockHero().getType());
    }

    /**
     * Test method for {@link EntityFactory#generatePillars()}
     */
    @Test
    void testGeneratePillars()
    {
        var pillars = entityFactory.generatePillars();
        assertEquals("Encapsulation",pillars.get(0).getType());
        assertEquals("Inheritance",pillars.get(1).getType());
        assertEquals("Abstraction",pillars.get(2).getType());
        assertEquals("Polymorphism",pillars.get(3).getType());
    }

    /**
     * Test method for {@link EntityFactory#generateSword()}
     */
    @Test
    void testGenerateSword()
    {
        assertEquals("Sword",entityFactory.generateSword().getType());
    }
}
