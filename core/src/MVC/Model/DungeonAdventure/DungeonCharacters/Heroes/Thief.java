package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Thief extends Hero
{
    /**
     * The Thief's name
     */
    private String myName;

    /**
     * The Thief's hidden status.
     */
    private boolean myHiddenStatus = false;

    /**
     * Chance of being hidden when entering a room.
     */
    private double myHiddenChance = 0.4;

    /**
     * Thief constructor that calls its parent constructor to initialize the Thief's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity, and sets its hidden status to false.
     */
    public Thief()
    {
        super("Thief", "Thief", 75, 20, 40, 6,  new Vec2(), new Vec2());
    }

    /**
     * Thief constructor that calls its parent constructor to initialize the Thief's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity, and sets its hidden status to false.
     */
    public Thief(final String theName, final Vec2 thePos)
    {
        super(theName, "Thief",75, 20, 40, 6,  thePos, new Vec2());
        this.myName = theName;
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
        myHiddenStatus = true;
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.2) {
            myHiddenStatus = false;
            return damage;
        } else if (chance < 0.6) {
            myHiddenStatus = false;
            damage = attack(theOpponent, super.getWeapon().getBoundingBox());
        } else if (chance < 1.0) {
            damage = attack(theOpponent, super.getWeapon().getBoundingBox());
            damage += surpriseAttack(theOpponent);
        }
        theOpponent.applyDamage(damage);
        return damage;
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
    public void setMyHiddenStatus(final boolean theStatus)
    {
        this.myHiddenStatus = theStatus;
    }

//    @Override
//    public String getName()
//    {
//        return myName;
//    }

    private void setName(final String theName)
    {
    myName = theName;
    }

}