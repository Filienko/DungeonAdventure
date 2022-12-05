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
        assertEquals(myBomb.getMyPos(), new Vec2(0,0));
    }

    @Test
    void testItemBehavior()
    {
        Bomb myBomb = new Bomb(new EntityFactory());

        Thief myThief = new Thief(new EntityFactory());

        int HP = myThief.getHitPoints();

        myBomb.activate(myThief);

        assertEquals(myThief.getHitPoints(), HP / 2);

    }

    @Test
    void testUpdate()
    {
        //write tests for update
    }
}