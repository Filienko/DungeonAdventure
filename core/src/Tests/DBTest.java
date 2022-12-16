package Tests;

import MVC.Model.DB.MockMonsterDB;
import MVC.Model.DB.MonsterDB;
import MVC.Model.DB.SuperMonsterDB;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DBTest
{
    /**
     * Mock hero Associated with the Database
     */
    Hero hero = (new EntityFactory()).generateHero("Mock");

    /**
     * Test method for the mock SQL connection and mock monster return.
     */
    @Test
    void testIntegrationSQLConnection()
    {
        SuperMonsterDB db = new MockMonsterDB();
        assertEquals("Monster",db.createMonsterDB("Mock",hero.getMyEntityFactory()).getCharacterType());
    }

    /**
     * Test method for true establishing of SQlite connection and database setup.
     */
    @Test
    void testEstablishConnection()
    {
        MonsterDB db = new MonsterDB();
        assertNotEquals(null, (new MonsterDB()).establishConnection());
    }

    /**
     * Test method for the mock monster return.
     */
    @Test
    void testUpdateMonsterData()
    {
        SuperMonsterDB db = new MockMonsterDB();
        assertEquals("Monster",db.createMonsterDB("Mock",hero.getMyEntityFactory()).getCharacterType());
    }

    /**
     * Test method for the stab worm return.
     */
    @Test
    void testWormGeneration()
    {
        SuperMonsterDB db = new MockMonsterDB();
        assertEquals(null,db.createWormDB(new Vec2(),null));
    }
}
