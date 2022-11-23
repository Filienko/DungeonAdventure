package MVC.Model.UnitTests;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Saver.Saver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaverTest
{
    @Test
    void testSerialization()
    {
        var entityFactory = new EntityFactory();
        var dungeon = entityFactory.generateDungeon();
        dungeon.setHero(new Thief());
        System.out.println(dungeon.getRooms().size());
        dungeon.getRooms().add(new Room());
        dungeon.getRooms().add(new Room());
        System.out.println(dungeon.getRooms().size());
        var caretaker = new Saver();
        caretaker.saveTheGame(dungeon);
        dungeon.setHero(new Priestess());
        dungeon.getRooms().add(new Room());
        dungeon.getRooms().add(new Room());
        dungeon.getRooms().add(new Room());
        dungeon.getRooms().add(new Room());
        dungeon = caretaker.loadTheGame();
        System.out.println(dungeon.getRooms().size());
        System.out.println(dungeon.getRooms().size());
    }

    @Test
    void testMemento() throws CloneNotSupportedException
    {
        var entityFactory = new EntityFactory();
        var dungeon = entityFactory.generateDungeon();
        dungeon.getRooms().add(new Room());
        var caretaker = new Saver();
        caretaker.saveStateDungeon(dungeon);
        assertEquals(1, caretaker.getSavedStates().size());
        dungeon.getRooms().add(new Room());
        dungeon.getRooms().add(new Room());
        caretaker.saveStateDungeon(dungeon);
        assertEquals(2, caretaker.getSavedStates().size());
        dungeon.getRooms().add(new Room());
        assertEquals(20, dungeon.getRooms().size());
        caretaker.restoreLastStateDungeon(dungeon);
        assertEquals(19, dungeon.getRooms().size());
        caretaker.restoreNthStateDungeon(dungeon,1);
        assertEquals(17, dungeon.getRooms().size());
    }
}