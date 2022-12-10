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
    }

    /**
     * Thief overloaded constructor that calls its parent constructor to initialize the Thief's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity, and sets its hidden status to false.
     */
    public Thief(final String theName, final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        super(theName, "Thief",10, 1,  5, thePos, new Vec2(), theEntityFactory);
    }

    /**
     * The Thief's specific attack behavior. They have a 25% chance of doing 5 extra points of damage using
     * their special skill.
     *
     * @return The amount of damage the Thief does during their attack.
     */
    @Override
    public int attack()
    {
        var damage = super.attack();
        //when they enter a room they have chance to surprise attack - chance to start out hidden, if they are hidden,
        //they get the chance to surprise attack. otherwise, they do a regular attack.

        var chance = Math.random();
        if (chance < 0.25)
        {
            damage = special();
        }

        return damage;
    }

    /**
     * This method is the Thief's special skill.
     * @return The amount of damage done to theOpponent's hit point count.
     */
    public int special()
    {
//        double chance = Math.random();
//
//        if (chance < MY_HIDDEN_CHANCE)
//        {
//            myHiddenStatus = true;
//        }
//
//        int damage = 1;
//        if (myHiddenStatus)
//        {
//            chance = Math.random();
//            if (chance < 0.8)
//            {
//                damage = 3;
//            }
//            myHiddenStatus = false;
//        }
//        else
//        {
//            damage = super.attack();
//        }
        return getDamage()+5;
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