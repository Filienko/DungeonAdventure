package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Door;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest
{
    @Test
    void testDoorConstructor()
    {
        Door myDoor = new Door(new EntityFactory());

        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));
    }

    @Test
    void testDoorOL1Constructor()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        assertTrue(myDoor.getMyLocation().equals(new Vec2(5,5)));
    }

    @Test
    void testDoorOL2Constructor()
    {
        Door myDoor = new Door(6,2, new Vec2(3,4), new EntityFactory());

        assertTrue(myDoor.getMyLocation().equals(new Vec2(3,4)));
    }

    @Test
    void testGetMyLocation()
    {
        Door myDoor = new Door(3, new Vec2(5,8), new EntityFactory());
        assertTrue(myDoor.getMyLocation().equals(new Vec2(5,8)));
    }

    @Test
    void testSetMyLocation()
    {
        Door myDoor = new Door(new EntityFactory());
        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));

        myDoor.setMyLocation(new Vec2(20,25));

        assertTrue(myDoor.getMyLocation().equals(new Vec2(20,25)));
    }

    @Test
    void testDecrementMonsterCounter()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        myDoor.decrementMonsterCounter();

        //can I add a getMonsterCounter() method to check if this test passes??
    }

    @Test
    void testUpdate()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        myDoor.update();

        //how to test if update() works?
    }

}