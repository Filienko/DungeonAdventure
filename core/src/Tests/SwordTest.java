package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    @Test
    void testGetInstance()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        assertEquals(15, mySword.getMyLifeSpan());
        assertEquals(0, mySword.getCurrentFrame());
    }

    @Test
    void testUpdate()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        //no way to retrieve sword's hero, add getter for testing?
        //assertFalse(entityFactory.getHero().getActiveStatus());

        mySword.setCurrentFrame(mySword.getMyLifeSpan() + 1);

        mySword.update();

        //assertTrue(entityFactory.getHero().getAttackStatus());
        assertFalse(mySword.getActiveStatus());
        assertFalse(entityFactory.getEntities().contains("Sword"));

        long frame = mySword.getCurrentFrame();
        mySword.setCurrentFrame(mySword.getMyLifeSpan() - 1);

        mySword.update(); //calls movement, collide - write tests

        assertEquals(mySword.getCurrentFrame(), frame + 1);

    }

    @Test
    void getMyLifeSpan()
    {
        Sword mySword = Sword.getInstance(entityFactory,new Warrior(entityFactory));

        assertEquals(15, mySword.getMyLifeSpan());
    }

    @Test
    void testDestroy()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        mySword.destroy();

        assertFalse(mySword.getActiveStatus());
    }

    @Test
    void testCollide()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        mySword.collide(); //myEntityFactory is null, initialized in constructor

        //write collide test
    }

}