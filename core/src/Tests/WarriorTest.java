package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarriorTest
{
    /**
     * Test method for Warrior's default constructor.
     */
    @Test
    void testWarriorConstructor()
    {
        final Warrior myWarrior = new Warrior();

        assertEquals("Warrior", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getMyCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(10, myWarrior.getHitPoints());
        assertEquals(5, myWarrior.getMaxSpeed());
        assertEquals(1, myWarrior.getDamage());
    }

    /**
     * Test method for Warrior's overloaded constructor.
     */
    @Test
    void testWarriorOLConstructor()
    {
        final Warrior myWarrior = new Warrior("W", new Vec2());

        assertEquals("W", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getMyCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(10, myWarrior.getHitPoints());
        assertEquals(5, myWarrior.getMaxSpeed());
        assertEquals(1, myWarrior.getDamage());
    }

    /**
     * Test method for {@link Warrior#attack(DungeonCharacter, Vec2)}
     */
    @Test
    void testAttack()
    {
        //do we need to test if crushing blow happens 40% of the time?

        final Warrior myWarrior1 = new Warrior();
        final Warrior myWarrior2 = new Warrior();

        int oldHealth = myWarrior1.getHitPoints();
        int damage = myWarrior1.attack(myWarrior2, new Vec2());

        assertTrue(myWarrior2.getHitPoints() < oldHealth);
        assertEquals(myWarrior2.getHitPoints(), oldHealth - damage);
    }

    /**
     * Test method for {@link Warrior#crushingBlow(DungeonCharacter)}
     */
    @Test
    void testCrushingBlow()
    {
        //how to test if crushing blow happens 40% of the time??

        final Warrior myWarrior1 = new Warrior();
        final Warrior myWarrior2 = new Warrior();

        int oldHealth = myWarrior1.getHitPoints();
        int damage = myWarrior1.crushingBlow(myWarrior2);

        assertTrue(myWarrior2.getHitPoints() < oldHealth);
        assertEquals(myWarrior2.getHitPoints(), oldHealth - damage);
    }
}