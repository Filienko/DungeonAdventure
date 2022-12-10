package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest
{

    /**
     * Test method for Thief's default constructor.
     */
    @Test
    void testThiefConstructor()
    {
        final Thief myThief = new Thief(new EntityFactory());

        assertEquals("Thief", myThief.getName());
        assertEquals("Thief", myThief.getCharacterType());
        assertTrue(myThief.getHeroStatus());
        assertEquals(10, myThief.getHitPoints());
        assertEquals(5, myThief.getMaxSpeed());
        assertEquals(1, myThief.getDamage());
        assertFalse(myThief.getHiddenStatus());
    }

    /**
     * Test method for Thief's overloaded constructor.
     */
    @Test
    void testThiefOLConstructor()
    {
        final Thief myThief = new Thief("T", new Vec2(), new EntityFactory());

        assertEquals("T", myThief.getName());
        assertEquals("Thief", myThief.getCharacterType());
        assertTrue(myThief.getHeroStatus());
        assertEquals(10, myThief.getHitPoints());
        assertEquals(5, myThief.getMaxSpeed());
        assertEquals(1, myThief.getDamage());
    }

    /**
     * Test method for {@link Thief#attack()}
     */
    @Test
    void testAttack()
    {
        final Thief myThief = new Thief(new EntityFactory());

        assertTrue(myThief.attack() == 1);

    }

    /**
     * Test method for {@link Thief#special()}
     */
    @Test
    void testSpecial()
    {
        final Thief myThief = new Thief(new EntityFactory());

        assertTrue(myThief.getDamage() + 5 == myThief.special());
    }

    /**
     * Test method for {@link Thief#getHiddenStatus()}
     */
    @Test
    void testGetHiddenStatus()
    {
        final Thief myThief = new Thief(new EntityFactory());

        assertFalse(myThief.getHiddenStatus());
    }

    /**
     * Test method for {@link Thief#setHiddenStatus(boolean)}
     */
    @Test
    void testSetMyHiddenStatus()
    {
        final Thief myThief = new Thief(new EntityFactory());

        assertFalse(myThief.getHiddenStatus());

        myThief.setHiddenStatus(true);

        assertTrue(myThief.getHiddenStatus());

    }
}