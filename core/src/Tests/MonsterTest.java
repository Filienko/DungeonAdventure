package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        final Monster myMonster = new Monster("Skeleton", 100, 30, 3, new Vec2(),
                new Vec2(), myHero, new Vec2(),myEntityFactory);

        //write test
    }

    @Test
    void testUpdate()
    {
        //write tests for update
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