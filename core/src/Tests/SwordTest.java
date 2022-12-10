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

        assertEquals(15, mySword.getMyLifeSpan());
        assertEquals(0, mySword.getCurrentFrame());
    }

    @Test
    void testUpdate()
    {
        Sword mySword = Sword.getInstance(new EntityFactory(), new Warrior(new EntityFactory()));

        //write update test
    }

    @Test
    void getMyLifeSpan()
    {
        Sword mySword = Sword.getInstance(new EntityFactory(),new Warrior(new EntityFactory()));

        assertEquals(15, mySword.getMyLifeSpan());
    }

    @Test
    void testDestroy()
    {
        Sword mySword = Sword.getInstance(new EntityFactory(), new Warrior(new EntityFactory()));

        mySword.destroy();

        assertFalse(mySword.getActiveStatus());
    }

    @Test
    void testCollide()
    {
        Sword mySword = Sword.getInstance(new EntityFactory(), new Warrior(new EntityFactory()));

        mySword.collide(); //myEntityFactory is null, initialized in constructor

        //write collide test
    }

}