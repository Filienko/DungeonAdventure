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

        assertTrue(myBomb.getType().equals("Bomb"));
    }

    @Test
    void testActivate()
    {
        Bomb myBomb = new Bomb(new EntityFactory());

        Thief myThief = new Thief(new EntityFactory());

        int hpExpected = myThief.getHitPoints() / 2;

        //System.out.println(hpExpected);

        myBomb.activate(myThief);

        //System.out.println(myThief.getHitPoints());

        assertEquals(myThief.getHitPoints(), hpExpected);

    }

    @Test
    void testUpdate()
    {
        //write tests for update
    }
}