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
        final Monster myMonster = new Monster("Ogre", 200, 60, 2, new Vec2(), new Vec2(), myHero, myEntityFactory);

        assertEquals("Ogre", myMonster.getCharacterType());
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
        final Monster myMonster = new Monster("Gremlin", 70, 15, 5, new Vec2(), new Vec2(), myHero, myEntityFactory);

        assertEquals("Gremlin", myMonster.getCharacterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(70, myMonster.getHitPoints());
        assertEquals(5, myMonster.getMaxSpeed());
        assertEquals(15, myMonster.getDamage());
    }


    /**
     * Test method for   //why wont link work?
     */
    @Test
    void testAttack()
    {
        final Monster myMonster = new Monster("Skeleton", 100, 30, 3, new Vec2(), new Vec2(), myHero, myEntityFactory);
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
        final Monster myMonster = new Monster("Ogre", 200, 30, 2, new Vec2(), new Vec2(),myHero, myEntityFactory);

        assertEquals("Monster {myCharacterType = Ogre, \n Hero status = false, \n myHitPoints = 200 }", myMonster.toString());
    }
}