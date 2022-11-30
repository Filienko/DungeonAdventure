package Tests;

import MVC.Model.DB.MonsterDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class DBTest
{
    @Test
    void testIntegrationSQLConnection()
    {
        MonsterDB db = new MonsterDB();
        assertEquals("Ogre",db.createMonsterDB("Ogre").getCharacterType());
        assertEquals("Gremlin",db.createMonsterDB("Gremlin").getCharacterType());
        assertEquals("Elf",db.createMonsterDB("Elf").getCharacterType());
        assertEquals("Swarm of Rats",db.createMonsterDB("Rats").getCharacterType());
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
        MonsterDB db = new MonsterDB();
        assertEquals("Ogre",db.createMonster(1).getCharacterType());
        assertEquals("Gremlin",db.createMonster(2).getCharacterType());
        assertEquals("Elf",db.createMonster(3).getCharacterType());
        assertEquals("Swarm of Rats",db.createMonster(4).getCharacterType());
    }
}
