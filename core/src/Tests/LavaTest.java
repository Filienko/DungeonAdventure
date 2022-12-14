package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.Lava;
import MVC.Model.DungeonItems.Items.SpeedPotion;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LavaTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

    /**
     * Test method for Lava's constructor.
     */
    @Test
    void testLavaConstructor()
    {
        Lava myLava = new Lava(entityFactory);

        assertTrue(myLava.getType().equals("lava"));
        assertEquals(myLava.getDamage(), 2);
    }

    /**
     * Test method for {@link Lava#setDamage(int)}.
     */
    @Test
    void testSetDamage()
    {
        Lava myLava = new Lava(entityFactory);

        myLava.setDamage(20);

        assertEquals(myLava.getDamage(), 20);
    }

    /**
     * Test method for {@link Lava#getDamage()}.
     */
    @Test
    void testGetDamage()
    {
        Lava myLava = new Lava(entityFactory);

        assertEquals(myLava.getDamage(), 2);
    }

    /**
     * Test method for {@link Lava#activate(Hero)}.
     */
    @Test
    void testActivate()
    {
        Lava myLava = new Lava(entityFactory);
        Thief myThief = new Thief(entityFactory);

        myThief.setHitPoints(5);
        myLava.activate(myThief);

        assertEquals(myThief.getHitPoints(), (Math.max(0, 5 - myLava.getDamage())));
        assertFalse(myLava.getActiveStatus());
    }
}