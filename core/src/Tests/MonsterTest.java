package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MonsterTest
{

    /**
     * Test method for Monster's constructor (uses Ogre statistics).
     */
    @Test
    void testMonsterConstructorOgre()
    {
        final Monster myMonster = new Monster("Ogre", 200, 60, 2, new Vec2(), new Vec2());

        assertEquals("Ogre", myMonster.getMyCharacterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(200, myMonster.getHitPoints());
        assertEquals(2, myMonster.getMaxSpeed());
        assertEquals(60, myMonster.getDamage());
    }

    /**
     * Test method for Monster's constructor (uses Gremlin statistics).
     */
    @Test
    void testMonsterConstructorGremlin()
    {
        final Monster myMonster = new Monster("Gremlin", 70, 15, 5, new Vec2(), new Vec2());

        assertEquals("Gremlin", myMonster.getMyCharacterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(70, myMonster.getHitPoints());
        assertEquals(5, myMonster.getMaxSpeed());
        assertEquals(15, myMonster.getDamage());
    }

    /**
     * Test method for Monster's constructor (uses Skeleton statistics).
     */
    @Test
    void testMonsterConstructorSkeleton()
    {
        final Monster myMonster = new Monster("Skeleton", 100, 30, 3, new Vec2(), new Vec2());

        assertEquals("Skeleton", myMonster.getMyCharacterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(100, myMonster.getHitPoints());
        assertEquals(3, myMonster.getMaxSpeed());
        assertEquals(30, myMonster.getDamage());
    }

    /**
     * Test method for {@link Monster#attack()}
     */
    @Test
    void testAttack()
    {
        //finish attack() method and then write these tests
    }

    /**
     * Test method for {@link Monster#toString()}
     */
    @Test
    void testToString()
    {
        final Monster myMonster = new Monster("Ogre", 200, 30, 2, new Vec2(), new Vec2());

        assertEquals("Monster {myCharacterType = Ogre, \n Hero status = false, \n myHitPoints = 200 }", myMonster.toString());
    }
}