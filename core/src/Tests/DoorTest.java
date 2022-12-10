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

        assertTrue(myDoor.getMyPos().equals(new Vec2(0,0)));
    }

    @Test
    void testDoorOL1Constructor()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        assertTrue(myDoor.getMyPos().equals(new Vec2(5,5)));
    }

    @Test
    void testDoorOL2Constructor()
    {
        Door myDoor = new Door(new Vec2(),2, new Vec2(3,4), new EntityFactory());

        assertTrue(myDoor.getMyPos().equals(new Vec2(3,4)));
    }

    @Test
    void testgetMyPos()
    {
        Door myDoor = new Door(3, new Vec2(5,8), new EntityFactory());
        assertTrue(myDoor.getMyPos().equals(new Vec2(5,8)));
    }

    @Test
    void testSetMyLocation()
    {
        Door myDoor = new Door(new EntityFactory());
        assertTrue(myDoor.getMyPos().equals(new Vec2(0,0)));

        myDoor.setMyPos(new Vec2(20,25));

        assertTrue(myDoor.getMyPos().equals(new Vec2(20,25)));
    }

    @Test
    void testGetMonsterCounter()
    {
        Door myDoor = new Door(12, new Vec2(5,5), new EntityFactory());

        assertEquals( myDoor.getMonsterCounter(), 12);

    }


    @Test
    void testDecrementMonsterCounter()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        myDoor.decrementMonsterCounter();

        assertTrue(myDoor.getMonsterCounter()==1);
    }

    @Test
    void testUpdate()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());
        assertTrue(myDoor.getActiveStatus());
        myDoor.decrementMonsterCounter();
        assertTrue(myDoor.getActiveStatus());
        myDoor.decrementMonsterCounter();
        assertTrue(myDoor.getActiveStatus());

        myDoor.update();
        assertFalse(myDoor.getActiveStatus());
    }
}