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
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    /**
     * Test method for Priestess's default constructor.
     */
    @Test
    void testPriestessConstructor()
    {
        final Priestess myPriestess = new Priestess(entityFactory);

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
        final Priestess myPriestess = new Priestess("P", new Vec2(), entityFactory);

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
        final Priestess myPriestess = new Priestess(entityFactory);

        myPriestess.setHitPoints(5);

        int oldHealth = myPriestess.getHitPoints();
        int restoredHealth = myPriestess.special();

        assertTrue(myPriestess.getHitPoints() > oldHealth);
        assertEquals(myPriestess.getHitPoints(), restoredHealth + oldHealth);

        assertTrue(restoredHealth + oldHealth <= 10);
        //this ensures that healHero does not heal over Priestess's maximum allowed hit points (10)
    }

    /**
     * Test method for {@link Priestess#damage()}
     */
    @Test
    void testDamage()
    {
        final Priestess myPriestess = new Priestess(entityFactory);

        assertTrue(myPriestess.damage() == 1);
    }

    /**
     * Test method for {@link Priestess#toString()}
     */
    @Test
    void testToString()
    {
        final Priestess myPriestess = new Priestess("P", new Vec2(), entityFactory);

        assertEquals("Name: P {myCharacterType = Priestess, Hero status = true, Potions = '[]', Pillars = 0}", myPriestess.toString());
    }
}