package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
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

    private Vec2 myHeroPosition;

    private List<Room> myRooms;

    private int myDimension;

    public Dungeon()
    {
        myHero = new Warrior("Brave Warrior",new Vec2());
        myHeroPosition = new Vec2();
        myDimension = 3;
        myRooms = EntityFactory.generateRooms(myDimension);
        myDungeon = generateDungeonFromRooms(myDimension);
    }

    Room[][] generateDungeonFromRooms(int theDimension)
    {
        Random random = new Random();
        var entrances = new ArrayList<String>();
        var adjacentRooms = new ArrayList<Room>();
        //Eliminate all the impossible Doors
        for (var i=0;i<myRooms.size(); i++)
        {
            Room room = myRooms.get(i);

            //Listing all possible directions
            entrances.add("E");
            entrances.add("S");
            entrances.add("W");
            entrances.add("N");

            if(room.getLocation().getMyX()==0)
            {
                entrances.remove("N");
            }
            else if(room.getLocation().getMyX()==theDimension)
            {
                entrances.remove("S");
            }

            if(room.getLocation().getMyY()==0)
            {
                entrances.remove("W");
            }
            else if(room.getLocation().getMyY()==theDimension)
            {
                entrances.remove("E");
            }

            adjacentRooms.add(myRooms.get(i + 1));
            adjacentRooms.add(myRooms.get(i - 1));
            adjacentRooms.add(myRooms.get(i + myDimension));
            adjacentRooms.add(myRooms.get(i - myDimension));

            //Setting the appropriate flags all the surrounding rooms
            setAdjacent(room.setEntrances(entrances), i);
            entrances.clear();
            adjacentRooms.clear();
        }

        var answer = new Room[(theDimension)][(theDimension)];
        for (var room:myRooms)
        {
            answer[(int) room.getLocation().getMyX()][(int) room.getLocation().getMyX()] = room;
        }
        return answer;
    }

    public final void setAdjacent(final ArrayList<String> theDirection, int theI)
    {
        for (int i = 0; i < theDirection.size(); i++)
        {
            if(theDirection.get(i).contentEquals("S"))
            {
                myRooms.get(theI + myDimension).setN(true);
            }
            else if (theDirection.get(i).contentEquals("W"))
            {
                myRooms.get(theI - 1).setE(true);
            }
            else if (theDirection.get(i).contentEquals("E"))
            {
                myRooms.get(theI + 1).setW(true);
            }
            else if (theDirection.get(i).contentEquals("N"))
            {
                myRooms.get(theI - myDimension).setS(true);
            }
        }
    }

    public Dungeon(final Room[][] theDungeon)
    {
        myDungeon = theDungeon;
        myHero = new Warrior("Brave Warrior",new Vec2());
        myHeroPosition = new Vec2();
        myDimension = myDungeon.length;
        myRooms = EntityFactory.generateRooms(myDimension);
    }

    public Dungeon(final Room[][] theDungeon, final Hero theHero, final Vec2 theHeroPosition)
    {
        myDungeon = theDungeon;
        myHero = theHero;
        myHeroPosition = theHeroPosition;
        myDimension = myDungeon.length;
        myRooms = EntityFactory.generateRooms(myDimension);
    }

    public Vec2 getHeroCoordinates()
    {
        return myHeroPosition;
    }

    public void setHeroCoordinates(final Vec2 thePosition)
    {
        myHeroPosition.setMyX(thePosition.getMyX());
        myHeroPosition.setMyY(thePosition.getMyY());
    }
}