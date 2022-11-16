package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Warrior extends Hero
{
    /**
     * The Warrior's name.
     */
    private String myName;

    /**
     * Warrior constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity.
     */
    public Warrior()
    {
        super("Warrior","Warrior",  125, 35, 60, 4, new Vec2(), new Vec2());
    }

    /**
     * Warrior constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity.
     * @param theName The Warrior's name.
     * @param thePos The Warrior's location.
     */
    public Warrior(final String theName, final Vec2 thePos)
    {
        super(theName,"Warrior",125, 35, 60, 4, thePos,  new Vec2());
        this.myName = theName;
    }

    /**
     * The Warrior's special skill. This crushingBlow attack has a 40% chance of success, and if it is successful,
     * a damage amount between 75 and 175 is randomly generated. There is a 40% chance that a simple attack is performed.
     * @param theOpponent The DungeonCharacter the Warrior is attacking.
     * @return The amount of damage done to theOpponent's hit points.
     */
    public int crushingBlow(final DungeonCharacter theOpponent)
    {
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.4) {
            damage = attack(theOpponent, super.getWeapon().getBoundingBox());
        } else if (chance < 0.6) {
            Random rand = new Random();
            damage = rand.nextInt(75, 175);
        }
        theOpponent.applyDamage(damage);
        return damage;
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