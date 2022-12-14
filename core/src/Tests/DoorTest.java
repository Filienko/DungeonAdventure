package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Door;
import MVC.Model.Physics.Vec2;
import MVC.View.Assets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    @Test
    void testDoorConstructor()
    {
        Door myDoor = new Door(entityFactory);

        assertEquals(myDoor.getMonsterCounter(), 4);
    }

    @Test
    void testDoorOL1Constructor()
    {
        Door myDoor = new Door(2, new Vec2(5,5), entityFactory);

        assertTrue(myDoor.getMyPos().equals(new Vec2(5,5)));
        assertEquals(myDoor.getMonsterCounter(), 2);
    }

    @Test
    void testDoorOL2Constructor()
    {
        Door myDoor = new Door(new Vec2(),2, new Vec2(3,4), entityFactory);

        assertTrue(myDoor.getMyPos().equals(new Vec2(3,4)));
        assertEquals(myDoor.getMonsterCounter(), 2);
    }

    @Test
    void testGetMonsterCounter()
    {
        Door myDoor = new Door(2, new Vec2(5,5), entityFactory);

        assertEquals(myDoor.getMonsterCounter(), 2);
    }

    @Test
    void testSetMonsterCounter()
    {
        Door myDoor = new Door(2, new Vec2(5,5), entityFactory);

        myDoor.setMonsterCounter(3);

        assertEquals(myDoor.getMonsterCounter(), 3);
    }

    @Test
    void testDecrementMonsterCounter()
    {
        Door myDoor = new Door(2, new Vec2(5,5), entityFactory);

        myDoor.decrementMonsterCounter();

        assertTrue(myDoor.getMonsterCounter()==1);
    }


    @Test
    void testUpdate()
    {
        Door myDoor = new Door(2, new Vec2(5,5), entityFactory);

        assertTrue(myDoor.getActiveStatus());
        myDoor.decrementMonsterCounter();
        assertTrue(myDoor.getActiveStatus());
        myDoor.decrementMonsterCounter();
        assertFalse(myDoor.getActiveStatus());

        myDoor.update();
        assertFalse(myDoor.getActiveStatus());
    }
}