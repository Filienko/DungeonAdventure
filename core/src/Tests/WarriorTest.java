package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
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
     * Test method for {@link Warrior#attack()}
     */
    @Test
    void testAttack()
    {
        final Warrior myWarrior= new Warrior(new EntityFactory());

        assertTrue(myWarrior.attack() == 1);
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