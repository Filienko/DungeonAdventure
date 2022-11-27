package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.Physics.Vec2;


import java.util.List;
import java.util.Random;

//TODO: discuss container Class
public class Room extends Entity
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
    private List<Door> myDoors;

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
    private List<Item> myItems;

    /**
     * The Monsters that the Room contains.
     */
    private List<Monster> myMonsters;

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
        super(new Vec2(),new Vec2());
        this.myEntrance = false;
        this.myExit = false;
        this.myRoomHasItems = false;
        this.myLocation = new Vec2();
        this.myHeroPresent = false;
        populateTheRoom();
    }

    /**
     * Room constructor that creates a Room that is not the Entrance or Exit and that has no Items and assigns location;
     */
    public Room(final int theNumber, Vec2 theLocation)
    {
        super(theLocation,new Vec2());
        this.myNumber = theNumber;
        this.myEntrance = false;
        this.myExit = false;
        this.myRoomHasItems = false;
        this.myLocation = theLocation;
        this.myHeroPresent = false;
        this.myRoomHasMonsters = true;
        populateTheRoom();
    }

    /**
     * Room constructor that creates a Room that is the Entrance or Exit and that has no Items, at the given location.
     */
    public Room(boolean theEntrance, int theNumber, Vec2 theLocation)
    {
        super(theLocation,new Vec2());
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
            myItems.add((new Exit(super.getMyBoundingBox())).getInstance(new Vec2(new Random().nextInt(0, (int) super.getMyBoundingBox().getMyX()),
                    new Random().nextInt(0, (int) super.getMyBoundingBox().getMyY()))));
        }
        this.myNumber = theNumber;
        this.myRoomHasItems = true;
        this.myRoomHasMonsters = true;
        this.myLocation = theLocation;
        populateTheRoom();
    }

    /**
     * Room constructor that sets all of its attributes based on its parameters.
     * @param theEntrance Boolean that tells if the Room is the Entrance to the Dungeon.
     * @param theExit Boolean that tells if the Room is the Exit to the Dungeon.
     * @param theDoors List of the Doors to the Room.
     * @param theRoomHasItem Boolean that tells if the Room contains an Item.
     * @param theRoomHasMonsters
     * @param theItems The Items that the Room contains.
     * @param theLocation The location of the Room in the Dungeon.
     */
    public Room(final boolean theEntrance, final boolean theExit, final List<Door> theDoors, boolean theRoomHasItem,
                final boolean theRoomHasMonsters, final List<Item> theItems, final Vec2 theLocation)
    {
        super(theLocation,new Vec2());
        //how to guard against both being entered as true ?
        this.myExit = theExit;
        this.myEntrance = theEntrance;
        this.myDoors = theDoors;
        this.myRoomHasItems = theRoomHasItem;
        this.myRoomHasMonsters = theRoomHasMonsters;
        this.myItems = theItems;
        this.myLocation = theLocation;
        this.myHeroPresent = false;
        populateTheRoom();
    }
    /**
     * Populates the rooms with random items.
     */
    public void populateTheRoom()
    {
        Random random = new Random();
        populatePotions(random);
        populatePit(random);
        populateDoors(random);
        populateMonsters();
    }

    private void populateMonsters()
    {
        myRoomHasMonsters = true;
        myMonsters.addAll(EntityFactory.generateMonsters(1));
    }

    private void populateDoors(final Random theRandom)
    {
        if(myE){myDoors.add(new Door(false));}
        if(myN){myDoors.add(new Door(false));}
        if(myW){myDoors.add(new Door(false));}
        if(myS){myDoors.add(new Door(false));}
    }

    private void populatePit(final Random theRandom)
    {
        if(theRandom.nextDouble() < 0.10)
        {
            this.myItems.add(new Pit(new Vec2(theRandom.nextInt(0, (int) super.getMyBoundingBox().getMyX()),
                theRandom.nextInt(0, (int) super.getMyBoundingBox().getMyY()))));
        }
    }

    private void populatePotions(final Random theRandom)
    {
        if(theRandom.nextDouble() < 0.333)
        {
            this.myItems.add(new HealingPotion(theRandom.nextInt(15,46),new Vec2(theRandom.nextInt(0,
                    (int) super.getMyBoundingBox().getMyX()), theRandom.nextInt(0,
                    (int) super.getMyBoundingBox().getMyY()))));
        }
        else if(theRandom.nextDouble() < 0.67)
        {
            this.myItems.add(new AttackPotion(theRandom.nextInt(15,46),new Vec2(theRandom.nextInt(0,
                    (int) super.getMyBoundingBox().getMyX()), theRandom.nextInt(0,
                    (int) super.getMyBoundingBox().getMyY()))));
        } else
        {
            this.myItems.add(new SpeedPotion(theRandom.nextInt(15,46),new Vec2(theRandom.nextInt(0,
                    (int) super.getMyBoundingBox().getMyX()), theRandom.nextInt(0,
                    (int) super.getMyBoundingBox().getMyY()))));
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
    public void setItems (List<Item> theItems) { this.myItems = theItems; this.myRoomHasItems = myItems.size() < 1 ? false : true;; }

    /**
     * This method adds the Items in the Room.
     * @param theItems The Items to be contained in the Room.
     */
    public void addItems (List<Item> theItems) { this.myItems.addAll(theItems); this.myRoomHasItems = true; }

    /**
     * This method sets the Item in the Room.
     * @param theItem The Item to be contained in the Room.
     */
    public void addItem (Item theItem) { this.myItems.add(theItem); this.myRoomHasItems = true; };

    /**
     * This method removes all Items from the Room.
     */
    public void clearRoom () { this.myItems.clear(); this.myRoomHasItems = false; }

    /**
     * This method removes the Item from the Room.
     * @param theItem The Item to be removed from the Room.
     */
    public void removeItem (Item theItem) { this.myItems.remove(theItem); this.myRoomHasItems = myItems.size() < 1 ? false : true; }

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
    private boolean isRoomHasItems()
    {
        return myRoomHasItems;
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
     * @return the number of the Room.
     */
    int getNumber()
    {
        return myNumber;
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
    void setE(final boolean theE)
    {
        myE = theE;
    }
    /**
     * @param theN signifies that Room has North Entry.
     */
    void setN(final boolean theN)
    {
        myN = theN;
    }
    /**
     * @param theW signifies that Room has West Entry.
     */
    void setW(final boolean theW)
    {
        myW = theW;
    }
    /**
     * @param theS signifies that Room has South Entry.
     */
    void setS(final boolean theS)
    {
        myS = theS;
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