package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityFactoryTest
{
    private final EntityFactory myEntityFactory = new EntityFactory(null, "Mock");

    /**
     * Test method for {@link EntityFactory#generateOgre()}
     */
    @Test
    void testGenerateOgre()
    {
        assertEquals("ogre", myEntityFactory.generateOgre().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateGremlin()}
     */
    @Test
    void testGenerateGremlin()
    {
        assertEquals("gremlin", myEntityFactory.generateGremlin().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateKnight()}
     */
    @Test
    void testGenerateKnight()
    {
        assertEquals("knight", myEntityFactory.generateKnight().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateRats()}
     */
    @Test
    void testGenerateRats()
    {
        assertEquals("rat", myEntityFactory.generateRats().getMonsterType());
    }

    /**
     * Test method for {@link EntityFactory#generateMonsters(String)}
     */
    @Test
    void testGenerateMonsters()
    {
        assertTrue(myEntityFactory.generateMonsters("rat").getMonsterType().contentEquals("rat"));
        assertTrue(myEntityFactory.generateMonsters("ogre").getMonsterType().contentEquals("ogre"));
        assertTrue(myEntityFactory.generateMonsters("knight").getMonsterType().contentEquals("knight"));
        assertTrue(myEntityFactory.generateMonsters("gremlin").getMonsterType().contentEquals("gremlin"));
    }

    /**
     * Test method for {@link EntityFactory#generateItems(String)}
     */
    @Test
    void testGenerateItems()
    {
        assertTrue(myEntityFactory.generateItems("healthPotion").getType().contentEquals("healthPotion"));
        assertTrue(myEntityFactory.generateItems("attackPotion").getType().contentEquals("attackPotion"));
        assertTrue(myEntityFactory.generateItems("speedPotion").getType().contentEquals("speedPotion"));
        assertTrue(myEntityFactory.generateItems("lava").getType().contentEquals("lava"));
        assertTrue(myEntityFactory.generateItems("pillar").getType().contentEquals("pillar"));
        assertTrue(myEntityFactory.generateItems("exit").getType().contentEquals("exit"));
        assertTrue(myEntityFactory.generateItems("bomb").getType().contentEquals("bomb"));
    }

    /**
     * Test method for {@link EntityFactory#addEntity(Entity)}
     */
    @Test
    void testAddEntity()
    {
        assertEquals("speedPotion", myEntityFactory.generateItems("speedPotion").getType());
    }

    /**
     * Test method for {@link EntityFactory#generateLava()}
     */
    @Test
    void testGenerateLava()
    {
        assertEquals("lava", myEntityFactory.generateLava().getType());
    }

    /**
     * Test method for generateWall(Vec2, int, int, int) ()}
     * Cannot test, because of the LibGDX file system
     */
    @Test
    void testGenerateWall()
    {
        //myEntityFactory.generateWall(new Vec2(), 9, 5, 0);
        assertEquals(1, myEntityFactory.getEntities().size());
    }

    /**
     * Test method for {@link EntityFactory#generateHero(String)}
     */
    @Test
    void testGenerateHero()
    {
        assertEquals("Hero", myEntityFactory.generateHero("Mock").getType());
    }

    /**
     * Test method for {@link EntityFactory#generateMockHero()}
     */
    @Test
    void testGenerateMockHero()
    {
        assertEquals("Hero", myEntityFactory.generateMockHero().getType());
    }

    /**
     * Test method for {@link EntityFactory#generatePillars()}
     */
    @Test
    void testGeneratePillars()
    {
        var pillars = myEntityFactory.generatePillars();
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
        assertEquals("Sword", myEntityFactory.generateSword().getType());
    }
}
