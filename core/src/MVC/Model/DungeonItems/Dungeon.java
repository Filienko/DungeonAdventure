package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.Physics.Vec2;

import java.util.List;

public class Dungeon
{

    //DUNGEON KEEPS TRACK OF THE LOCATION OF THE HERO
    //DUNGEON KNOWS ONLY GENERAL ENTRANCE/EXIT LOGIC
    //ROOM KNOWS SPECIFIC

    private final Room[][] myDungeon;

    private final Hero myHero;

    private Vec2 myHeroPosition;

    private List<Room> myRooms;

    private int myNumberOfRooms;

    public Dungeon()
    {
        myDungeon = new Room[3][3];
        myHero = new Warrior("Brave Warrior",new Vec2());
        myHeroPosition = new Vec2();
        myRooms = EntityFactory.generateRooms(9);
        myNumberOfRooms = getNumberOfRooms();
    }

    public Dungeon(final Room[][] theDungeon)
    {
        myDungeon = theDungeon;
        myHero = new Warrior("Brave Warrior",new Vec2());
        myHeroPosition = new Vec2();
        myNumberOfRooms = getNumberOfRooms();
        myRooms = EntityFactory.generateRooms(myNumberOfRooms);
    }

    public Dungeon(final Room[][] theDungeon, final Hero theHero, final Vec2 theHeroPosition)
    {
        myDungeon = theDungeon;
        myHero = theHero;
        myHeroPosition = theHeroPosition;
        myNumberOfRooms = getNumberOfRooms();
        myRooms = EntityFactory.generateRooms(myNumberOfRooms);
    }

    private int getNumberOfRooms()
    {
        int size = 0;
        for (var row:myDungeon)
        {
            for (var col: row)
            {
                size++;
            }
        }
        return size;
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