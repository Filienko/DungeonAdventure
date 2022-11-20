package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriestessTest
{

    //javadoc these tests!!!

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

    @Test
    void testHealHero()
    {
        final Priestess myPriestess1 = new Priestess();
        final Priestess myPriestess2 = new Priestess();

        int oldHealth = myPriestess1.getHitPoints();
        myPriestess1.healHero(myPriestess2);

        assertTrue(myPriestess2.getHitPoints() > oldHealth);
    }

    @Test
    void testAttack()
    {
        //finish attack() method and then write these tests
    }
}