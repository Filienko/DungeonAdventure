package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest
{
    //javadoc these tests!!!

    @Test
    void testWarriorConstructor()
    {
        final Warrior myWarrior = new Warrior();

        assertEquals("Warrior", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getMyCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(125, myWarrior.getHitPoints());
        assertEquals(4, myWarrior.getMaxSpeed());
        assertEquals(35, myWarrior.getMinDamageRange());
        assertEquals(60, myWarrior.getMaxDamageRange());
    }

    @Test
    void testWarriorOLConstructor()
    {
        final Warrior myWarrior = new Warrior("W", new Vec2());

        assertEquals("W", myWarrior.getName());
        assertEquals("Warrior", myWarrior.getMyCharacterType());
        assertTrue(myWarrior.getHeroStatus());
        assertEquals(125, myWarrior.getHitPoints());
        assertEquals(4, myWarrior.getMaxSpeed());
        assertEquals(35, myWarrior.getMinDamageRange());
        assertEquals(30, myWarrior.getMaxDamageRange());
    }

    @Test
    void testCrushingBlow()
    {
        //how to test if crushing blow happens 40% of the time??

        final Warrior myWarrior1 = new Warrior();
        final Warrior myWarrior2 = new Warrior();

        int oldHealth = myWarrior1.getHitPoints();
        myWarrior1.crushingBlow(myWarrior2);

        assertTrue(myWarrior2.getHitPoints() < oldHealth);
    }
}