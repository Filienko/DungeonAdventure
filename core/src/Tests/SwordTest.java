package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.DungeonItems.Door;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.DungeonItems.Room;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Physics;
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

        assertEquals(0, mySword.getCurrentFrame());
        assertEquals(15, mySword.getMyLifeSpan());
    }

    @Test
    void testUpdate()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        //added getter for sword's hero for testing

        assertFalse(mySword.getMyHero().getAttackStatus());

        mySword.setCurrentFrame(mySword.getMyLifeSpan() + 1);

        mySword.update();

        assertFalse(mySword.getActiveStatus());
        assertFalse(entityFactory.getEntities().contains("Sword"));

        mySword.setCurrentFrame(mySword.getMyLifeSpan() - 1);
        long frame = mySword.getCurrentFrame();

        mySword.update();

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
        assertTrue(mySword.getMySize().equals(new Vec2()));
        assertFalse(mySword.getMyHero().getAttackStatus());
    }

    @Test
    void testCollideMonster()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        Monster m = entityFactory.generateMonster("gremlin");

        assertTrue(m.getActiveStatus());
        assertFalse(m.isInvincibility());

        m.setHitPoints(3);
        m.setMyPos(new Vec2());
        mySword.setMyPos(new Vec2());

        //Vec2 ov = Physics.getOverlap(m, mySword);

        int oldHP = m.getHitPoints();

        mySword.collide();

        assertEquals(oldHP - mySword.getMyHero().getDamage(), m.getHitPoints());

        m.setHitPoints(0);

        mySword.collide();

        assertTrue(m.getMySize().equals(new Vec2()));
        assertFalse(m.getActiveStatus());

        m.setHitPoints(5);
        assertTrue(m.isInvincibility());
        assertEquals(m.getInvincibilityEndFrame(), mySword.getMyLifeSpan() + 1);
        assertTrue(m.getMyKnockbackLength() == mySword.getMyHero().getMyKnockbackLength());
        assertTrue(m.getMyKnockbackPower() == mySword.getMyHero().getMyKnockbackPower());

//        Vec2 v = m.getMyPos().minus(mySword.getMyPos());
//        assertTrue(m.getVelocity().equals((v.multiply(v.quickInverseMagnitude())).multiply((float) 4)));
//        assertTrue(m.isInvincibility());

        m.setInvincibility(true);
        oldHP = m.getHitPoints();

        mySword.collide();

        assertTrue(m.getHitPoints() == oldHP);

        m.setActiveStatus(false);
        oldHP = m.getHitPoints();

        mySword.collide();

        assertTrue(m.getHitPoints() == oldHP);
    }
}