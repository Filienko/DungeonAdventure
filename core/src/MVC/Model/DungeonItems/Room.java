package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

//TODO: discuss container Class
public class Room
{
    /**
     * Boolean that tells if the Room is the Entrance of the Dungeon.
     */
    private boolean myEntrance;

    /**
     * Boolean that tells if the Room is the Exit of the Dungeon.
     */
    private boolean myExit;

    /**
     * List of the Doors that the Room has (North, South, East, West).
     */
    private final List<Door> myDoors;

    /**
     * Boolean that tells if the Room contains a special item (Potion, Pillar, Pit, etc).
     */
    private boolean myRoomHasItem;

    /**
     * The Item that the Room contains.
     */
    private List<Item> myItems;

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
        this.myDoors = new ArrayList<Door>();
        this.myItems = new ArrayList<Item>();
        this.myRoomHasItem = false;
        this.myLocation = new Vec2();
        this.myHeroPresent = false;
    }

    /**
     * Room constructor that creates a Room that is not the Entrance or Exit and that has no Items.
     */
    public Room(boolean theEntrance)
    {
        myItems = new ArrayList<Item>();
        if(theEntrance)
        {
            this.myEntrance = true;
            this.myExit = false;

        } else {
            this.myEntrance = false;
            this.myExit = true;
            myItems.add((new Exit()).getInstance());
        }
        this.myDoors = new ArrayList<Door>();
        this.myRoomHasItem = false;
        this.myLocation = new Vec2();
        this.myHeroPresent = false;
    }
    /**
     * Room constructor that sets all of its attributes based on its parameters.
     * @param theEntrance Boolean that tells if the Room is the Entrance to the Dungeon.
     * @param theExit Boolean that tells if the Room is the Exit to the Dungeon.
     * @param theDoors List of the Doors to the Room.
     * @param theRoomHasItem Boolean that tells if the Room contains an Item.
     * @param theItems The Items that the Room contains.
     * @param theLocation The location of the Room in the Dungeon.
     */
    public Room(final boolean theEntrance, final boolean theExit, final List<Door> theDoors, boolean theRoomHasItem,
                final List<Item> theItems, final Vec2 theLocation)
    {
        //how to guard against both being entered as true ?
        this.myExit = theExit;
        this.myEntrance = theEntrance;
        this.myDoors = theDoors;
        this.myRoomHasItem = theRoomHasItem;
        this.myItems = theItems;
        this.myLocation = theLocation;
        this.myHeroPresent = false;
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
    public void setEntranceStatus(final boolean theEntrance)
    {
        if (this.myExit && theEntrance)
        {
            return;
        }
        this.myEntrance = theEntrance;
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
    public void setExitStatus(final boolean theExit) {
        if (this.myEntrance && theExit)
        {
            return;
        }
        this.myEntrance = theExit;
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
    public void setItems (List<Item> theItems) { this.myItems = theItems; this.myRoomHasItem = true; }

    /**
     * This method adds the Items in the Room.
     * @param theItems The Items to be contained in the Room.
     */
    public void addItems (List<Item> theItems) { this.myItems.addAll(theItems); this.myRoomHasItem = true; }

    /**
     * This method sets the Item in the Room.
     * @param theItem The Item to be contained in the Room.
     */
    public void addItem (Item theItem) { this.myItems.add(theItem); this.myRoomHasItem = true; };

    /**
     * This method removes all Items from the Room.
     */
    public void clearRoom () { this.myItems.clear(); this.myRoomHasItem = true; }

    /**
     * This method removes the Item from the Room.
     * @param theItem The Item to be removed from the Room.
     */
    public void removeItem (Item theItem) { this.myItems.remove(theItem); this.myRoomHasItem = true; }

    /**
     * This method returns a boolean that tells whether the Room contains an Item.
     * @return True if the Room contains an Item, false if it does not.
     */
    public boolean getItemStatus() { return this.myRoomHasItem; }

    /**
     * This method sets a boolean that tells whether the Room contains an Item.
     * @param theRoomHasItem True if the Room contains an Item, false if it does not.
     */
    public void setItemStatus(final boolean theRoomHasItem) { this.myRoomHasItem = theRoomHasItem; }

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
     * @param theEntrance the Entrance status of the room.
     */
    private void setEntrance(final boolean theEntrance)
    {
        myEntrance = theEntrance;
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
    private boolean isRoomHasItem()
    {
        return myRoomHasItem;
    }

    /**
     * @return whether Hero in the Room.
     */
    private boolean isHeroPresent()
    {
        return myHeroPresent;
    }

    /**
     * @param theHeroPresent define whether Hero would be present in the room.
     */
    private void setHeroPresent(final boolean theHeroPresent)
    {
        myHeroPresent = theHeroPresent;
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
            switch (Item.getType()) {
                case "Potion":
                    room.append("Potion");
                    break;
                case "Pillar":
                    room.append("Potion");
                    break;
                case "Pit":
                    room.append("Potion");
                    break;
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