package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Thief extends Hero
{
    /**
     * Chance of being hidden when entering a room.
     */
    private static final double MY_HIDDEN_CHANCE = 0.4;

    /**
     * The Thief's name
     */
    private String myName;

    /**
     * The Thief's hidden status.
     */
    private boolean myHiddenStatus = false;



    /**
     * Thief constructor that calls its parent constructor to initialize the Thief's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity, and sets its hidden status to false.
     */
    public Thief()
    {
        super("Thief", "Thief", 75, 20, 40, 6,  new Vec2(), new Vec2());
    }

    /**
     * Thief overloaded constructor that calls its parent constructor to initialize the Thief's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity, and sets its hidden status to false.
     */
    public Thief(final String theName, final Vec2 thePos)
    {
        super(theName, "Thief",75, 20, 40, 6,  thePos, new Vec2());
        this.myName = theName;
    }

    /**
     *
     * @param theOpponent The DungeonCharacter the Thief is attacking.
     * @param theDamageArea --
     * @return The amount of damage done to theOpponent's hit point count.
     */
    @Override
    public int attack(final DungeonCharacter theOpponent, final Vec2 theDamageArea)
    {
        double chance = Math.random();
        if (chance < MY_HIDDEN_CHANCE)
        {
            myHiddenStatus = true;
        }

        int damage = 0;
        if (myHiddenStatus)
        {
            chance = Math.random();
            if (chance < 0.4)
            {
                damage = surpriseAttack(theOpponent);
            }
            myHiddenStatus = false;
        } else
        {
            damage = super.attack(theOpponent, super.getWeapon().getBoundingBox());
        }
        theOpponent.applyDamage(damage);
        return damage;
    }

    /**
     * This method is the Thief's special skill. The Thief's hidden status is set to true and then the Thief is given
     * the chance to do a surprise attack (40% chance of success) where the Thief performs an attack and then is given the
     * chance to do another surprise attack. The Thief has a 20% chance that no attack is rendered, and an additional 40%
     * chance of just performing a simple attack.
     * @param theOpponent The DungeonCharacter the Thief is attacking.
     * @return The amount of damage done to theOpponent's hit point count.
     */
    public int surpriseAttack(final DungeonCharacter theOpponent)
    {
        //when they enter a room they have chance to surprise attack - chance to start out hidden, if they are, they get the chance to surprise attack
        // otherwise, they do a regular attack

        return super.attack(theOpponent, super.getWeapon().getBoundingBox()) + super.attack(theOpponent, super.getWeapon().getBoundingBox());
    }

    /**
     * This method retrieves the Thief's hidden status.
     * @return Thief's hidden status - true if hidden, false if visible.
     */
    public boolean getHiddenStatus()
    {
        return this.myHiddenStatus;
    }

    /**
     * This method sets the Thief's hidden status.
     * @param theStatus Thief's new hidden status - true if hidden, false if visible.
     */
    public void setHiddenStatus(final boolean theStatus)
    {
        this.myHiddenStatus = theStatus;
    }

//    @Override
//    public String getName()
//    {
//        return myName;
//    }

    /**
     * This method sets the Thief's name.
     * @param theName The Thief's new name.
     */
    private void setName(final String theName)
    {
        this.myName = theName;
    }

}