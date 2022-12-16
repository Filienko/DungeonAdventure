package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.*;
import MVC.Model.DungeonItems.Wall;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WormTest
{
    private final EntityFactory myEntityFactory = new EntityFactory(null, "Mock");;
    private final Hero myHero = myEntityFactory.getHero();


    /**
     * Test method for Worm's constructor.
     */
    @Test
    void testWormConstructor()
    {
        Worm worm = new Worm(new Vec2(0, 0), myEntityFactory);

        // Test construction of the Worm
        assertEquals("Worm", worm.getType());
        assertFalse(worm.getHeroStatus());
        assertEquals(10, worm.getHitPoints());
        assertEquals(4, worm.getMaxSpeed());
        assertEquals(1, worm.getDamage());
        assertEquals(96, worm.getMySize().getMyX());
        assertEquals(96, worm.getMySize().getMyY());
        assertEquals(0, worm.getMyPos().getMyX());
        assertEquals(0, worm.getMyPos().getMyY());
        assertEquals(0, worm.getMyPreviousPos().getMyX());
        assertEquals(0, worm.getMyPreviousPos().getMyY());
        assertEquals(0, worm.getRoom().getMyX());
        assertEquals(0, worm.getRoom().getMyY());
        assertNotNull(worm.getSegments());
        assertEquals(10, worm.getMyKnockBackPower());
        assertEquals(10, worm.getMyKnockBackLength());

        // Test construction of the first Body segment
        assertEquals(64, worm.getSegments().get(0).getMySize().getMyX());
        assertEquals(64, worm.getSegments().get(0).getMySize().getMyY());
        assertEquals(0, worm.getSegments().get(0).getMyPos().getMyX());
        assertEquals(0, worm.getSegments().get(0).getMyPos().getMyY());
        assertEquals(0, worm.getSegments().get(0).getMyPreviousPos().getMyX());
        assertEquals(0, worm.getSegments().get(0).getMyPreviousPos().getMyY());
        assertEquals(myEntityFactory, worm.getSegments().get(0).getMyEntityFactory());
        assertEquals(worm, worm.getSegments().get(0).getHead());

        // Test construction of the second Body segment
        assertEquals(64, worm.getSegments().get(1).getMySize().getMyX());
        assertEquals(64, worm.getSegments().get(1).getMySize().getMyY());
        assertEquals(0, worm.getSegments().get(1).getMyPos().getMyX());
        assertEquals(0, worm.getSegments().get(1).getMyPos().getMyY());
        assertEquals(0, worm.getSegments().get(1).getMyPreviousPos().getMyX());
        assertEquals(0, worm.getSegments().get(1).getMyPreviousPos().getMyY());
        assertEquals(myEntityFactory, worm.getSegments().get(1).getMyEntityFactory());
        assertEquals(worm, worm.getSegments().get(1).getHead());

        // Test construction of the third Body segment
        assertEquals(48, worm.getSegments().get(2).getMySize().getMyX());
        assertEquals(48, worm.getSegments().get(2).getMySize().getMyY());
        assertEquals(0, worm.getSegments().get(2).getMyPos().getMyX());
        assertEquals(0, worm.getSegments().get(2).getMyPos().getMyY());
        assertEquals(0, worm.getSegments().get(2).getMyPreviousPos().getMyX());
        assertEquals(0, worm.getSegments().get(2).getMyPreviousPos().getMyY());
        assertEquals(myEntityFactory, worm.getSegments().get(2).getMyEntityFactory());
        assertEquals(worm, worm.getSegments().get(2).getHead());

        // Test construction of the Tail
        assertEquals(48, worm.getTail().getMySize().getMyX());
        assertEquals(48, worm.getTail().getMySize().getMyY());
        assertEquals(0, worm.getTail().getMyPos().getMyX());
        assertEquals(0, worm.getTail().getMyPos().getMyY());
        assertEquals(0, worm.getTail().getMyPreviousPos().getMyX());
        assertEquals(0, worm.getTail().getMyPreviousPos().getMyY());
        assertEquals(myEntityFactory, worm.getTail().getMyEntityFactory());
        assertEquals(worm, worm.getTail().getHead());
    }

    /**
     * Test Method for {@link Worm#update()}
     */
    @Test
    void updateTest()
    {
        Worm worm = new Worm(new Vec2(0, 0), myEntityFactory);
        Sword sword = Sword.getInstance(myEntityFactory, myHero);

        worm.setCurrentFrame(40);
        worm.setInvincibility(true, 1);
        worm.knockBack(sword, 0, 1);
        worm.update();
        worm.update();
        assertFalse(worm.isInvincibility());
        assertFalse(worm.isKnockBack());
    }

    /**
     * Test method for {@link Worm#movement()}
     */
    @Test
    void movementTest()
    {
        Worm worm = new Worm(new Vec2(0, 0), myEntityFactory);
        Vec2 pos = worm.getMyPos();
        worm.update();
        Vec2 velocity = worm.getVelocity();
        Vec2 newPos = pos.add(velocity);
        assertEquals(worm.getMyPreviousPos(), pos);
        assertTrue(worm.getMyPos().equals(newPos));
    }

    /**
     * Test method for {@link Worm#collide()}  with walls
     */
    @Test
    void collisionTest1()
    {
        Worm worm = new Worm(new Vec2(0, 0), myEntityFactory);
        Wall wall = new Wall(new Vec2(85, 85), new Vec2(64, 64));
        myEntityFactory.addEntity(wall);
        myEntityFactory.update();

        worm.setMyPreviousPos(new Vec2(0, 60));
        worm.setMyPos(new Vec2(60, 60));
        float speed = worm.getMaxSpeed();

        Vec2 overlap = Physics.getOverlap(worm, wall);
        assertTrue(overlap.getMyX() > 0);
        assertTrue(overlap.getMyY() > 0);

        worm.collide();
        overlap = Physics.getOverlap(worm, wall);
        assertTrue(overlap.getMyX() <= 0 || overlap.getMyY() <= 0);
        assertEquals(-speed, worm.getMaxSpeed());
    }

    /**
     * Test method for {@link Worm#collide()}  with the Hero
     */
    @Test
    void collisionTest2()
    {
        Worm worm = new Worm(new Vec2(0, 0), myEntityFactory);
        myEntityFactory.addEntity(myHero);
        myEntityFactory.update();

        worm.setMyPreviousPos(new Vec2(0, 60));
        worm.setMyPos(new Vec2(60, 60));
        myHero.setMyPos(new Vec2(130, 60));
        int health = myHero.getHitPoints();

        worm.collide();

        assertEquals(health - 1, myHero.getHitPoints());
        assertTrue(myHero.isInvincibility());
        assertTrue(myHero.isKnockBack());
    }
}
