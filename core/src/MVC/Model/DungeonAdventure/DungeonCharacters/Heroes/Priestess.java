package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Healable;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Priestess extends Hero implements Healable
{
    /**
     * The Priestess's name.
     */
    private String myName;

    /**
     * Priestess constructor that calls its parent constructor to initialize the Priestess's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, agility, position, and velocity.
     */
    public Priestess()
    {
        super("Priestess", "Priestess", 75, 25, 45,  5,  new Vec2(), new Vec2());
    }

    /**
     * Priestess constructor that calls its parent constructor to initialize the Priestess's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, agility, position, and velocity.
     * @param theName The Priestess's name.
     * @param thePos The Priestess's location.
     */
    public Priestess(final String theName, final Vec2 thePos)
    {
        super(theName,"Priestess", 75, 25, 45,  5,  thePos, new Vec2());
        this.myName = theName;
    }

    /**
     * This is the Priestess's special skill. It allows the Priestess to heal another Hero. This method implements the
     * healCharacter method from the Healable interface do accomplish this.
     * @param theHero The Hero the Priestess is healing.
     * @return The number of the Hero's hit points that the Priestess restored.
     */
    public int healCharacter(final Hero theHero)
    {
        return Healable.super.healCharacter(theHero);
    }

    public int attack(DungeonCharacter theOpponent)
    {
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.2) {
            return damage;
        } else if (chance < 0.6) {
            damage = attack(theOpponent, super.getWeapon().getBoundingBox());
        } else if (chance < 1.0) {
            damage += attack(theOpponent);
        }
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