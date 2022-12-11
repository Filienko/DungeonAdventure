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
     * Test method for {@link Priestess#special()}.
     */
    @Test
    void testSpecial()
    {
        final Priestess myPriestess = new Priestess(new EntityFactory());

        myPriestess.setHitPoints(5);

        int oldHealth = myPriestess.getHitPoints();
        int restoredHealth = myPriestess.special();

        assertTrue(myPriestess.getHitPoints() > oldHealth);
        assertEquals(myPriestess.getHitPoints(), restoredHealth + oldHealth);

        assertTrue(restoredHealth + oldHealth <= 10);
        //this ensures that healHero does not heal over Priestess's maximum allowed hit points (10)
    }

    /**
     * Test method for {@link Priestess#attack()}
     */
    @Test
    void testAttack()
    {
        final Priestess myPriestess = new Priestess(new EntityFactory(null, "Mock"));

        assertTrue(myPriestess.attack() == 1); //this this ok if im testing priestess^^

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