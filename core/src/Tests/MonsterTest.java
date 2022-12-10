package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest
{
    EntityFactory myEntityFactory = new EntityFactory();
    Hero myHero = new Warrior(myEntityFactory);
    /**
     * Test method for Monster's constructor (uses Ogre statistics).
     */
    @Test
    void testMonsterConstructorOgre()
    {
        final Monster myMonster = new Monster("Ogre", 5, 2, 2, new Vec2(),
                new Vec2(), myHero, new Vec2(),myEntityFactory);

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
                new Vec2(), myHero, new Vec2(),myEntityFactory);

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
                new Vec2(), myHero, new Vec2(),myEntityFactory);

        assertEquals(myMonster.attack(), 1);
    }

    /**
     * Test method for {@link Monster#destroy()}
     */
    @Test
    void testDestroy()
    {
        final Monster myMonster = new Monster("Gremlin", 3, 1, 3, new Vec2(),
                new Vec2(), myHero, new Vec2(),myEntityFactory);

        myMonster.destroy();

        assertFalse(myMonster.getActiveStatus());

        //write tests for destroy, theres more to it
    }

    /**
     * Test method for {@link Monster#update()}
     */
    @Test
    void testUpdate()
    {
        //write tests for update
    }

    /**
     * Test method for {@link Monster#movement()}
     */
    @Test
    void testMovement()
    {
        //write tests for Movement
    }

    /**
     * Test method for {@link Monster#collide()}
     */
    @Test
    void testCollide()
    {
        //write tests for collide
    }

    /**
     * Test method for {@link Monster#getMonsterType()}
     */
    @Test
    void testGetMonsterType()
    {
        final Monster myMonster = new Monster("Ogre", 10, 30, 2, new Vec2(),
                new Vec2(), myHero, new Vec2(),myEntityFactory);

        assertEquals(myMonster.getMonsterType(), "Ogre");
    }

    /**
     * Test method for {@link Monster#setRoom(Vec2)}
     */
    @Test
    void testSetRoom()
    {
        final Monster myMonster = new Monster("Ogre", 10, 30, 2, new Vec2(),
                new Vec2(), myHero, new Vec2(),myEntityFactory);

        Vec2 room = new Vec2(8,6);
        myMonster.setRoom(new Vec2(8,6));
        assertTrue(myMonster.getMyPos().equals(Physics.getPosition((int) room.getMyX(), (int) room.getMyY(),
                (int) myMonster.getMyPos().getMyX(),(int) myMonster.getMyPos().getMyY()))); //review this

    }

    /**
     * Test method for {@link Monster#toString()}
     */
    @Test
    void testToString()
    {
        final Monster myMonster = new Monster("Ogre", 10, 30, 2, new Vec2(),
                new Vec2(), myHero, new Vec2(),myEntityFactory);

        assertEquals("Monster {myMonsterType = 'Ogre', Hero status = false, myHitPoints = 10}", myMonster.toString());
    }
}