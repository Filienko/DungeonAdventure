package MVC.Model.UnitTests;

import MVC.Model.DB.SQLConnection;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class DBTest
{
    @Test
void testIntegrationSQLConnection()
{
    SQLConnection db = new SQLConnection("Ogre");
    assertEquals("Ogre",db.getCharacterType());
    db = new SQLConnection("Gremlin");
    assertEquals("Gremlin",db.getCharacterType());
    db = new SQLConnection("Elf");
    assertEquals("Elf",db.getCharacterType());
}

    @Test
    void testEstablishConnection()
    {
        assertNotEquals(null, (new SQLConnection("Ogre")).establishConnection());
        assertNotEquals(null, (new SQLConnection("Gremlin")).establishConnection());
        assertNotEquals(null, (new SQLConnection("Elf")).establishConnection());
    }

    @Test
    void testUpdateMonsterData()
    {
        SQLConnection db = new SQLConnection("Ogre");
        db.updateMonsterData(2);
        assertEquals("Gremlin",db.getCharacterType());
        db = new SQLConnection("Ogre");
        db.updateMonsterData(3);
        assertEquals("Elf",db.getCharacterType());
        db = new SQLConnection("Ogre");
        db.updateMonsterData(1);
        assertEquals("Ogre",db.getCharacterType());
    }
}
