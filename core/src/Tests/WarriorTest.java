package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest
{
    /**
     * Test method for Warrior's default constructor.
     */
    @Test
    void testWarriorConstructor()
    {
        final Warrior myWarrior = new Warrior(new EntityFactory());

        assertEquals("Warrior", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getMyCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(125, myWarrior.getHitPoints());
        assertEquals(4, myWarrior.getMaxSpeed());
        assertEquals(35, myWarrior.getMinDamageRange());
        assertEquals(60, myWarrior.getMaxDamageRange());
    }

    /**
     * Test method for Warrior's overloaded constructor.
     */
    @Test
    void testWarriorOLConstructor()
    {
        final Warrior myWarrior = new Warrior("W", new Vec2(), new EntityFactory());

        assertEquals("W", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getMyCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(125, myWarrior.getHitPoints());
        assertEquals(4, myWarrior.getMaxSpeed());
        assertEquals(35, myWarrior.getMinDamageRange());
        assertEquals(30, myWarrior.getMaxDamageRange());
    }

    /**
     * Test method for {@link
     */
    @Test
    void testAttack()
    {
        //do we need to test if crushing blow happens 40% of the time?

        final Warrior myWarrior1 = new Warrior(new EntityFactory());
        final Warrior myWarrior2 = new Warrior(new EntityFactory());

        int oldHealth = myWarrior1.getHitPoints();
        int damage = myWarrior1.attack(myWarrior2, new Vec2());

        assertTrue(myWarrior2.getHitPoints() < oldHealth);
        assertEquals(myWarrior2.getHitPoints(), oldHealth - damage);
    }

    /**
     * Test method for {@link
     */
    @Test
    void testCrushingBlow()
    {
        final Warrior myWarrior = new Warrior(new EntityFactory());

        final Thief myThief = new Thief(new EntityFactory());

        int damage = myWarrior.special(myThief); //may need to change this

        assertTrue(damage < 175 && damage > 75);

    }
}