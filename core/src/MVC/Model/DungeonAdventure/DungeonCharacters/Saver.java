package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Room;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Saver
{
    private List<Dungeon.Memento> mySavedStates = new ArrayList<>();

    public static void main(String[] args)
    {
        var entityFactory = new EntityFactory();
        var dungeon = entityFactory.generateDungeon();
        dungeon.setHero(new Thief());
        System.out.println(dungeon.getRooms().size());
        dungeon.getRooms().add(new Room());
        System.out.println(dungeon.getRooms().size());
        var caretaker = new Saver();
        caretaker.saveStateDungeon(dungeon);
        dungeon.setHero(new Priestess());
        dungeon.getRooms().add(new Room());
        System.out.println(dungeon.getRooms().size());
        dungeon = caretaker.loadTheGame();
        System.out.println(dungeon.getRooms().size());

    }
    public void saveTheGame(Dungeon theDungeon)
    {
        try
        {
            //Creating stream and writing the object
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("dungeon.da"));
            out.writeObject(theDungeon);
            out.flush();
            //closing the stream
            out.close();
            System.out.println("success");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public Dungeon loadTheGame()
    {
        Dungeon game = new Dungeon();
        try
        {
            //Creating stream to read the object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("dungeon.da"));
            game = (Dungeon) in.readObject();
            //printing the data of the serialized object
            System.out.println(game.getRooms());
            //closing the stream
            in.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return game;
    }

    public void saveStateDungeon(Dungeon theDungeon)
    {
        mySavedStates.add(theDungeon.saveToMemento());
    }

    public void restoreStateDungeon(Dungeon theDungeon)
    {
        theDungeon.restoreFromMemento(mySavedStates.get(mySavedStates.size()-1));
    }
}
