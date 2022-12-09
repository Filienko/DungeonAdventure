package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest
{
    @Test
    void testGetInstance()
    {
        Sword mySword = Sword.getInstance(new EntityFactory(), new Warrior(new EntityFactory()));

        assertTrue(mySword.getMySize().equals(new Vec2()));
        assertEquals(0, mySword.getMyLifeSpan()); //lifeSpan == 60 or 0???
    }

    @Test
    void testUpdate()
    {
        //review update and then test it
    }

    @Test
    void testGetMySize()
    {
        Sword mySword = Sword.getInstance(new EntityFactory(),new Warrior(new EntityFactory()));

        assertTrue(mySword.getMySize().equals(new Vec2(46,46)));
    }

    @Test
    void testSetMySize()
    {
        Sword mySword = Sword.getInstance(new EntityFactory(),new Warrior(new EntityFactory()));

        assertTrue(mySword.getMySize().equals(new Vec2(46,46)));

        mySword.setMySize(new Vec2(10,10));

        assertTrue(mySword.getMySize().equals(new Vec2(10,10)));
    }
}