package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Warrior extends Hero
{

    /**
     * Warrior constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity.
     */
    public Warrior(final EntityFactory theEntityFactory)
    {
        super("Warrior","Warrior",  10, 1, 5, new Vec2(), new Vec2(), theEntityFactory);
    }

    /**
     * Warrior overloaded constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, and velocity.
     * @param theName The Warrior's name.
     * @param thePos The Warrior's location.
     */
    public Warrior(final String theName, final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        super(theName,"Warrior",10, 1,  5, thePos, new Vec2(), theEntityFactory);
    }

    /**
     * This method allows the Warrior to attack another DungeonCharacter. There is a 40% chance that a simple attack is
     * performed. The Warrior also has a special skill called Crushing Blow, which also has a 40% chance of success.
     *
     * @return The amount of damage done to theOpponent's hit points.
     */
    @Override
    public int attack()
    {
        var damage = super.attack();
        var chance = Math.random();
        if (chance < 0.4)
        {
            damage = special();
        }

        return damage;
    }

    /**
     * This is the Warrior's special skill. damage amount between 75 and 175 is randomly generated.
     * @return The amount of damage done to theOpponent's hit points.
     */
    public int special()
    {
        Random rand = new Random();
        return rand.nextInt(getDamage(), getDamage()+3);
    }
}