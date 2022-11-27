package MVC.Model.UnitTests;

import MVC.Model.DungeonItems.Items.Pit;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PitTest {

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
    }

    @Test
    void testSetMyLocation()
    {
    }

    @Test
    void testSetDamageFall()
    {
    }

    @Test
    void testGetDamageFall()
    {
    }
}