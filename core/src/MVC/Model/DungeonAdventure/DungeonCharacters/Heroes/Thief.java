package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Thief extends Hero
{
    /**
     * Chance of being hidden when entering a room.
     */
    private static final double MY_HIDDEN_CHANCE = 0.4;

    private final EntityFactory myEntityFactory;

    /**
     * The Thief's hidden status.
     */
    private boolean myHiddenStatus = false;

    /**
     * Thief constructor that calls its parent constructor to initialize the Thief's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity, and sets its hidden status to false.
     */
    public Thief(final EntityFactory theEntityFactory)
    {
        super("Thief", "Thief",10, 1, 5, new Vec2(), new Vec2(), theEntityFactory);
        myEntityFactory = theEntityFactory;
    }

    /**
     * Thief overloaded constructor that calls its parent constructor to initialize the Thief's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity, and sets its hidden status to false.
     */
    public Thief(final String theName, final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        super(theName, "Thief",10, 1,  5, thePos, new Vec2(), theEntityFactory);
        myEntityFactory = theEntityFactory;
    }

    /**
     * Thief's attack behavior. The Thief's hidden status is set to true and then the Thief is given
     * the chance to do a surprise attack (40% chance of success) where the Thief performs an attack and then is given the
     * chance to do another attack. The Thief has additional 40% chance of performing a simple attack.
     *
     * @param theOpponent The DungeonCharacter the Thief is attacking.
     * @param theDamageArea --
     * @return The amount of damage done to theOpponent's hit point count.
     */
    @Override
    public int attack(final DungeonCharacter theOpponent, final Vec2 theDamageArea)
    {
        //when they enter a room they have chance to surprise attack - chance to start out hidden, if they are hidden,
        //they get the chance to surprise attack. otherwise, they do a regular attack.

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
                damage = special(theOpponent);
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
     * This method is the Thief's special skill.
     * @param theOpponent The DungeonCharacter the Thief is attacking.
     * @return The amount of damage done to theOpponent's hit point count.
     */
    public int special(final DungeonCharacter theOpponent)
    {
        //Thief gets an attack (super.attack()) and an extra turn (attack())

        return super.attack(theOpponent, super.getWeapon().getBoundingBox()) +
                attack(theOpponent, super.getWeapon().getBoundingBox());
    }

    /**
     * This method retrieves the Thief's hidden status.
     * @return Thief's hidden status - true if hidden, false if visible.
     */
    public boolean getHiddenStatus()
    {
        return myHiddenStatus;
    }

    /**
     * This method sets the Thief's hidden status.
     * @param theStatus Thief's new hidden status - true if hidden, false if visible.
     */
    public void setHiddenStatus(final boolean theStatus)
    {
        myHiddenStatus = theStatus;
    }
}