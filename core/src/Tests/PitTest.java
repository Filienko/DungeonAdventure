package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.Pit;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PitTest
{
    @Test
    void testPitConstructor()
    {
        Pit myPit = new Pit(new EntityFactory());

        assertTrue(myPit.getType().equals("pit"));
        assertEquals(myPit.getDamageFall(), 2);
    }

    @Test
    void testPitOL1Constructor()
    {
        Pit myPit = new Pit(new Vec2(10,10), new EntityFactory());

        assertTrue(myPit.getType().equals("pit"));
        assertEquals(myPit.getDamageFall(), 2);
        assertTrue(myPit.getMyPos().equals(new Vec2(10,10)));
    }

    @Test
    void testSetDamageFall()
    {
        Pit myPit = new Pit(new Vec2(20,20), new EntityFactory());

        myPit.setDamageFall(20);

        assertEquals(myPit.getDamageFall(), 20);
    }

    @Test
    void testGetDamageFall()
    {
        Pit myPit = new Pit(new Vec2(2,2), new EntityFactory());

        assertEquals(myPit.getDamageFall(), 2);
    }

    @Test
    void testActivate()
    {
        Pit myPit = new Pit(new EntityFactory());
        Thief myThief = new Thief(new EntityFactory());

        int HP = myThief.getHitPoints();

        myPit.activate(myThief);

        assertEquals(myThief.getHitPoints(), HP - myPit.getDamageFall());
        assertFalse(myPit.getActiveStatus());

    }
}