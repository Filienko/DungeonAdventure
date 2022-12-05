package Tests;

import MVC.Model.DB.MonsterDB;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class DBTest
{
    Hero hero = new Warrior();

    @Test
    void testIntegrationSQLConnection()
    {
        MonsterDB db = new MonsterDB();
        assertEquals("Ogre",db.createMonsterDB("Ogre",hero).getCharacterType());
        assertEquals("Gremlin",db.createMonsterDB("Gremlin",hero).getCharacterType());
        assertEquals("Elf",db.createMonsterDB("Elf",hero).getCharacterType());
        assertEquals("Swarm of Rats",db.createMonsterDB("Rats",hero).getCharacterType());
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
        assertEquals("Ogre",db.createMonster(1,hero).getCharacterType());
        assertEquals("Gremlin",db.createMonster(2,hero).getCharacterType());
        assertEquals("Elf",db.createMonster(3,hero).getCharacterType());
        assertEquals("Swarm of Rats",db.createMonster(4,hero).getCharacterType());
    }
}
