package MVC.Model.UnitTests;

import MVC.Model.DungeonItems.Door;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest
{
    @Test
    void testDoorConstructor()
    {
        Door myDoor = new Door();

        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));
        assertFalse(myDoor.isMyDoorOpen());
    }

    @Test
    void testDoorOLConstructor()
    {
        Door myDoor = new Door(true);

        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));
        assertTrue(myDoor.isMyDoorOpen());
    }

    @Test
    void testGetMyLocation()
    {
        Door myDoor = new Door();
        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));
    }

    @Test
    void testSetMyLocation()
    {
        Door myDoor = new Door();
        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));

        myDoor.setMyLocation(new Vec2(20,25));

        assertTrue(myDoor.getMyLocation().equals(new Vec2(20,25)));
    }

    @Test
    void testIsMyDoorOpen()
    {
        Door myDoor = new Door();

        assertFalse(myDoor.isMyDoorOpen());
    }

    @Test
    void testSetMyDoorOpen()
    {
        Door myDoor = new Door();

        assertFalse(myDoor.isMyDoorOpen());

        myDoor.setMyDoorOpen(true);

        assertTrue(myDoor.isMyDoorOpen());
    }

    @Test
    void testCompareTo()
    {
        //theres no way to set weights?

        Door myDoor1 = new Door(true);
        Door myDoor2 = new Door();

        int comparison = myDoor1.compareTo(myDoor2);

        assertEquals(comparison, 0); //the doors always have the same weights ?

    }
}