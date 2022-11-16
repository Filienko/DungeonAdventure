package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.DungeonItems.Pillar;
import MVC.Model.DungeonItems.Weapon.Attackable;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hero extends DungeonCharacter
{
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
    private Attackable myWeapon = new Sword();

    /**
     * A list of all the Potions in the Hero's inventory.
     */
    private List<Item> myHealingPotions; //Item is ok?

    /**
     * A list of all the Pillars in the Hero's inventory.
     */
    private List<Pillar> myPillars;

    /**
     * The Hero's hit points (health).
     */
    private final int myHitPoints;

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
                final int theMaxDamageRange, final int theMaxSpeed, final Vec2 thePos, final Vec2 theVelocity)
    {
        super(theCharacterType, true, theHitPoints, theMinDamageRange, theMaxDamageRange, theMaxSpeed, thePos, theVelocity);
        this.myName = theName;
        this.myCharacterType = theCharacterType;
        //this.myHeroStatus = true;
        this.myHealingPotions = new ArrayList<>();
        this.myPillars = new ArrayList<>();
        this.myHitPoints = RANDOM_GENERATOR.nextInt(75,100);
        //is hero status necessary ?? should it just be a static final field?
    }

    /**
     * This method moves the Hero to the specified coordinates.
     * @param theCoordinates Coordinates that determine the Hero's new location.
     */
    public void moveHero(final Vec2 theCoordinates)
    {
        super.setPos(theCoordinates);
    }

    public void healHero(final Hero theHero, final int theHealingHP)
    {
        theHero.setHitPoints(theHero.getHitPoints()+theHealingHP);
    }

    public Attackable getWeapon()
    {
        return myWeapon;
    }

    public void setWeapon(final Attackable theWeapon)
    {
        myWeapon = theWeapon;
    }

    /**
     * This method sets the Potions in the Hero's inventory.
     * @param thePotions Potions to be put into inventory.
     */
    protected void setPotions(final List<Item> thePotions)
    {
        this.myHealingPotions = thePotions;
    }

    /**
     * This method adds a Potion to the Hero's inventory.
     * @param thePotion Potion to be added into inventory.
     */
    protected void addPotion(Item thePotion)
    {
        this.myHealingPotions.add(thePotion);
    }

    /**
     * This method retrieves the Potions in the Hero's inventory.
     * @return Potions in inventory.
     */
    protected List<Item> getPotions()
    {
        return this.myHealingPotions;
    }

    /**
     * This method sets the Pillars in the Hero's inventory.
     * @param thePillars Pillars to be put into inventory.
     */
    protected void setPillars(final List<Pillar> thePillars)
    {
        this.myPillars = thePillars;
    }

    /**
     * This method adds a Pillar to the Hero's inventory.
     * @param thePillar Pillar to be added into inventory.
     */
    protected void addPillar(Pillar thePillar)
    {
        this.myPillars.add(thePillar);
    }

    /**
     * This method retrieves the Pillars in the Hero's inventory.
     * @return Pillars in inventory.
     */
    protected List<Pillar> getPillars()
    {
        return this.myPillars;
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
                "myCharacterType + " + myCharacterType +
                ", Hero status = " + MY_HERO_STATUS +
                ", myHealingPotions = '" + myHealingPotions.toString() + '\'' +
                ", myPillars = " + myPillars.toString() +
                '}';
    }

//    /**
//     * This method retrieves the Hero's name, as determined by the user.
//     * @return The Hero's name in String form.
//     */
//    @Override
//    public String getName()
//    {
//        return myName;
//    }

    /**
     * This method returns a String describing the type of Hero it is.
     * @return The specific type of Hero it is.
     */
    @Override
    public String getMyCharacterType()
    {
        return this.myCharacterType;
    }

    /**
     * This method tells that the Hero is a Hero.
     * @return The Hero's hero status, which is always true.
     */
    @Override
    public boolean getHeroStatus()
    {
        return this.MY_HERO_STATUS;
    }

    /**
     * This method retrieves the Hero's hit points.
     * @return The number of hit points a Hero has, represented by an int.
     */
    @Override
    public int getHitPoints()
    {
        return this.myHitPoints;
    }

}