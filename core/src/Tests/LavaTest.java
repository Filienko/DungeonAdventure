package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.Lava;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LavaTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    @Test
    void testPitConstructor()
    {
        Lava myLava = new Lava(entityFactory);

        assertTrue(myLava.getType().equals("lava"));
        assertEquals(myLava.getDamage(), 2);
    }

    @Test
    void testPitOL1Constructor()
    {
        Lava myLava = new Lava(new Vec2(10,10), entityFactory);

        assertTrue(myLava.getType().equals("lava"));
        assertEquals(myLava.getDamage(), 2);
        assertTrue(myLava.getMyPos().equals(new Vec2(10,10)));
    }

    @Test
    void testSetDamageFall()
    {
        Lava myPit = new Lava(new Vec2(20,20), entityFactory);

        myPit.setDamage(20);

        assertEquals(myPit.getDamage(), 20);
    }

    @Test
    void testGetDamageFall()
    {
        Lava myPit = new Lava(new Vec2(2,2), entityFactory);

        assertEquals(myPit.getDamage(), 2);
    }

    @Test
    void testActivate()
    {
        Lava myPit = new Lava(entityFactory);
        Thief myThief = new Thief(entityFactory);

        int HP = myThief.getHitPoints();

        myPit.activate(myThief);

        assertEquals(myThief.getHitPoints(), HP - myPit.getDamage());
        assertFalse(myPit.getActiveStatus());

    }
}