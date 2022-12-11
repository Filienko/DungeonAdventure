package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Door;
import MVC.Model.Physics.Vec2;
import MVC.View.Assets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest
{
    @Test
    void testDoorConstructor()
    {
        Door myDoor = new Door(new EntityFactory());

        assertEquals(myDoor.getMonsterCounter(), 4);
    }

    @Test
    void testDoorOL1Constructor()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        assertTrue(myDoor.getMyPos().equals(new Vec2(5,5)));
        assertEquals(myDoor.getMonsterCounter(), 2);
    }

    @Test
    void testDoorOL2Constructor()
    {
        Door myDoor = new Door(new Vec2(),2, new Vec2(3,4), new EntityFactory());

        assertTrue(myDoor.getMyPos().equals(new Vec2(3,4)));
        assertEquals(myDoor.getMonsterCounter(), 2);
    }
//
//    @Test
//    void testgetMyPos()
//    {
//        Door myDoor = new Door(3, new Vec2(5,8), new EntityFactory());
//        assertTrue(myDoor.getMyPos().equals(new Vec2(5,8)));
//    }

    @Test
    void testGetMonsterCounter()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        myDoor.getMonsterCounter();

        assertEquals(myDoor.getMonsterCounter(), 2);
    }

    @Test
    void testSetMonsterCounter()
    {
        Door myDoor = new Door(2, new Vec2(5,5), new EntityFactory());

        myDoor.setMonsterCounter(3);

        assertEquals(myDoor.getMonsterCounter(), 3);
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
        EntityFactory theEntityFactory = new EntityFactory(null,"Mock");
        Door myDoor = new Door(2, new Vec2(5,5), theEntityFactory);

        assertTrue(myDoor.getActiveStatus());
        myDoor.decrementMonsterCounter();
        assertTrue(myDoor.getActiveStatus());
        myDoor.decrementMonsterCounter();
        assertTrue(myDoor.getActiveStatus());

        myDoor.update();
        assertFalse(myDoor.getActiveStatus());
    }
}