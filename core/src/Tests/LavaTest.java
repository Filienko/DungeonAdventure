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
    void testLavaConstructor()
    {
        Lava myLava = new Lava(entityFactory);

        assertTrue(myLava.getType().equals("lava"));
        assertEquals(myLava.getDamage(), 2);
    }

    @Test
    void testSetDamage()
    {
        Lava myLava = new Lava(entityFactory);

        myLava.setDamage(20);

        assertEquals(myLava.getDamage(), 20);
    }

    @Test
    void testGetDamage()
    {
        Lava myLava = new Lava(entityFactory);

        assertEquals(myLava.getDamage(), 2);
    }

    @Test
    void testActivate()
    {
        Lava myLava = new Lava(entityFactory);
        Thief myThief = new Thief(entityFactory);

        int HP = myThief.getHitPoints();

        myLava.activate(myThief);

        assertEquals(myThief.getHitPoints(), HP - myLava.getDamage());
        assertFalse(myLava.getActiveStatus());

    }
}