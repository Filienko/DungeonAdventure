package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.Physics.Vec2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room implements Serializable, Cloneable
{
    /**
     * The int indicating room's number.
     */
    private int myNumber;

    /**
     * Boolean that tells if the Room is the Entrance of the Dungeon.
     */
    private boolean myEntrance;

    /**
     * Boolean that tells if the Room contains an Entrance to the East room.
     */
    private boolean myE;

    /**
     * Boolean that tells if the Room contains an Entrance to the North room.
     */
    private boolean myN;

    /**
     * Boolean that tells if the Room contains an Entrance to the West room.
     */
    private boolean myW;

    /**
     * Boolean that tells if the Room contains an Entrance to the South room.
     */
    private boolean myS;

    /**
     * Boolean that tells if the Room is the Exit of the Dungeon.
     */
    private boolean myExit;

    /**
     * List of the Doors that the Room has (North, South, East, West).
     */
    private List<Door> myDoors = new ArrayList<>();

    /**
     * Boolean that tells if the Room contains a special item (Potion, Pillar, Pit, etc).
     */
    private boolean myRoomHasItems;

    /**
     * Boolean that tells if the Room contains a Monster
     */
    private boolean myRoomHasMonsters;

    /**
     * The Items that the Room contains.
     */
    private List<Item> myItems =  new ArrayList<>();

    /**
     * The Monsters that the Room contains.
     */
    private List<Monster> myMonsters =  new ArrayList<>();

    /**
     * The location of the Room in the Dungeon.
     */
    private Vec2 myLocation;

    /**
     * The Hero in the Room.
     */
    private boolean myHeroPresent;

    /**
     * Room constructor that creates a Room that is not the Entrance or Exit and that has no Items.
     */
    public Room()
    {
        this.myEntrance = false;
        this.myExit = false;
        this.myRoomHasItems = false;
        this.myLocation = new Vec2();
        this.myHeroPresent = false;
    }

    /**
     * Room constructor that creates a Room that is not the Entrance or Exit and that has no Items and assigns location;
     */
    public Room(final int theNumber, Vec2 theLocation)
    {
        this.myNumber = theNumber;
        this.myEntrance = false;
        this.myExit = false;
        this.myRoomHasItems = false;
        this.myLocation = theLocation;
        this.myHeroPresent = false;
        this.myRoomHasMonsters = true;
    }

    /**
     * Room constructor that creates a Room that is the Entrance or Exit and that has no Items, at the given location.
     */
    public Room(boolean theEntrance, int theNumber, Vec2 theLocation)
    {
        if(theEntrance)
        {
            this.myEntrance = true;
            this.myExit = false;
            this.myHeroPresent = true;
        }
        else
        {
            this.myEntrance = false;
            this.myExit = true;
            this.myHeroPresent = false;
            myItems.add(new Exit());
        }

        this.myNumber = theNumber;
        this.myRoomHasItems = true;
        this.myRoomHasMonsters = true;
        this.myLocation = theLocation;
    }

    /**
     * Room constructor that sets all of its attributes based on its parameters.
     * @param theEntrance Boolean that tells if the Room is the Entrance to the Dungeon.
     * @param theExit Boolean that tells if the Room is the Exit to the Dungeon.
     * @param theDoors List of the Doors to the Room.
     * @param theRoomHasItem Boolean that tells if the Room contains an Item.
     * @param theRoomHasMonsters Boolean whether monsters are present in room
     * @param theItems The Items that the Room contains.
     * @param theLocation The location of the Room in the Dungeon.
     */
    public Room(final boolean theEntrance, final boolean theExit, final List<Door> theDoors, boolean theRoomHasItem,
                final boolean theRoomHasMonsters, final List<Item> theItems, final Vec2 theLocation)
    {
        //how to guard against both being entered as true ?
        this.myExit = theExit;
        this.myEntrance = theEntrance;
        this.myDoors = theDoors;
        this.myRoomHasItems = theRoomHasItem;
        this.myRoomHasMonsters = theRoomHasMonsters;
        this.myItems = theItems;
        this.myLocation = theLocation;
        this.myHeroPresent = false;
    }
    /**
     * Populates the rooms with random items.
     */
    public void populateTheRoom(boolean theTest)
    {
        Random random = new Random();
        populatePotions(random,1);
        if(theTest){
            populatePit(0.09);
        } else
        {
            populatePit(random.nextDouble());
        }
        populateDoors();
        populateMonsters();
    }

    public void populateMonsters()
    {
        myRoomHasMonsters = true;
        myMonsters.addAll(EntityFactory.generateMonsters(1));
    }

    public void populateDoors()
    {
        if(myE){myDoors.add(new Door(false));}
        if(myN){myDoors.add(new Door(false));}
        if(myW){myDoors.add(new Door(false));}
        if(myS){myDoors.add(new Door(false));}
    }

    public void populatePit(double theChance)
    {
        if(theChance < 0.10)
        {
            this.myItems.add(new Pit());
        }
    }

    public void populatePotions(final Random theRandom, int theNumber)
    {
        for (int i = 0; i < theNumber; i++)
        {
            var randD = theRandom.nextDouble();
            if (randD < 0.333)
            {
                this.myItems.add(new HealingPotion(theRandom.nextInt(15, 46)));
            }
            else if (randD < 0.67)
            {
                this.myItems.add(new AttackPotion(theRandom.nextInt(15, 46)));
            }
            else
            {
                this.myItems.add(new SpeedPotion());
            }
        }
    }

    /**
     * This method returns a boolean that tells if the Room is the Entrance to the Dungeon.
     * @return True if the Room is the Entrance, false if it is not the Entrance.
     */
    public boolean isTheEntrance()
    {
        return this.myEntrance;
    }

    /**
     * This method allows you to set the Entrance status of the Room (cannot be set to true if myExit is set to true).
     * @param theEntrance Boolean that determines whether the Room is the Entrance to the Dungeon.
     */
    public Room setEntranceStatus(final boolean theEntrance)
    {
        if (!myExit)
        {
            this.myEntrance = theEntrance;
        }
        return this;
    }

    /**
     * This method returns a boolean that tells if the Room is the Exit to the Dungeon.
     * @return True if the Room is the Exit, false if it is not the Exit.
     */
    public boolean isTheExit()
    {
        return this.myExit;
    }

    /**
     * This method allows you to set the Exit status of the Room (cannot be set to true if myEntrance is set to true).
     * @param theExit Boolean that determines whether the Room is the Entrance to the Dungeon.
     */
    public Room setExitStatus(final boolean theExit)
    {
        if (!myEntrance)
        {
            this.myExit = theExit;
        }
        return this;
    }

    /**
     * This method retrieves the Item in the Room.
     * @return The Item the Room contains.
     */
    public List<Item> getItems() { return this.myItems; }

    /**
     * This method sets the Items in the Room.
     * @param theItems The Items to be contained in the Room.
     */
    public void setItems (List<Item> theItems) { this.myItems = theItems; this.myRoomHasItems = myItems.size() >= 1; }

    /**
     * This method adds the Items in the Room.
     * @param theItems The Items to be contained in the Room.
     */
    public void addItems (List<Item> theItems) { this.myItems.addAll(theItems); this.myRoomHasItems = true; }

    /**
     * This method sets the Item in the Room.
     * @param theItem The Item to be contained in the Room.
     */
    public void addItem (Item theItem) { this.myItems.add(theItem); this.myRoomHasItems = true; }

    /**
     * This method removes all Items from the Room.
     */
    public void clearRoom () { this.myItems.clear(); this.myRoomHasItems = false; }

    /**
     * This method removes the Item from the Room.
     * @param theItem The Item to be removed from the Room.
     */
    public void removeItem (Item theItem) { this.myItems.remove(theItem); this.myRoomHasItems = myItems.size() >= 1; }

    /**
     * This method returns a boolean that tells whether the Room contains an Item.
     * @return True if the Room contains an Item, false if it does not.
     */
    public boolean getItemStatus() { return this.myRoomHasItems; }

    /**
     * This method sets a boolean that tells whether the Room contains an Item.
     * @param theRoomHasItem True if the Room contains an Item, false if it does not.
     */
    public void setItemStatus(final boolean theRoomHasItem) { this.myRoomHasItems = theRoomHasItem; }

    /**
     * This method retrieves the location of the Room in the Dungeon.
     * @return The location of the Room.
     */
    public Vec2 getLocation() { return this.myLocation; }

    /**
     * This method sets the location of the Room in the Dungeon.
     * @param theLocation The new location of the Room.
     */
    public void setLocation(final Vec2 theLocation) { this.myLocation = theLocation; }

    /**
     * This method returns a list of the Doors to the Room (North, South, East, and West).
     * @return A list of Doors.
     */
    public List<Door> getDoors() { return this.myDoors; }

    /**
     * @return whether Room is an entrance.
     */
    private boolean isEntrance()
    {
        return myEntrance;
    }

    /**
     * @return whether Room is an exit.
     */
    private boolean isExit()
    {
        return myExit;
    }

    /**
     * @param theExit the Exit status of the room.
     */
    private void setExit(final boolean theExit)
    {
        myExit = theExit;
    }

    /**
     * @return whether Rooms contains Items.
     */
    private boolean isRoomHasItems()
    {
        return myRoomHasItems;
    }

    /**
     * @return the number of the Room.
     */
    public int getNumber()
    {
        return myNumber;
    }

    /**
     * @param theNumber intended number of the Room.
     */
    public void setNumber(final int theNumber)
    {
        myNumber = theNumber;
    }

    /**
     * @return whether Room has East entry.
     */
    public boolean isE()
    {
        return myE;
    }
    /**
     * @return whether Room has North entry.
     */
    public boolean isN()
    {
        return myN;
    }
    /**
     * @return whether Room has West entry.
     */
    public boolean isW()
    {
        return myW;
    }
    /**
     * @return whether Room has South entry.
     */
    public boolean isS()
    {
        return myS;
    }
    /**
     * @param theE signifies that Room has East Entry.
     */
    public void setE(final boolean theE)
    {
        myE = theE;
    }
    /**
     * @param theN signifies that Room has North Entry.
     */
    public void setN(final boolean theN)
    {
        myN = theN;
    }
    /**
     * @param theW signifies that Room has West Entry.
     */
    public void setW(final boolean theW)
    {
        myW = theW;
    }
    /**
     * @param theS signifies that Room has South Entry.
     */
    public void setS(final boolean theS)
    {
        myS = theS;
    }

    public boolean isRoomHasMonsters()
    {
        return myRoomHasMonsters;
    }

    public void setRoomHasMonsters(final boolean theRoomHasMonsters)
    {
        myRoomHasMonsters = theRoomHasMonsters;
    }

    public List<Monster> getMonsters()
    {
        return myMonsters;
    }

    public void setMonsters(final List<Monster> theMonsters)
    {
        myMonsters = theMonsters;
    }

    protected Room clone() throws CloneNotSupportedException
    {
        return (Room) super.clone();
    }

    /**
     * This method returns String that serves as a 2D representation of the Room.
     * Doors are represented by: -,
     * Entrances are represented by: i,
     * Exits are represented by: O,
     * Potions are represented by: Potion,
     * Pillars are represented by the first letter of the Pillar: A, P, I, or E,
     * Pits are represented by: X.
     * @return A 2D representation of the Room expressed as a String.
     */
    @Override
    public String toString()
    {
        StringBuilder room = new StringBuilder();

        room.append("*-* \n | ");
        for (var Item:myItems)
        {
            switch (Item.getType())
            {
                case "Potion" -> room.append("Potion");
                case "Pillar" -> room.append("Pillar");
                case "Pit" -> room.append("Pit");
            }
        }
        if (myEntrance)
        {
            room.append("i");
        } else if (myExit)
        {
            room.append("O");
        }
        room.append("| \n *-*");

        return room.substring(0, room.length());
    }
}