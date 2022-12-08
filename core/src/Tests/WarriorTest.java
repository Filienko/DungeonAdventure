package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
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
        final Warrior myWarrior = new Warrior(new EntityFactory());

        assertEquals("Warrior", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getCharacterType());
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
        final Warrior myWarrior = new Warrior("W", new Vec2(), new EntityFactory());

        assertEquals("W", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(10, myWarrior.getHitPoints());
        assertEquals(5, myWarrior.getMaxSpeed());
        assertEquals(1, myWarrior.getDamage());
    }

    /**
     * Test method for {@link Warrior#(DungeonCharacter, Vec2)} //why isnt link working?
     */
    @Test
    void testAttack()
    {
        final Warrior myWarrior1 = new Warrior(new EntityFactory());
        final Warrior myWarrior2 = new Warrior(new EntityFactory());

        int oldHealth = myWarrior1.getHitPoints();
        int damage = myWarrior1.attack();

        assertTrue(myWarrior2.getHitPoints() < oldHealth);
        assertEquals(myWarrior2.getHitPoints(), oldHealth - damage);
    }

    /**
     * Test method for {@link Warrior#(DungeonCharacter)}
     */
    @Test
    void testSpecial()
    {
        final Warrior myWarrior = new Warrior(new EntityFactory());

        int damage = myWarrior.special();

        assertTrue(damage < 4 && damage > 1);
    }
}