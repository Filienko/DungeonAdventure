package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;
import java.util.ArrayList;
import java.util.List;

public abstract class Hero extends DungeonCharacter
{
    /**
     * A hero status that is hardcoded to true.
     */
    private static final boolean MY_HERO_STATUS = true;

    /**
     * The Hero's name that is input by the user.
     */
    private String myName;

    /**
     * The specific Hero type.
     */
    private String myCharacterType; //should this be changed back to final?

    /**
     * The Hero's weapon.
     */
    private Sword myWeapon;

    /**
     * A list of all the Potions in the Hero's inventory.
     */
    private List<Item> myPotions;

    /**
     * Amount of Pillars in the Hero's inventory.
     */
    private int myPillars;

    private boolean myUpStatus;
    private boolean myDownStatus;
    private boolean myLeftStatus;
    private boolean myRightStatus;
    private boolean myAttackStatus;
    private long myInitiatedFrame;
    private long myAttackFrameEnd;
    private Vec2 myFacing;
    /**
     * Hero constructor that calls its parent constructor to initialize the Hero's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, velocity, Potions in inventory, and Pillars in
     * inventory.
     *
     * @param theName The Hero's name, determined by the user.
     * @param theCharacterType The specific type of Hero it is.
     * @param theHitPoints The Hero's hit points (health).
     * @param theDamage The amount of damage the Hero can inflict.
     * @param theMaxSpeed The Hero's maximum speed.
     * @param thePos The Hero's location.
     * @param theVelocity The Hero's velocity.
     */
    public Hero(final String theName, final String theCharacterType, final int theHitPoints, final int theDamage,
                final int theMaxSpeed, final Vec2 thePos, final Vec2 theVelocity, final EntityFactory theEntityFactory)
    {
        super("Hero", MY_HERO_STATUS, theHitPoints, theDamage, theMaxSpeed,
                new Vec2(48, 48), thePos, theVelocity, theEntityFactory);

        setName(theName);
        setCharacterType(theCharacterType);
        myPotions = new ArrayList<>();
        myPillars = 0;
        myUpStatus = false;
        myDownStatus = false;
        myLeftStatus = false;
        myRightStatus = false;
        myInitiatedFrame = 0;
        myAttackFrameEnd = 0;
        myFacing = new Vec2(0, 1);
    }

    /**
     * This method moves the Hero to the specified coordinates.
     * @param theCoordinates Coordinates that determine the Hero's new location.
     */
    public void moveHero(final Vec2 theCoordinates)
    {
        setMyPos(theCoordinates);
    }

    public Sword getWeapon()
    {
        return myWeapon;
    }

    public void setWeapon(final Sword theWeapon)
    {
        if (theWeapon != null)
        {
            myWeapon = theWeapon;
        }
    }

    public abstract int special();

    @Override
    public void update()
    {
        super.update();
        if(getCurrentFrame()==myAttackFrameEnd)
        {
            myAttackStatus = false;
        }
        incrementCurrentFrame();
    }

    @Override
    public int attack()
    {
        myAttackStatus = true;
        return 1;
    }

    public void movement()
    {
        Vec2 newVelocity = new Vec2();

        if (myUpStatus && !myDownStatus)
        {
            newVelocity.setMyY(getMaxSpeed());
        }
        else if (!myUpStatus && myDownStatus)
        {
            newVelocity.setMyY(-1 * getMaxSpeed());
        }

        if (myLeftStatus && !myRightStatus)
        {
            newVelocity.setMyX(-1 * getMaxSpeed());
        }
        else if (!myLeftStatus && myRightStatus)
        {
            newVelocity.setMyX(getMaxSpeed());
        }

        if (newVelocity.getMyX() != 0 && newVelocity.getMyY() != 0)
        {
            newVelocity = newVelocity.multiply(newVelocity.quickInverseMagnitude());
            newVelocity = newVelocity.multiply(getMaxSpeed());
        }

        setVelocity(newVelocity);
        facing();
        setMyPreviousPos(getMyPos());
        updateMyPos(getVelocity());
    }

