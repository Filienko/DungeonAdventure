package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.Bomb;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombTest
{
    @Test
    void testBombConstructor()
    {
        Bomb myBomb = new Bomb(new EntityFactory());

        assertTrue(myBomb.getType().equals("bomb"));
    }

    @Test
    void testActivate()
    {
        Bomb myBomb = new Bomb(new EntityFactory());

        Thief myThief = new Thief(new EntityFactory());

        int hpExpected = myThief.getHitPoints() / 2;

        myBomb.activate(myThief);

        assertEquals(myThief.getHitPoints(), hpExpected);

    }
}