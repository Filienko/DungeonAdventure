package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Saver.Saver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SaverTest
{
    //Cannot test utilizing JUNIT because of Maven's repository management
    @Test
    void prepareFile()
    {
        var game = new EntityFactory();
        var caretaker = new Saver();
        caretaker.saveTheGame(game);
        game.generateMockHero();
        var before = game.getEntities().size();
        game = caretaker.loadTheGame();
        var after = game.getEntities().size();
        assertNotEquals(before,after);
        assertEquals(0,after);
    }
}