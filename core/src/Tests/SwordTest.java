package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    private final Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

    /**
     * Test method for {@link Sword#getInstance(EntityFactory, Hero)}.
     */
    @Test
    void testGetInstance()
    {
        assertEquals(0, mySword.getCurrentFrame());
        assertEquals(15, mySword.getMyLifeSpan());
    }

    /**
     * Test method for {@link Sword#update()}.
     */
    @Test
    void testUpdate()
    {
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

    /**
     * Test method for {@link Sword#getMyLifeSpan()}.
     */
    @Test
    void getMyLifeSpan()
    {
        assertEquals(15, mySword.getMyLifeSpan());
    }

    /**
     * Test method for {@link Sword#destroy()}.
     */
    @Test
    void testDestroy()
    {
        mySword.destroy();

        assertFalse(mySword.getActiveStatus());
        assertTrue(mySword.getMySize().equals(new Vec2()));
        assertFalse(mySword.getMyHero().getAttackStatus());
    }

    /**
     * Test method for {@link Sword#collide()}.
     */
    @Test
    void testCollideMonster()
    {
        Monster m = entityFactory.generateMonster("gremlin");

        entityFactory.addEntity(m);

        entityFactory.update();

        assertTrue(m.getActiveStatus());
        assertFalse(m.isInvincibility());

        m.setHitPoints(5);
        m.setMyPos(new Vec2());
        mySword.setMyPos(new Vec2());

        int oldHP = m.getHitPoints();

        mySword.collide();

        assertEquals(oldHP - mySword.getMyHero().getDamage(), m.getHitPoints());
        assertTrue(m.isInvincibility());
        assertEquals(m.getInvincibilityEndFrame(),
                (mySword.getMyLifeSpan() - (mySword.getCurrentFrame() + 1)) + m.getCurrentFrame());
        assertTrue(m.isKnockBack());
        assertEquals(m.getKnockBackEndFrame(),
                mySword.getMyHero().getMyKnockBackLength() + m.getCurrentFrame());

        m.setInvincibility(true);
        oldHP = m.getHitPoints();
        mySword.collide();

        assertTrue(m.getHitPoints() == oldHP);

        m.setActiveStatus(false);
        oldHP = m.getHitPoints();
        mySword.collide();

        assertTrue(m.getHitPoints() == oldHP);
    }

    /**
     * Test method for {@link Sword#collide()}.
     */
    @Test
    void testCollideWorm()
    {
        Worm theWorm = new Worm("worm", 10, 2, 3, new Vec2(2,2), new Vec2(2,2),
                new Vec2(2,2), entityFactory);

        entityFactory.addEntity(theWorm);

        entityFactory.update();

        assertTrue(theWorm.getActiveStatus());
        assertFalse(theWorm.isInvincibility());

        //this is testing the worm not the tail??
        theWorm.setHitPoints(5);
        theWorm.setMyPos(new Vec2(1,1));
        theWorm.setMySize(new Vec2(1,1));
        mySword.setMyPos(new Vec2(576,320));

        int oldHP = theWorm.getHitPoints();
        float oldMS = theWorm.getMaxSpeed();

        mySword.collide();

        assertEquals(theWorm.getHitPoints(), oldHP - 1);
        assertTrue(theWorm.isInvincibility());
        assertEquals(theWorm.getInvincibilityEndFrame(),60);
        assertTrue(theWorm.isKnockBack());
        assertEquals(theWorm.getKnockBackEndFrame(), 60);
        assertEquals(theWorm.getMaxSpeed(), oldMS + Math.signum(oldMS) * .5f);

        Vec2 oldSize = theWorm.getMySize();

        theWorm.setInvincibility(false);

        mySword.collide();

        assertTrue(theWorm.getMySize().equals(oldSize));
    }
}