package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
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

        assertTrue(myPit.getType().equals("Pit"));
        assertEquals(myPit.getDamageFall(), 15);
    }

    @Test
    void testPitOL1Constructor()
    {
        Pit myPit = new Pit(new Vec2(10,10), new EntityFactory());

        assertTrue(myPit.getType().equals("Pit"));
        assertEquals(myPit.getDamageFall(), 15);
        assertTrue(myPit.getMyLocation().equals(new Vec2(10,10)));
    }

    @Test
    void testGetMyLocation()
    {
        Pit myPit = new Pit(new Vec2(5,5), new EntityFactory());

        assertTrue(myPit.getMyLocation().equals(new Vec2(5,5)));
    }

    @Test
    void testSetMyLocation()
    {
        Pit myPit = new Pit(new Vec2(5,5), new EntityFactory());

        myPit.setMyLocation(new Vec2(6,6));

        assertTrue(myPit.getMyLocation().equals(new Vec2(6,6)));
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

        assertEquals(myPit.getDamageFall(), 15);
    }

    @Test
    void testItemBehavior()
    {

    }

    @Test
    void testUpdate()
    {
        //write tests for update
    }
}