package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Warrior extends Hero
{
    private final EntityFactory myEntityFactory;

    /**
     * The Warrior's name.
     */
    private String myName;

    /**
     * Warrior constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity.
     */
    public Warrior(final EntityFactory theEntityFactory)
    {
        super("Warrior","Warrior",  125, 35, 60, 4, new Vec2(), new Vec2(), theEntityFactory);
        myEntityFactory = theEntityFactory;
    }

    /**
     * Warrior overloaded constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity.
     * @param theName The Warrior's name.
     * @param thePos The Warrior's location.
     */
    public Warrior(final String theName, final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        super(theName,"Warrior",125, 35, 60, 4, thePos,  new Vec2(), theEntityFactory);
        myName = theName;
        myEntityFactory = theEntityFactory;
    }

    /**
     * This method allows the Warrior to attack another DungeonCharacter. There is a 40% chance that a simple attack is
     * performed. The Warrior also has a special skill called Crushing Blow, which also has a 40% chance of success.
     * @param theOpponent The DungeonCharacter the Warrior is attacking.
     * @param theDamageArea --
     * @return The amount of damage done to theOpponent's hit points.
     */
    @Override
    public int attack(final DungeonCharacter theOpponent, final Vec2 theDamageArea)
    {
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.4) {
            damage = super.attack(theOpponent, super.getWeapon().getBoundingBox());
        } else if (chance < 0.6) {
            damage = special(theOpponent);
        }
        theOpponent.applyDamage(damage);
        return damage;
    }
    /**
     * This is the Warrior's special skill. damage amount between 75 and 175 is randomly generated.
     * @return The amount of damage done to theOpponent's hit points.
     */
    public int special(final DungeonCharacter theOpponent) //theOpponent is never used, change ?
    {
        Random rand = new Random();
        return rand.nextInt(75, 175);
    }

    @Override
    public String getName()
    {
        return myName;
    }

    /**
     * This method sets the Warrior's name.
     * @param theName The Warrior's new name.
     */
    private void setName(final String theName)
    {
        myName = theName;
    }
}