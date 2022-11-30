package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.DungeonUtils.Graph;
import MVC.Model.Physics.Vec2;
import MVC.View.Assets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory.generatePillars;

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
        myRooms = generateRooms(myDimension);
        myDungeon = generateDungeonFromRooms(myRooms,myDimension);
    }

    public Dungeon(final Hero theHero, final int theDimension)
    {
        if(theDimension<=2)
        {
            System.out.println("Minimum Dimension for the dungeon is 3 by 3. We are setting up the 4 by 4 maze");
            myDimension = 4;
        }
        else
        {
            myDimension = theDimension;
        }
        myHero = theHero;
        myRooms = generateRooms(myDimension);
        myDungeon = generateDungeonFromRooms(myRooms,myDimension);
    }

    public Dungeon(final Room[][] theDungeon)
    {
        myDungeon = theDungeon;
        myHero = new Warrior("Brave Warrior",new Vec2());
        myDimension = myDungeon.length;
        myRooms = generateRooms(myDimension);
    }

    public Dungeon(final Room[][] theDungeon, final Hero theHero, final Vec2 theHeroPosition)
    {
        myDungeon = theDungeon;
        myHero = theHero;
        myDimension = myDungeon.length;
        myRooms = generateRooms(myDimension);
    }

    private Dungeon(final Room[][] theDungeon, final Hero theHero, final List<Room> theRooms, final int theDimension)
    {
        if(theDimension<=2)
        {
            System.out.println("Minimum Dimension for the dungeon is 3 by 3. We are setting up the 4 by 4 maze");
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
     * @return sets the Rooms' entrances directions and positions according to the map generated by the Kruskal
     */
    public Room[][] generateDungeonFromRooms(List<Room> theRooms, int theDimension)
    {
        var edges = Graph.generateMaze(theDimension);
        System.out.println(edges.size());
        var offset = theDimension+2;
        int src;
        int dest;
        for (int i = 0; i < edges.size()-1; i++)
        {
            src = edges.get(i).getSrc()-offset;
            dest = edges.get(i).getDest()-offset;

            if(src==dest-1)
            {
                for (int j = 0; j < theRooms.size()-1; j++)
                {
                    if(theRooms.get(i).getNumber()==src)
                    {
                        theRooms.get(i).setW(true);
                    }
                    else if (theRooms.get(i).getNumber()==dest)
                    {
                        theRooms.get(i).setE(true);
                    }
                }
            }
            else if (src==dest+1)
            {
                for (int j = 0; j < theRooms.size()-1; j++)
                {
                    if(theRooms.get(i).getNumber()==src)
                    {
                        theRooms.get(i).setE(true);
                    }
                    else if (theRooms.get(i).getNumber()==dest)
                    {
                        theRooms.get(i).setW(true);
                    }
                }
            }
            else if (src==dest-offset)
            {
                for (int j = 0; j < theRooms.size()-1; j++)
                {
                    if(theRooms.get(i).getNumber()==src)
                    {
                        theRooms.get(i).setS(true);
                    }
                    else if (theRooms.get(i).getNumber()==dest)
                    {
                        theRooms.get(i).setN(true);
                    }
                }
            }
            else if(src==dest+offset)
            {
                for (int j = 0; j < theRooms.size()-1; j++)
                {
                    if(theRooms.get(i).getNumber()==src)
                    {
                        theRooms.get(i).setN(true);
                    }
                    else if (theRooms.get(i).getNumber()==dest)
                    {
                        theRooms.get(i).setS(true);
                    }
                }
            }
        }

        var answer = new Room[(theDimension)][(theDimension)];

        for (var room:theRooms)
        {
            room.populateTheRoom(false);
            answer[(int) room.getLocation().getMyX()][(int) room.getLocation().getMyY()] = room;
        }
        return answer;
    }

    public static ArrayList<Room> generateRooms(int n1)
    {
        var arr = new ArrayList<Room>();
        // Number of rooms + boundary rooms
        int allVertices = (int) Math.pow((Math.sqrt(n1*n1)+2),2);

        for (int i = 1; i < ((n1+2)*n1)-1; i++)
        {
            //Skip buffer rooms
            if(i % Math.sqrt(allVertices) == Math.sqrt(allVertices)-1 || i % Math.sqrt(allVertices) == 0)
            {
                continue;
            }

            //Account for the buffer offset
            int row = i / (n1 + 2);
            int col = (i % (n1 + 2)) - 1;
            arr.add(new Room(i, new Vec2(row,col)));
        }

        var pillars = generatePillars();
        for (int i = 0; i < 4; i++)
        {
            System.out.println(pillars.get(i).getType());
            var room = arr.get(new Random().nextInt(1,arr.size()));
            room.addItem(pillars.get(i));
        }

        arr.get(0).setEntranceStatus(true);
        arr.get(new Random().nextInt(1,arr.size())).setExitStatus(true).addItem(new Exit());

        return arr;
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

    public void setHero(final Hero theHero)
    {
        myHero = theHero;
    }

    public void setRooms(final List<Room> theRooms)
    {
        myRooms = theRooms;
    }

    public Memento saveToMemento() throws CloneNotSupportedException
    {
        var factory = new EntityFactory();
        return new Memento(this.myDungeon, (factory.generateHero(this.myHero.getCharacterType())),
                copyRooms(this.myRooms), this.myDimension);
    }

    private List<Room> copyRooms(final List<Room> theRooms) throws CloneNotSupportedException
    {
        ArrayList<Room> answer = new ArrayList<>();
        for (var room:theRooms)
        {
            answer.add(room.clone());
        }
        return answer;
    }

    public void restoreFromMemento(Memento memento)
    {
        var dungeon = memento.getSavedState();
        myDungeon = dungeon.getDungeon();
        myHero = dungeon.getHero();
        myRooms = dungeon.getRooms();
        myDimension = dungeon.getDimension();
    }

    public static class Memento
    {
        private final Room[][] myDungeon;

        private final Hero myHero;

        private List<Room> myRooms;

        private int myDimension;

        public Memento(final Room[][] theDungeon, final Hero theHero, final List<Room> theRooms, final int theDimension)
        {
           myDimension =theDimension;
            myDungeon = theDungeon;
            myHero = theHero;
            myRooms = theRooms;
        }

        private Memento getSavedState()
        {
            return new Memento(myDungeon,myHero,myRooms,myDimension);
        }

        private Room[][] getDungeon()
        {
            return myDungeon;
        }

        private Hero getHero()
        {
            return myHero;
        }

        private List<Room> getRooms()
        {
            return myRooms;
        }

        private int getDimension()
        {
            return myDimension;
        }
    }
}