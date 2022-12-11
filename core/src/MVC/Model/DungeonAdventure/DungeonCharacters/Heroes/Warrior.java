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
     * The Warrior's specific attack behavior. They have a 40% chance of performing their special skill
     * during this attack
     *
     * @return The amount of damage the Warrior does during their attack.
     */
    @Override
    public int damage()
    {
        var damage = 0;
        var chance = Math.random();
        if (chance < 0.4)
        {
            damage = special();
        }
        else
        {
            damage = super.damage();
        }
        return damage;
    }

    /**
     * This is the Warrior's special skill. damage amount between 1 and 3 is randomly generated.
     * @return The amount of damage the Warrior will inflict.
     */
    public int special()
    {
        Random rand = new Random();
        return rand.nextInt(getDamage(), getDamage()+3);
    }
}