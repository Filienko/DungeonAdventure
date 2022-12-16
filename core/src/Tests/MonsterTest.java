package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonItems.Door;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest
{
    private final EntityFactory myEntityFactory = new EntityFactory(null, "Mock");
    private final Hero myHero = new Warrior(myEntityFactory);
    /**
     * Test method for Monster's constructor (uses Ogre statistics).
     */
    @Test
    void testMonsterConstructorOgre()
    {
        final Monster myMonster = new Monster("Ogre", 5, 2, 2, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        assertEquals("Ogre", myMonster.getMonsterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(5, myMonster.getHitPoints());
        assertEquals(2, myMonster.getMaxSpeed());
        assertEquals(2, myMonster.getDamage());
    }

    /**
     * Test method for Monster's constructor (uses Gremlin statistics).
     */
    @Test
    void testMonsterConstructorGremlin()
    {
        final Monster myMonster = new Monster("Gremlin", 3, 1, 3, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        assertEquals("Gremlin", myMonster.getMonsterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(3, myMonster.getHitPoints());
        assertEquals(3, myMonster.getMaxSpeed());
        assertEquals(1, myMonster.getDamage());
    }

    /**
     * Test method for {@link Monster#attack()}
     */
    @Test
    void testAttack()
    {
        final Monster myMonster = new Monster("Swarm of Rats",1 , 1, 7, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        assertEquals(myMonster.attack(), myMonster.getDamage());
    }

    /**
     * Test method for {@link Monster#destroy()}
     */
    @Test
    void testDestroy()
    {
        final Monster myMonster = new Monster("Gremlin", 3, 1, 3, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        myEntityFactory.generatePillars();

        myMonster.destroy();
        assertFalse(myMonster.getActiveStatus());
        assertTrue(myMonster.getMySize().equals(new Vec2()));

        for (int i = 0; i < myMonster.getMyEntityFactory().getEntities().size(); i++)
        {
            Entity e = myMonster.getMyEntityFactory().getEntities().get(i);
            if (e.getType().equals("door"))
            {
                assertTrue(((Door)e).getMonsterCounter()<0);
                //System.out.println("true");
            } else if (e.getType().equals("pillar"))
            {
                assertTrue(((Pillar)e).isBroken());
                //System.out.println("true");
            }

            assertTrue(myMonster.getMyEntityFactory().getEntities().size() > 1);
        }
    }

    /**
     * Test method for {@link Monster#update()}
     */
    @Test
    void testUpdate()
    {
        final Monster myMonster = new Monster("Gremlin", 3, 1, 3, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        long iEndFrame = myMonster.getInvincibilityEndFrame();
        myMonster.setCurrentFrame(iEndFrame+1);
        myMonster.setInvincibility(true);
        myMonster.update();
        assertFalse(myMonster.isInvincibility());

        long kEndFrame = myMonster.getKnockBackEndFrame();
        myMonster.setCurrentFrame(kEndFrame+1);
        myMonster.setKnockBack(true);
        myMonster.update();
        assertFalse(myMonster.isKnockBack());

        //cannot test this bc assets are null
//        myMonster.setHitPoints(0);
//        myHero.setInvincibility(false);
//
//        myMonster.update();
//        assertEquals(myMonster.getDamage(), 0);

        myMonster.setHitPoints(1);
        myMonster.setKnockBack(false);
        myMonster.update();
        Vec2 pos = myMonster.getMyPos();
        Vec2 vel = myMonster.getVelocity();
        assertTrue(myMonster.getMyPreviousPos().equals(pos));
        assertTrue(myMonster.getMyPos().equals(vel));

        myMonster.setHitPoints(1);
        myMonster.setKnockBack(true);
        Vec2 oldPos = myMonster.getMyPos();
        Vec2 oldPrev = myMonster.getMyPreviousPos();
        Vec2 vel2 = myMonster.getVelocity();
        myMonster.update();

        assertTrue(oldPos.equals(myMonster.getMyPreviousPos()));
        assertTrue((oldPos.add(vel2)).equals(myMonster.getMyPos()));
        assertTrue(oldPrev.equals(myMonster.getMyPos()));

        assertEquals(myMonster.getCurrentFrame(), 4);
    }

    /**
     * Test method for {@link Monster#movement()}
     */
    @Test
    void testMovement()
    {
        //write tests for Movement
        final Monster myMonster = new Monster("Gremlin", 3, 1, 3, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        Vec2 vel1 = myMonster.getVelocity();

        myMonster.movement();

    }

    /**
     * Test method for {@link Monster#collide()}
     */
    @Test
    void testCollide()
    {
        final Monster myMonster = new Monster("Gremlin", 3, 1, 3, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        myHero.setHitPoints(4);
        myMonster.setMyPos(new Vec2());
        myHero.setMyPos(new Vec2());
        myMonster.collide();

        assertEquals(4 - myMonster.attack(), myHero.getHitPoints());
        assertTrue(myHero.isInvincibility());


        myHero.setInvincibility(false);
        myHero.setHitPoints(1);
        myMonster.collide();

        assertTrue(myHero.getMyPos().equals(myHero.getHomePosition()));
        assertTrue(myHero.getHitPoints() == 10);
        assertTrue(myHero.getDamage() == 1);
        assertTrue(myHero.getMaxSpeed() == 5);


        myMonster.setMyPos(new Vec2(2,2));
        myHero.setMyPos(new Vec2(1,1));
        int oldHP = myHero.getHitPoints();;
        myMonster.collide();

        assertEquals(oldHP, myHero.getHitPoints());

    }

    /**
     * Test method for {@link Monster#getMonsterType()}
     */
    @Test
    void testGetMonsterType()
    {
        final Monster myMonster = new Monster("Ogre", 10, 3, 2, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        assertEquals(myMonster.getMonsterType(), "Ogre");
    }

    /**
     * Test method for {@link Monster#setRoom(Vec2)}
     */
    @Test
    void testSetRoom()
    {
        final Monster myMonster = new Monster("Ogre", 10, 3, 2, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        Vec2 room = new Vec2(8,6);
        myMonster.setRoom(new Vec2(8,6));
        assertTrue(myMonster.getMyPos().equals(Physics.getPosition((int) room.getMyX(), (int) room.getMyY(),
                (int) myMonster.getMyPos().getMyX(),(int) myMonster.getMyPos().getMyY()))); //not passing

    }

    /**
     * Test method for {@link Monster#setRoom(Vec2, int)}
     */
    @Test
    void testSetRoom2()
    {
        final Monster myMonster = new Monster("Gremlin", 10, 3, 3, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        Vec2 room = new Vec2(8,6);
        myMonster.setRoom(new Vec2(8,6), 2);
        assertTrue(myMonster.getMyPos().equals(Physics.getPosition((int) room.getMyX(), (int) room.getMyY(),
                (int) myMonster.getMyPos().getMyX(),(int) myMonster.getMyPos().getMyY()))); //not passing?

    }

    /**
     * Test method for {@link Monster#toString()}
     */
    @Test
    void testToString()
    {
        final Monster myMonster = new Monster("Ogre", 10, 30, 2, new Vec2(),
                new Vec2(), new Vec2(),myEntityFactory);

        assertEquals("Monster {myMonsterType = 'Ogre', Hero status = false, myHitPoints = 10}", myMonster.toString());
    }
}