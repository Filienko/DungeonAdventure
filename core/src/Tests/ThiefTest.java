package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    /**
     * Test method for Thief's default constructor.
     */
    @Test
    void testThiefConstructor()
    {
        final Thief myThief = new Thief(entityFactory);

        assertEquals("Thief", myThief.getName());
        assertEquals("Thief", myThief.getCharacterType());
        assertTrue(myThief.getHeroStatus());
        assertEquals(10, myThief.getHitPoints());
        assertEquals(5, myThief.getMaxSpeed());
        assertEquals(1, myThief.getDamage());
    }

    /**
     * Test method for Thief's overloaded constructor.
     */
    @Test
    void testThiefOLConstructor()
    {
        final Thief myThief = new Thief("T", new Vec2(), entityFactory);

        assertEquals("T", myThief.getName());
        assertEquals("Thief", myThief.getCharacterType());
        assertTrue(myThief.getHeroStatus());
        assertEquals(10, myThief.getHitPoints());
        assertEquals(5, myThief.getMaxSpeed());
        assertEquals(1, myThief.getDamage());
    }

    /**
     * Test method for {@link Thief#damage()}
     */
    @Test
    void testDamage()
    {
        final Thief myThief = new Thief(entityFactory);

        assertTrue(myThief.damage() == 1 || myThief.damage() == 6);

    }

    /**
     * Test method for {@link Thief#special()}
     */
    @Test
    void testSpecial()
    {
        final Thief myThief = new Thief(entityFactory);

        assertEquals(myThief.getDamage() + 5, myThief.special());
    }
}