    private void facing()
    {
        if (getVelocity().getMyX() != 0)
        {
            if (getVelocity().getMyX() > 0)
            {
                myFacing.setMyX(1);
            }
            else
            {
                myFacing.setMyX(-1);
            }
            myFacing.setMyY(0);
        }
        else if (getVelocity().getMyY() != 0)
        {
            if (getVelocity().getMyY() > 0)
            {
                myFacing.setMyY(1);
            }
            else
            {
                myFacing.setMyY(-1);
            }
            myFacing.setMyX(0);
        }
    }


    /**
     * This method sets the Potions in the Hero's inventory.
     * @param thePotions Potions to be put into inventory.
     */
    public void setPotions(final List<Item> thePotions)
    {
        if (thePotions != null && thePotions.size() > 0)
        {
            myPotions = thePotions;
        }
    }

    /**
     * This method adds a Potion to the Hero's inventory.
     * @param thePotion Potion to be added into inventory.
     */
    public void addPotion(final Item thePotion)
    {
        if (thePotion != null)
        {
            myPotions.add(thePotion);
        }
    }

    /**
     * This method retrieves the Potions in the Hero's inventory.
     * @return Potions in inventory.
     */
    public List<Item> getPotions()
    {
        return myPotions;
    }

    /**
     * This method sets the Pillars in the Hero's inventory.
     */
    public void incrementPillars()
    {
        myPillars++;
    }

    //changed visibility for testing!!! reevaluate if this should be protected (was originally) or public
    /**
     * This method retrieves the Pillars in the Hero's inventory.
     * @return Pillars in inventory.
     */
    public int getPillars()
    {
        return myPillars;
    }

    /**
     * This method retrieves the Hero's name, as determined by the user.
     * @return The Hero's name in String form.
     */
    public String getName()
    {
        return myName;
    }

    /**
     * This method sets the Hero's name.
     * @param theName The Hero's new name.
     */
    private void setName(final String theName)
    {
        if (theName != null)
        {
            myName = theName;
        }
    }

    public boolean isAttackStatus()
    {
        return myAttackStatus;
    }

    public void setAttackStatus(final boolean theAttackStatus)
    {
        myAttackStatus = theAttackStatus;
    }

    public void setUp(final boolean theUpStatus) { myUpStatus = theUpStatus; }

    public void setDown(final boolean theDownStatus) { myDownStatus = theDownStatus; }

    public void setLeft(final boolean theLeftStatus) { myLeftStatus = theLeftStatus; }

    public void setRight(final boolean theRightStatus) { myRightStatus = theRightStatus; }

    /**
     * This method returns a String describing the type of Hero it is.
     * @return The specific type of Hero it is.
     */
    @Override
    public String getCharacterType()
    {
        return myCharacterType;
    }

    /**
     * This method tells that the Hero is a Hero.
     * @return The Hero's hero status, which is always true.
     */
    @Override
    public boolean getHeroStatus()
    {
        return MY_HERO_STATUS;
    }

    /**
     * Method that returns details about the Hero.
     * @return A String that lists the Hero's name, what type of Hero it is,
     * the Hero's hero status, and the Potions and Pillars in its inventory.
     */
    @Override
    public String toString()
    {
        return "Name: " + myName +
                " {" +
                "myCharacterType = " + myCharacterType +
                ", Hero status = " + MY_HERO_STATUS +
                ", Potions = '" + myPotions.toString() + '\'' +
                ", Pillars = " + myPillars +
                '}';
    }

    public Vec2 getFacing() { return myFacing; }

    public void setFacing(final Vec2 theFacing)
    {
        if (theFacing != null)
        {
            myFacing = theFacing;
        }
    }

    public boolean getAttackStatus() { return myAttackStatus; }

}