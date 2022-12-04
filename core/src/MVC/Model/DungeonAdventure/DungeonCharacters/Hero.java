package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hero extends DungeonCharacter
{
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

    private final String myName;

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
     * A list of all the Pillars in the Hero's inventory.
     */
    private List<Pillar> myPillars;

    /**
     * The Hero's hit points (health).
     */
    private final int myHitPoints;

    private boolean myInvincibilityStatus;

    //wrote setters for these fields - are they necessary?
    boolean myUpStatus;
    boolean myDownStatus;
    boolean myLeftStatus;
    boolean myRightStatus;
    boolean myAttackStatus;

    private long myCurrentFrame;
    private long initiatedFrame;

    /**
     * Hero constructor that calls its parent constructor to initialize the Hero's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, velocity, Potions in inventory, and Pillars in
     * inventory.
     *
     * @param theName The Hero's name, determined by the user.
     * @param theCharacterType The specific type of Hero it is.
     * @param theHitPoints The Hero's hit points (health).
     * @param theMinDamageRange The minimum amount of damage the Hero can inflict.
     * @param theMaxDamageRange The maximum amount of damage the Hero can inflict.
     * @param theMaxSpeed The Hero's maximum speed.
     * @param thePos The Hero's location.
     * @param theVelocity The Hero's velocity.
     */
    public Hero(final String theName, final String theCharacterType, final int theHitPoints, final int theMinDamageRange,
                final int theMaxDamageRange, final int theMaxSpeed, final Vec2 thePos, final Vec2 theVelocity,
                final EntityFactory theEntityFactory)
    {
        super(theCharacterType, MY_HERO_STATUS, theHitPoints, theMinDamageRange, theMaxDamageRange, theMaxSpeed,
                thePos, theVelocity, theEntityFactory);
        myName = theName;
        myCharacterType = theCharacterType;
        myPotions = new ArrayList<>();
        myPillars = new ArrayList<>();
        myHitPoints = RANDOM_GENERATOR.nextInt(75,100);
        myEntityFactory = theEntityFactory;

        myUpStatus = false;
        myDownStatus = false;
        myLeftStatus = false;
        myRightStatus = false;
        myAttackStatus = false;

        myCurrentFrame = 0;
        initiatedFrame = 0;
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
    public int attack(final DungeonCharacter theOpponent, final Vec2 theDamageArea)
    {
        myAttackStatus = true;

        int damage = 0;

        //change this up so that weapon applies damage

        if (myWeapon == null)
        {
            myWeapon = EntityFactory.generateSword();
            damage = super.attack(theOpponent, getWeapon().getBoundingBox());
        }

        long delay = 15;
        if (myCurrentFrame <= initiatedFrame + delay)
        {
            initiatedFrame++;
        } else
        {
            myAttackStatus = false; //delay setting to false for 15 frames
        }

        return damage;
    }

    @Override
    public void movement()
    {
        //set velocity based on booleans passed

        //super.movement();

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

    }

    //changed visibility of the following potions/pillars methods to public (were protected)
    /**
     * This method sets the Potions in the Hero's inventory.
     * @param thePotions Potions to be put into inventory.
     */
    public void setPotions(final List<Item> thePotions) { myPotions = thePotions; }

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
     * @param thePillars Pillars to be put into inventory.
     */
    public void setPillars(final List<Pillar> thePillars)
    {
        myPillars = thePillars;
    }

    /**
     * This method adds a Pillar to the Hero's inventory.
     * @param thePillar Pillar to be added into inventory.
     */
    public void addPillar(final Pillar thePillar)
    {
        myPillars.add(thePillar);
    }

    //changed visibility for testing!!! reevaluate if this should be protected (was originally) or public
    /**
     * This method retrieves the Pillars in the Hero's inventory.
     * @return Pillars in inventory.
     */
    public List<Pillar> getPillars()
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

    public void setMyUpStatus(final boolean theUpStatus) { myUpStatus = theUpStatus; }

    public void setMyDownStatus(final boolean theDownStatus) { myDownStatus = theDownStatus; }

    public void setMyLeftStatus(final boolean theLeftStatus) { myLeftStatus = theLeftStatus; }

    public void setMyRightStatus(final boolean theRightStatus) { myRightStatus = theRightStatus; }

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
                ", Pillars = " + myPillars.toString() +
                '}';
    }

    /**
     * This method returns a String describing the type of Hero it is.
     * @return The specific type of Hero it is.
     */
    @Override
    public String getMyCharacterType()
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
     * This method retrieves the Hero's hit points.
     * @return The number of hit points a Hero has, represented by an int.
     */
    @Override
    public int getHitPoints()
    {
        return myHitPoints;
    }


}