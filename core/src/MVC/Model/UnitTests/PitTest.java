package MVC.Model.UnitTests;

import MVC.Model.DungeonItems.Items.Pit;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PitTest
{
    @Test
    void testPitConstructor()
    {
        Pit myPit = new Pit();

        assertTrue(myPit.getType().equals("Pit"));
        assertEquals(myPit.getDamageFall(), 15);
    }

    @Test
    void testPitOL1Constructor()
    {
        Pit myPit = new Pit(new Vec2(10,10));

        assertTrue(myPit.getType().equals("Pit"));
        assertEquals(myPit.getDamageFall(), 15);
        assertTrue(myPit.getMyLocation().equals(new Vec2(10,10)));
    }

    @Test
    void testPitOL2Constructor()
    {
        Pit myPit = new Pit(new Vec2(2,2), 10);

        assertTrue(myPit.getType().equals("Pit"));
        assertTrue(myPit.getMyLocation().equals(new Vec2(2,2)));
        assertEquals(myPit.getDamageFall(), 10);
    }

    @Test
    void testGetMyLocation()
    {
        Pit myPit = new Pit(new Vec2(5,5));

        assertTrue(myPit.getMyLocation().equals(new Vec2(5,5)));
    }

    @Test
    void testSetMyLocation()
    {
        Pit myPit = new Pit(new Vec2(5,5));

        myPit.setMyLocation(new Vec2(6,6));

        assertTrue(myPit.getMyLocation().equals(new Vec2(6,6)));
    }

    @Test
    void testSetDamageFall()
    {
        Pit myPit = new Pit(new Vec2(20,20), 10);

        myPit.setDamageFall(20);

        assertEquals(myPit.getDamageFall(), 20);
    }

    @Test
    void testGetDamageFall()
    {
        Pit myPit = new Pit(new Vec2(2,2), 10);

        assertEquals(myPit.getDamageFall(), 10);
    }
}