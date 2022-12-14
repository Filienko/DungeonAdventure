package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Healable;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

import java.util.Random;


public class Priestess extends Hero implements Healable
{
    /**
     * Maximum number of hit points the Priestess can heal.
     */
    private static final int MY_MAX_HEAL = 3;

    /**
     * Priestess constructor that calls its parent constructor to initialize the Priestess's name, character type, hero status, hit points,
     * amount of damage it can inflict, agility, position, and velocity.
     * @param theEntityFactory The Entity Factory that generated the Priestess.
     */
    public Priestess(final EntityFactory theEntityFactory)
    {
        super("Priestess", "Priestess", 10, 1,  5, new Vec2(), new Vec2(), theEntityFactory);
    }

    /**
     * Priestess overloaded constructor that calls its parent constructor to initialize the Priestess's name, character type, hero status, hit points,
     * amount of damage it can inflict, agility, position, and velocity.
     * @param theName The Priestess's name.
     * @param thePos The Priestess's position.
     * @param theEntityFactory The Entity Factory that generated the Priestess.
     */
    public Priestess(final String theName, final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        super(theName,"Priestess", 10, 1, 5, thePos, new Vec2(), theEntityFactory);
    }

    /**
     * The Priestess's specific attack behavior. She has a 5% chance of healing herself during her attack.
     * @return The amount of damage the Priestess inflicts during her attack.
     */
    @Override
    public int damage()
    {
        var damage = super.damage();

        if((new Random()).nextDouble()<0.05)
        {
            special();
        }
        return damage;
    }

    /**
     * This is the Priestess's special skill. It allows the Priestess herself by utilizing the heal method
     * from the Healable interface. The maximum number of hit points the Priestess can restore is determined by
     * MY_MAX_HEAL.
     * @return The number of hit points the Priestess restored.
     */
    public int special()
    {
        setUsingSpecial(true);

        return heal(this, MY_MAX_HEAL);
    }
}