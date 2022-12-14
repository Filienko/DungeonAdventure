package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Thief extends Hero
{

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
    public int damage()
    {
        var damage = 0;

        var chance = Math.random();
        if (chance < 0.25)
        {
            damage = special();
        }
        else
        {
            damage = super.damage();
            setMyKnockbackPower(4);
            setMyKnockbackLength(10);
        }

        return damage;
    }

    /**
     * This method is the Thief's special skill.
     * @return The amount of damage done to theOpponent's hit point count.
     */
    public int special()
    {
        setUsingSpecial(true);
        setMyKnockbackPower(0);
        setMyKnockbackLength(60);

        return getDamage()+5;
    }
}