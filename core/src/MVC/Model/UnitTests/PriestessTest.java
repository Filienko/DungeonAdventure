package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriestessTest
{

    /**
     * Test method for Priestess's default constructor.
     */
    @Test
    void testPriestessConstructor()
    {
        final Priestess myPriestess = new Priestess();

        assertEquals("Priestess", myPriestess.getName());
        assertEquals("Priestess", myPriestess.getMyCharacterType());
        assertTrue(myPriestess.getHeroStatus());
        assertEquals(75, myPriestess.getHitPoints());
        assertEquals(5, myPriestess.getMaxSpeed());
        assertEquals(25, myPriestess.getMinDamageRange());
        assertEquals(45, myPriestess.getMaxDamageRange());

    }

    /**
     * Test method for Priestess's overloaded constructor.
     */
    @Test
    void testPriestessOLConstructor()
    {
        final Priestess myPriestess = new Priestess("P", new Vec2());

        assertEquals("P", myPriestess.getName());
        assertEquals("Priestess", myPriestess.getMyCharacterType());
        assertTrue(myPriestess.getHeroStatus());
        assertEquals(75, myPriestess.getHitPoints());
        assertEquals(5, myPriestess.getMaxSpeed());
        assertEquals(25, myPriestess.getMinDamageRange());
        assertEquals(45, myPriestess.getMaxDamageRange());
    }

    /**
     * Test method for {@link Priestess#healHero(Hero)}.
     */
    @Test
    void testHealHero()
    {
        final Priestess myPriestess1 = new Priestess();
        final Priestess myPriestess2 = new Priestess();

        int oldHealth = myPriestess1.getHitPoints();
        int restoredHealth = myPriestess1.healHero(myPriestess2);

        assertTrue(myPriestess2.getHitPoints() > oldHealth);
        assertEquals(myPriestess2.getHitPoints(), restoredHealth + oldHealth);

        assertTrue(restoredHealth + oldHealth <= 75);
        //this ensures that healHero does not heal over Priestess's maximum allowed hit points (75)
    }

    /**
     * Test method for {@link Priestess#toString()}
     */
    @Test
    void testToString()
    {
        final Priestess myPriestess = new Priestess("P", new Vec2());

        assertEquals("Name: P {myCharacterType = Priestess, \n Hero status = true, \n Potions = [], \n Pillars = []}", myPriestess.toString());
    }
}