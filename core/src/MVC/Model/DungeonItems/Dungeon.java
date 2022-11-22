package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonUtils.Graph;
import MVC.Model.Physics.Vec2;

import java.io.Serializable;
import java.util.List;

public class Dungeon implements Serializable
{
    //DUNGEON KEEPS TRACK OF THE LOCATION OF THE HERO
    //DUNGEON KNOWS ONLY GENERAL ROOM POSITION
    //ROOM KNOWS SPECIFIC ENTRANCE/EXIT

    private Room[][] myDungeon;

    private Hero myHero;

    private List<Room> myRooms;

    private int myDimension;

    public Dungeon()
    {
        myHero = new Warrior("Brave Warrior",new Vec2());
        myDimension = 4;
        myRooms = EntityFactory.generateRooms(myDimension);
        myDungeon = generateDungeonFromRooms(myDimension);
    }

    public Dungeon(final Hero theHero, final int theDimension)
    {
        if(theDimension<4)
        {
            System.out.println("Minimum Dimension for the dungeon is 4 by 4. We are setting up the 4 by 4 maze");
            myDimension = 4;
        }
        else
        {
            myDimension = theDimension;
        }

        myHero = theHero;
        myRooms = EntityFactory.generateRooms(myDimension);
        myDungeon = generateDungeonFromRooms(myDimension);
    }

    public Dungeon(final Room[][] theDungeon)
    {
        myDungeon = theDungeon;
        myHero = new Warrior("Brave Warrior",new Vec2());
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

    private Dungeon(final Room[][] theDungeon, final Hero theHero, final List<Room> theRooms, final int theDimension)
    {
        if(theDimension<4)
        {
            System.out.println("Minimum Dimension for the dungeon is 4 by 4. We are setting up the 4 by 4 maze");
            myDimension = 4;
        }
        else
        {
            myDimension = theDimension;
        }
        myDungeon = theDungeon;
        myHero = theHero;
        myRooms = theRooms;
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

        var answer = new Room[(theDimension)][(theDimension)];

        for (var room:myRooms)
        {
            answer[(int) room.getLocation().getMyX()][(int) room.getLocation().getMyX()] = room;
            System.out.println("Room "+room.getNumber());
        }
        return answer;
    }

    public Room[][] getDungeon()
    {
        return myDungeon;
    }

    public Hero getHero()
    {
        return myHero;
    }

    public List<Room> getRooms()
    {
        return myRooms;
    }

    public int getDimension()
    {
        return myDimension;
    }


    public void setDungeon(final Room[][] theDungeon)
    {
        myDungeon = theDungeon;
    }

    public void setHero(final Hero theHero)
    {
        myHero = theHero;
    }

    public void setRooms(final List<Room> theRooms)
    {
        myRooms = theRooms;
    }

    public void setDimension(final int theDimension)
    {
        myDimension = theDimension;
    }

    public Memento saveToMemento()
    {

        return new Memento(this.myDungeon, this.myHero, this.myRooms, this.myDimension);
    }

    public void restoreFromMemento(Memento memento)
    {
        var dungeon = memento.getSavedState();
        this.myDungeon = dungeon.getDungeon();
        this.myHero = dungeon.getHero();
        this.myRooms = dungeon.getRooms();
        this.myDimension = dungeon.getDimension();
    }

    public static class Memento
    {
        private final Room[][] myDungeon;

        private final Hero myHero;

        private List<Room> myRooms;

        private int myDimension;

        public Memento(final Room[][] theDungeon, final Hero theHero, final List<Room> theRooms, final int theDimension)
        {
            this.myDimension =theDimension;
            this.myDungeon = theDungeon;
            this.myHero = theHero;
            this.myRooms = theRooms;
        }

        private Dungeon getSavedState()
        {
            return new Dungeon(myDungeon,myHero,myRooms,myDimension);
        }
    }
}