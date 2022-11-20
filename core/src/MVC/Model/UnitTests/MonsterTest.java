package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest
{
    //javadoc these tests!!!

    @Test
    void testMonsterConstructorOgre()
    {
        final Monster myMonster = new Monster("Ogre", 200, 30, 60, 2, new Vec2(), new Vec2());

        assertEquals("Ogre", myMonster.getMyCharacterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(200, myMonster.getHitPoints());
        assertEquals(2, myMonster.getMaxSpeed());
        assertEquals(30, myMonster.getMinDamageRange());
        assertEquals(60, myMonster.getMaxDamageRange());
    }

    @Test
    void testMonsterConstructorGremlin()
    {
        final Monster myMonster = new Monster("Gremlin", 70, 15, 30, 5, new Vec2(), new Vec2());

        assertEquals("Gremlin", myMonster.getMyCharacterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(70, myMonster.getHitPoints());
        assertEquals(5, myMonster.getMaxSpeed());
        assertEquals(15, myMonster.getMinDamageRange());
        assertEquals(30, myMonster.getMaxDamageRange());
    }

    @Test
    void testMonsterConstructorSkeleton()
    {
        final Monster myMonster = new Monster("Skeleton", 100, 30, 50, 3, new Vec2(), new Vec2());

        assertEquals("Skeleton", myMonster.getMyCharacterType());
        assertFalse(myMonster.getHeroStatus());
        assertEquals(100, myMonster.getHitPoints());
        assertEquals(3, myMonster.getMaxSpeed());
        assertEquals(30, myMonster.getMinDamageRange());
        assertEquals(50, myMonster.getMaxDamageRange());
    }

    @Test
    void testAttack()
    {
        //finish attack() method and then write these tests
    }

    @Test
    void testToString()
    {
        final Monster myMonster = new Monster("Ogre", 200, 30, 60, 2, new Vec2(), new Vec2());

        assertEquals("Monster {\n myCharacterType = Ogre, \n Hero status = false, \n myHitPoints = 200 }", myMonster.toString());
    }
}