package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.Healable;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Priestess extends Hero implements Healable
{
    /**
     * Maximum number of hit points the Priestess can heal.
     */
    private static final int MY_MAX_HEAL = 2;

    /**
     * Priestess constructor that calls its parent constructor to initialize the Priestess's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, agility, position, and velocity.
     */
    public Priestess()
    {
        super("Priestess", "Priestess", 10, 1,  5, new Vec2(), new Vec2());
    }

    /**
     * Priestess overloaded constructor that calls its parent constructor to initialize the Priestess's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, agility, position, and velocity.
     * @param theName The Priestess's name.
     * @param thePos The Priestess's location.
     */
    public Priestess(final String theName, final Vec2 thePos)
    {
        super(theName,"Priestess", 10, 1, 5, thePos, new Vec2());
    }

    /**
     * This is the Priestess's special skill. It allows the Priestess to heal another Hero by utilizing the heal method
     * from the Healable interface. The maximum number of hit points the Priestess can restore is determined by
     * MY_MAX_HEAL.
     * @param theHero The Hero the Priestess is healing.
     * @return The number of the Hero's hit points that the Priestess restored.
     */
    public int healHero(final Hero theHero)
    {
        return heal(theHero, MY_MAX_HEAL);
    }

    /**
     * This method calls the Healing on the Priestess.
     */
    @Override
    public void special()
    {
       healHero(this);
    }
}