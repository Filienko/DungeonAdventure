package MVC.Model.Saver;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saver
{
    private List<Dungeon.Memento> mySavedStates = new ArrayList<>();

    public void saveTheGame(Dungeon theDungeon)
    {
        try
        {
            //Creating stream and writing the object
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                    new File("core/src/MVC/Model/Saver/dungeon.ser")));
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
        Dungeon game = null;
        try
        {
            //Creating stream to read the object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    new File("core/src/MVC/Model/Saver/dungeon.ser")));
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

    public void saveStateDungeon(Dungeon theDungeon) throws CloneNotSupportedException
    {
        mySavedStates.add(theDungeon.saveToMemento());
    }

    public void restoreLastStateDungeon(Dungeon theDungeon)
    {
        theDungeon.restoreFromMemento(mySavedStates.get(mySavedStates.size()-1));
    }

    public void restoreNthStateDungeon(Dungeon theDungeon,int thOrder)
    {
        theDungeon.restoreFromMemento(mySavedStates.get(thOrder-1));
    }

    public List<Dungeon.Memento> getSavedStates()
    {
        return mySavedStates;
    }
}
