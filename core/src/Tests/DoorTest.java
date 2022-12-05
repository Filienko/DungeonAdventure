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
    void testDoorOLConstructor()
    {
        Door myDoor = new Door(true, new EntityFactory()); //use another constructor

        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));
    }

    @Test
    void testGetMyLocation()
    {
        Door myDoor = new Door(new EntityFactory());
        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));
    }

    @Test
    void testSetMyLocation()
    {
        Door myDoor = new Door(new EntityFactory());
        assertTrue(myDoor.getMyLocation().equals(new Vec2(0,0)));

        myDoor.setMyLocation(new Vec2(20,25));

        assertTrue(myDoor.getMyLocation().equals(new Vec2(20,25)));
    }

}