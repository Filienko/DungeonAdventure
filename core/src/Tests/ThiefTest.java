package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
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
        final Thief myThief = new Thief();

        assertEquals("Thief", myThief.getName());
        assertEquals("Thief", myThief.getMyCharacterType());
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
        final Thief myThief = new Thief("T", new Vec2());

        assertEquals("T", myThief.getName());
        assertEquals("Thief", myThief.getMyCharacterType());
        assertTrue(myThief.getHeroStatus());
        assertEquals(10, myThief.getHitPoints());
        assertEquals(5, myThief.getMaxSpeed());
        assertEquals(1, myThief.getDamage());
    }

    /**
     * Test method for {@link Thief#surpriseAttack(DungeonCharacter)}
     */
    @Test
    void testSurpriseAttack()
    {
        //finish surpriseAttack() method and then write these tests
    }

    /**
     * Test method for {@link Thief#getHiddenStatus()}
     */
    @Test
    void testGetHiddenStatus()
    {
        final Thief myThief = new Thief();

        assertFalse(myThief.getHiddenStatus());
    }

    /**
     * Test method for {@link Thief#setHiddenStatus(boolean)}
     */
    @Test
    void testSetMyHiddenStatus()
    {
        final Thief myThief = new Thief();

        assertFalse(myThief.getHiddenStatus());

        myThief.setHiddenStatus(true);

        assertTrue(myThief.getHiddenStatus());

    }
}