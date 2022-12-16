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
    Hero hero = (new EntityFactory()).generateHero("Mock");

    @Test
    void testIntegrationSQLConnection()
    {
        SuperMonsterDB db = new MockMonsterDB();
        assertEquals("Monster",db.createMonsterDB("Mock",hero.getMyEntityFactory()).getCharacterType());
    }

    @Test
    void testEstablishConnection()
    {
        MonsterDB db = new MonsterDB();
        assertNotEquals(null, (new MonsterDB()).establishConnection());
    }

    @Test
    void testUpdateMonsterData()
    {
        SuperMonsterDB db = new MockMonsterDB();
        assertEquals("Monster",db.createMonsterDB("Mock",hero.getMyEntityFactory()).getCharacterType());
    }

    @Test
    void testWormGeneration()
    {
        SuperMonsterDB db = new MockMonsterDB();
        assertEquals(null,db.createWormDB(new Vec2(),null));
    }
}
