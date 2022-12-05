package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest
{
    @Test
    void testGetInstance()
    {
        Sword mySword = Sword.getInstance(new Vec2(), new EntityFactory());

        assertTrue(mySword.getBoundingBox().equals(new Vec2()));
        assertEquals(0, mySword.getMyLifeSpan());
    }

    @Test
    void update()
    {
        //review update and then test it
    }

    @Test
    void getBoundingBox()
    {
        Sword mySword = Sword.getInstance(new Vec2(5,5), new EntityFactory());

        assertTrue(mySword.getBoundingBox().equals(new Vec2(5,5)));
    }

    @Test
    void setBoundingBox()
    {
        Sword mySword = Sword.getInstance(new Vec2(), new EntityFactory());

        assertTrue(mySword.getBoundingBox().equals(new Vec2()));

        mySword.setBoundingBox(new Vec2(10,10));

        assertTrue(mySword.getBoundingBox().equals(new Vec2(10,10)));
    }
}