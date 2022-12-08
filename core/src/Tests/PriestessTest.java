package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriestessTest
{
    /**
     * Test method for Priestess's default constructor.
     */
    @Test
    void testPriestessConstructor()
    {
        final Priestess myPriestess = new Priestess(new EntityFactory());

        assertEquals("Priestess", myPriestess.getName());
        assertEquals("Priestess", myPriestess.getCharacterType());
        assertTrue(myPriestess.getHeroStatus());
        assertEquals(10, myPriestess.getHitPoints());
        assertEquals(5, myPriestess.getMaxSpeed());
        assertEquals(1, myPriestess.getDamage());
    }

    /**
     * Test method for Priestess's overloaded constructor.
     */
    @Test
    void testPriestessOLConstructor()
    {
        final Priestess myPriestess = new Priestess("P", new Vec2(), new EntityFactory());

        assertEquals("P", myPriestess.getName());
        assertEquals("Priestess", myPriestess.getCharacterType());
        assertTrue(myPriestess.getHeroStatus());
        assertEquals(10, myPriestess.getHitPoints());
        assertEquals(5, myPriestess.getMaxSpeed());
        assertEquals(1, myPriestess.getDamage());
    }

    /**
     * Test method for {@link Priestess#healHero(Hero)}. //why is the link not working?
     */
    @Test
    void testHealHero()
    {
        final Priestess myPriestess1 = new Priestess(new EntityFactory());
        final Priestess myPriestess2 = new Priestess(new EntityFactory());

        int oldHealth = myPriestess1.getHitPoints();
        int restoredHealth = myPriestess1.special(myPriestess2);

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
        final Priestess myPriestess = new Priestess("P", new Vec2(), new EntityFactory());

        assertEquals("Name: P {myCharacterType = Priestess, Hero status = true, Potions = '[]', Pillars = 0}", myPriestess.toString());
    }
}