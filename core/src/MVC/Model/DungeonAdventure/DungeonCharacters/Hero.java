package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Vec2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hero extends DungeonCharacter implements ICollidable
{
    /**
     * Factory that generated Hero.
     */
    private final EntityFactory myEntityFactory;

    /**
     * Random number generator used to generate the Hero's hit point count.
     */
    private static final Random RANDOM_GENERATOR = new Random();

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
    private final String myCharacterType;

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

    private long myCurrentFrame;
    private long initiatedFrame;

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

        myName = theName;
        myCharacterType = theCharacterType;
        myPotions = new ArrayList<>();
        myPillars = 0;
        setHitPoints(5);
        myEntityFactory = theEntityFactory;

        myUpStatus = false;
        myDownStatus = false;
        myLeftStatus = false;
        myRightStatus = false;
        myAttackStatus = false;

        myCurrentFrame = 0;
        initiatedFrame = 0;

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
        myWeapon = theWeapon;
    }

    public abstract int special(final DungeonCharacter theOpponent);

    @Override
    public void update()
    {
        movement();

//        if(myAttackStatus) {   //is this how it should be formatted?
//            attack();
//        }
        myCurrentFrame++;

    }

    @Override
    public int attack()
    {
        myAttackStatus = true;
        int damage = 0;

        //change this up so that weapon applies damage

        if (myWeapon == null)
        {
            myWeapon = new EntityFactory().generateSword();
            damage = super.attack(); //weapon applies damage?
        }

        long delay = 15;
        if (myCurrentFrame <= initiatedFrame + delay)
        {
            initiatedFrame++;
        } else
        {
            myAttackStatus = false; //delays setting to false for 15 frames
        }

        return damage;
    }

    public void movement()
    {
        Vec2 newVelocity = new Vec2();

        if (myUpStatus && !myDownStatus)
        {
            newVelocity.setMyY(1 * getMaxSpeed());

        } else if (!myUpStatus && myDownStatus)
        {
            newVelocity.setMyX(0);
            newVelocity.setMyY(-1 * getMaxSpeed());

        } else if (!myUpStatus && !myDownStatus)
        {
            newVelocity.setMyY(0);
        }

        if (myLeftStatus && !myRightStatus)
        {
            newVelocity.setMyX(-1 * getMaxSpeed());

        } else if (!myLeftStatus && myRightStatus)
        {
            newVelocity.setMyX(1 * getMaxSpeed());

        } else if (!myLeftStatus && !myRightStatus)
        {
            newVelocity.setMyX(0);
        }

        super.setVelocity(newVelocity);

        //super.movement();

    }

    //changed visibility of the following potions/pillars methods to public (were protected)
    /**
     * This method sets the Potions in the Hero's inventory.
     * @param thePotions Potions to be put into inventory.
     */
    public void setPotions(final List<Item> thePotions)
    {
        myPotions = thePotions;
    }

    /**
     * This method adds a Potion to the Hero's inventory.
     * @param thePotion Potion to be added into inventory.
     */
    public void addPotion(final Item thePotion)
    {
        myPotions.add(thePotion);
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

//    /**
//     * This method adds a Pillar to the Hero's inventory.
//     * @param thePillar Pillar to be added into inventory.
//     */
//    public void addPillar(final Pillar thePillar)
//    {
//        myPillars++;
//    }

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
        myName = theName;
    }

    public void setUp(final boolean theUpStatus) { myUpStatus = theUpStatus; }

    public void setDown(final boolean theDownStatus) { myDownStatus = theDownStatus; }

    public void setLeft(final boolean theLeftStatus) { myLeftStatus = theLeftStatus; }

    public void setRight(final boolean theRightStatus) { myRightStatus = theRightStatus; }

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
                "myCharacterType + " + myCharacterType +
                ", Hero status = " + MY_HERO_STATUS +
                ", Potions = '" + myPotions.toString() + '\'' +
                ", Pillars = " + myPillars +
                '}';
    }

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
     * Analyzes whether the collision occurred between two objects and performs certain associated logic
     */
    @Override
    public void collide()
    {

    }

    public Vec2 getFacing() { return myFacing; }

    public boolean getAttackStatus() { return myAttackStatus; }
}