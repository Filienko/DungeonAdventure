package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Items.Bomb;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

    /**
     * Test method for Bomb's constructor.
     */
    @Test
    void testBombConstructor()
    {
        Bomb myBomb = new Bomb(entityFactory);

        assertTrue(myBomb.getType().equals("bomb"));
    }

    /**
     * Test method for {@link Bomb#activate(Hero)}.
     */
    @Test
    void testActivate()
    {
        Bomb myBomb = new Bomb(entityFactory);

        Thief myThief = new Thief(entityFactory);

        int hpExpected = myThief.getHitPoints() / 2;

        myBomb.activate(myThief);

        assertEquals(myThief.getHitPoints(), hpExpected);
    }
}