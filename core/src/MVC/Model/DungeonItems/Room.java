package MVC.Model.DungeonItems;

import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;

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
    private List<Door> myDoors;

    /**
     * Boolean that tells if the Room contains a special item (Potion, Pillar, Pit, etc).
     */
    private boolean myRoomHasItem;
    /**
     * The Item that the Room contains.
     */
    private Item myItem; //there can be multiple Items in one Room, should this be a List?
    /**
     * The location of the Room in the Dungeon.
     */
    private Vec2 myLocation;

    /**
     * Room constructor that creates a Room that is not the Entrance or Exit and that has no Items.
     */
    public Room()
    {
        this.myEntrance = false;
        this.myExit = false;
        this.myDoors = new ArrayList<Door>();
        this.myRoomHasItem = false;
        this.myLocation = new Vec2();
    }

    /**
     * Room constructor that sets all of its attributes based on its parameters.
     * @param theEntrance Boolean that tells if the Room is the Entrance to the Dungeon.
     * @param theExit Boolean that tells if the Room is the Exit to the Dungeon.
     * @param theDoors List of the Doors to the Room.
     * @param theRoomHasItem Boolean that tells if the Room contains an Item.
     * @param theItem The Item that the Room contains.
     * @param theLocation The location of the Room in the Dungeon.
     */
    public Room(final boolean theEntrance, final boolean theExit, final List<Door> theDoors, final boolean theRoomHasItem,
                final Item theItem, final Vec2 theLocation)
    {
        //how to guard against both being entered as true ?
        this.myExit = theExit;
        this.myEntrance = theEntrance;
        this.myDoors = theDoors;
        this.myRoomHasItem = theRoomHasItem;
        this.myItem = theItem;
        this.myLocation = theLocation;
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
    public Item getItem() { return this.myItem; }

    /**
     * This method sets the Item in the Room.
     * @param theItem The Item to be contained in the Room.
     */
    public void setItem(final Item theItem) { this.myItem = theItem; this.myRoomHasItem = true; }

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
     * This method sets the Doors to the Room to the list of Doors passed to the method.
     * @param theDoors A list of Doors.
     */
    public void setDoors(final List<Door> theDoors)
    {
        this.myDoors = theDoors;
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
        switch (myItem) { //fix this!!!!
            case myItem.getType() == Potion:
                room.append("Potion");
                break;
            case myItem.getType() == Pillar:
                room.append(myItem.getMyName().substring(0,1));
                break;
            case myItem.getType() == Pit:
                room.append("X");
                break;
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