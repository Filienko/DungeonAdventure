package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.Physics.Vec2;
import java.util.*;
import java.util.Random;

public class Room implements Cloneable
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
     * Boolean that tells if the Room contains lava
     */
    private boolean myLava;

    /**
     * Boolean that tells if the Room is the Exit of the Dungeon.
     */
    private boolean myExit;

    /**
     * The Items that the Room contains.
     */
    private final StringBuilder myItems;

    /**
     * The Monsters that the Room contains.
     */
    private final StringBuilder myMonsters;

    /**
     * The location of the Room in the Dungeon.
     */
    private Vec2 myLocation;

    /**
     * Room constructor that creates a Room that is not the Entrance or Exit and that has no Items.
     */
    public Room()
    {
        myNumber = 0;
        myEntrance = false;
        myExit = false;
        myLocation = new Vec2();
        myItems = new StringBuilder();
        myMonsters = new StringBuilder();
    }

    /**
     * Room constructor that creates a Room that is not the Entrance or Exit and that has no Items and assigns location;
     */
    public Room(final int theNumber, Vec2 theLocation)
    {
        myNumber = theNumber;
        myEntrance = false;
        myExit = false;
        myLocation = theLocation;
        myItems = new StringBuilder();
        myMonsters = new StringBuilder();
    }

    /**
     * Room constructor that sets all of its attributes based on its parameters.
     * @param theEntrance Boolean that tells if the Room is the Entrance to the Dungeon.
     * @param theExit Boolean that tells if the Room is the Exit to the Dungeon.
     * @param theItems The Items that the Room contains.
     * @param theLocation The location of the Room in the Dungeon.
     */
    public Room(final boolean theEntrance, final boolean theExit,
                final StringBuilder theMonsters, final StringBuilder theItems, final Vec2 theLocation)
    {
        //how to guard against both being entered as true ?
        myExit = theExit;
        myEntrance = theEntrance;
        myLocation = theLocation;
        myMonsters = theMonsters;
        myItems = theItems;
    }


    /**
     * Populates the rooms with random items.
     */
    public void populateTheRoom(boolean theTest)
    {
        Random random = new Random();
        populatePotions(random,1);
        if(theTest)
        {
            populateLava(0.09);
            populatePotions(new Random(), 1);
        }
        else
        {
            populateLava(random.nextDouble());
        }

        if (!myLocation.equals(new Vec2(0, 0)))
        {
            populateMonsters(1);
        }
    }

    public void populateMonsters(final int theN)
    {
        var ran = new Random().nextDouble();
        for (int j = 0; j < theN; j++)
        {
            if(ran <= 0.25)
            {
                for (int i = 0; i < 4; i++)
                {
                    myMonsters.append("rat,");
                }
            }
            else if(ran <= 0.50)
            {
                for (int i = 0; i < 2; i++)
                {
                    myMonsters.append("gremlin,");
                }
            }
            else if(ran <= 0.75)
            {
                myMonsters.append("knight,");
            }
            else
            {
                myMonsters.append("ogre,");
            }
        }
    }

    public void populateLava(double theChance)
    {
        if(theChance < 0.10)
        {
            setLava(true);
        }
    }

    public void populateBomb(double theChance)
    {
        if(theChance < 0.10)
        {
            addItem("bomb");
        }
    }



    public void populatePotions(final Random theRandom, int theNumber)
    {
        for (int i = 0; i < theNumber; i++)
        {
            var randD = theRandom.nextDouble();
            if (randD < 0.25)
            {
                addItem("healthPotion");
            }
            else if (randD < 0.5)
            {
                addItem("attackPotion");
            }
            else if (randD < 0.76)
            {
                addItem("speedPotion");
            }
        }
    }

    /**
     * This method returns a boolean that tells if the Room is the Entrance to the Dungeon.
     * @return True if the Room is the Entrance, false if it is not the Entrance.
     */
    public boolean isTheEntrance()
    {
        return myEntrance;
    }

    /**
     * This method allows you to set the Entrance status of the Room (cannot be set to true if myExit is set to true).
     * @param theEntrance Boolean that determines whether the Room is the Entrance to the Dungeon.
     */
    public Room setEntranceStatus(final boolean theEntrance)
    {
        if (!myExit)
        {
            myEntrance = theEntrance;
        }
        return this;
    }

    /**
     * This method returns a boolean that tells if the Room is the Exit to the Dungeon.
     * @return True if the Room is the Exit, false if it is not the Exit.
     */
    public boolean isTheExit()
    {
        return myExit;
    }

    /**
     * This method allows you to set the Exit status of the Room (cannot be set to true if myEntrance is set to true).
     * @param theExit Boolean that determines whether the Room is the Entrance to the Dungeon.
     */
    public Room setExitStatus(final boolean theExit)
    {
        if (!myEntrance)
        {
            myExit = theExit;
        }
        return this;
    }

    /**
     * This method retrieves the Item in the Room.
     * @return The Item the Room contains.
     */
    public StringBuilder getItems() { return myItems; }

    /**
     * This method sets the Items in the Room.
     * @param theItems The Items to be contained in the Room.
     */
    public void setItems (List<Item> theItems)
    {
        myItems.delete(0, myItems.length());

        for (var item:theItems)
        {
            myItems.append(item);
        }
    }

    /**
     * This method adds the Items in the Room.
     * @param theItems The Items to be contained in the Room.
     */
    public void addItems (List<Item> theItems)
    {
        for (var item:theItems)
        {
            myItems.append(item.getType()).append(",");
        }
    }

    /**
     * This method sets the Item in the Room.
     * @param theItem The Item to be contained in the Room.
     */
    public void addItem (Item theItem)
    {
        if(theItem.getType().contentEquals("pillar"))
        {
            myItems.append(((Pillar) theItem).getName()).append(",");
        }
        else
        {
            myItems.append(theItem.getType()).append(",");
        }
    }

    /**
     * This method sets the Item in the Room.
     * @param theItem The Item to be contained in the Room.
     */
    public void addItem (String theItem)
    {
        myItems.append(theItem).append(",");
    }
    /**
     * This method removes all Items from the Room.
     */
    public void clearRoom () {myItems.delete(0, myItems.length()); }

    /**
     * This method retrieves the location of the Room in the Dungeon.
     * @return The location of the Room.
     */
    public Vec2 getLocation() { return myLocation; }

    /**
     * This method sets the location of the Room in the Dungeon.
     * @param theLocation The new location of the Room.
     */
    public void setLocation(final Vec2 theLocation) { myLocation = theLocation; }

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

    /**
     * Method to set if the Room has lava.
     * @param isLava signifies that the Room has lava.
     */
    public void setLava(final boolean isLava) { myLava = isLava; }

    /**
     * @return whether the Room has lava.
     */
    public boolean isLava() { return myLava; }

    public StringBuilder getMonsters()
    {
        return myMonsters;
    }

    public void setMonsters(final List<Monster> theMonsters)
    {
        for (var monster:theMonsters)
        {
            myMonsters.append(monster.getMonsterType()).append(",");
        }
    }

    protected Room clone() throws CloneNotSupportedException
    {
        Room s = (Room) super.clone();
        return s;
    }
}