package MVC.Model.UnitTests;

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
        Bomb myBomb = new Bomb();

        assertTrue(myBomb.getType().equals("Bomb"));
        assertEquals(myBomb.getMyPos(), new Vec2(0,0));
    }

    @Test
    void testBombOLConstructor()
    {
        Bomb myBomb = new Bomb(new Vec2(5,10));

        assertTrue(myBomb.getType().equals("Bomb"));
        assertTrue(myBomb.getMyPos().equals(new Vec2(5,10)));
    }

    @Test
    void testBlast()
    {
        Bomb myBomb = new Bomb();

        Thief myThief = new Thief();

        int HP = myThief.getHitPoints();

        myBomb.blast(myThief);

        assertEquals(myThief.getHitPoints(), HP / 2);

    }
}