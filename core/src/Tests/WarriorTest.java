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
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    /**
     * Test method for Warrior's default constructor.
     */
    @Test
    void testWarriorConstructor()
    {
        final Warrior myWarrior = new Warrior(entityFactory);

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
        final Warrior myWarrior = new Warrior("W", new Vec2(), entityFactory);

        assertEquals("W", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(10, myWarrior.getHitPoints());
        assertEquals(5, myWarrior.getMaxSpeed());
        assertEquals(1, myWarrior.getDamage());
    }

    /**
     * Test method for {@link Warrior#damage()}
     */
    @Test
    void testDamage()
    {
        final Warrior myWarrior= new Warrior(entityFactory);

        assertTrue(myWarrior.damage() == 1 || (myWarrior.damage() <=4 && myWarrior.damage() >= 1));
    }

    /**
     * Test method for {@link Warrior#special()}
     */
    @Test
    void testSpecial()
    {
        final Warrior myWarrior = new Warrior(entityFactory);

        int damage = myWarrior.special();

        assertTrue(damage < 4 && damage >= 1);
    }
}