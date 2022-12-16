package MVC.Model.Saver;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saver
{
    /**
     * @param theDungeon the entityFactory containing all of the entities associated with the dungeon
     * to save a current state of the game.
     */
    public void saveTheGame(EntityFactory theDungeon)
    {
        try
        {
            //Creating stream and writing the object
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                    new File("dungeon.ser")));
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

    /**
     * @return the entities with their associated states
     */
    public EntityFactory loadTheGame()
    {
        EntityFactory game = null;
        try
        {
            //Creating stream to read the object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    new File("dungeon.ser")));
            game = (EntityFactory) in.readObject();
            //printing the status of the serialized object
            System.out.println("Entity Factory Downloaded");
            //closing the stream
            in.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return game;
    }
}
