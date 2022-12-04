package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonUtils.Graph;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon
{
    //DUNGEON KEEPS TRACK OF THE LOCATION OF THE HERO
    //DUNGEON KNOWS ONLY GENERAL ENTRANCE/EXIT LOGIC
    //ROOM KNOWS SPECIFIC

    private final Room[][] myDungeon;

    private final Hero myHero;

    private List<Room> myRooms;

    private int myDimension;

    public Dungeon()
    {
        myHero = new Warrior("Brave Warrior",new Vec2(), new EntityFactory()); //added new EntityFactory
        myDimension = 3;
        myRooms = EntityFactory.generateRooms(myDimension);
        myDungeon = generateDungeonFromRooms(myDimension);
    }

    public Dungeon(final Room[][] theDungeon)
    {
        myDungeon = theDungeon;
        myHero = new Warrior("Brave Warrior",new Vec2(), new EntityFactory()); //added new EntityFactory
        myDimension = myDungeon.length;
        myRooms = EntityFactory.generateRooms(myDimension);
    }

    public Dungeon(final Room[][] theDungeon, final Hero theHero, final Vec2 theHeroPosition)
    {
        myDungeon = theDungeon;
        myHero = theHero;
        myDimension = myDungeon.length;
        myRooms = EntityFactory.generateRooms(myDimension);
    }

    /**
     * @return sets the Rooms' entrances directions according to the map
     */
    public Room[][] generateDungeonFromRooms(int theDimension)
    {
        var edges = Graph.generateMaze(theDimension);
        var offset = theDimension+2;
        int src;
        int dest;
        for (int i = 0; i < edges.size()-1; i++)
        {
            src = edges.get(i).getSrc()-offset;
            dest = edges.get(i).getDest()-offset;

            if(src==dest-1)
            {
                for (int j = 0; j < myRooms.size()-1; j++)
                {
                    if(myRooms.get(i).getNumber()==src)
                    {
                        myRooms.get(i).setW(true);
                    }
                    else if (myRooms.get(i).getNumber()==dest)
                    {
                        myRooms.get(i).setE(true);
                    }
                }

            }
            else if (src==dest+1)
            {
                for (int j = 0; j < myRooms.size()-1; j++)
                {
                    if(myRooms.get(i).getNumber()==src)
                    {
                        myRooms.get(i).setE(true);
                    }
                    else if (myRooms.get(i).getNumber()==dest)
                    {
                        myRooms.get(i).setW(true);
                    }
                }
            }
            else if (src==dest-offset)
            {
                for (int j = 0; j < myRooms.size()-1; j++)
                {
                    if(myRooms.get(i).getNumber()==src)
                    {
                        myRooms.get(i).setS(true);
                    }
                    else if (myRooms.get(i).getNumber()==dest)
                    {
                        myRooms.get(i).setN(true);
                    }
                }
            }
            else if(src==dest+offset)
            {
                for (int j = 0; j < myRooms.size()-1; j++)
                {
                    if(myRooms.get(i).getNumber()==src)
                    {
                        myRooms.get(i).setN(true);
                    }
                    else if (myRooms.get(i).getNumber()==dest)
                    {
                        myRooms.get(i).setS(true);
                    }
                }
            }

        }
        for (int i = 0; i < myRooms.get(myRooms.size()-1).getNumber(); i++)
        {
            myRooms.get(i).getNumber();
        }
        for (int i = 0; i < (theDimension+2)*(theDimension+2); i++)
        {

        }

        var answer = new Room[(theDimension)][(theDimension)];
        for (var room:myRooms)
        {
            answer[(int) room.getLocation().getMyX()][(int) room.getLocation().getMyX()] = room;
        }
        return answer;
    }
}