package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Priestess extends Hero
{
    /**
     * Maximum number of hit points the Priestess can heal.
     */
    private static final int MY_MAX_HEAL = 25;

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
     * This is the Priestess's special skill. It allows the Priestess to heal another Hero.
     * @param theHero The Hero the Priestess is healing.
     * @return The number of the Hero's hit points that the Priestess restored.
     */
    public int healHero(final Hero theHero)
    {
        int healPoints;
        Random rand = new Random();
        healPoints = rand.nextInt(MY_MAX_HEAL) + 1;
        theHero.setHitPoints(theHero.getHitPoints() + healPoints);
        return healPoints;
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
//        return this.myName;
//    }

    /**
     * This method sets the Priestess's name.
     * @param theName The Priestess's new name.
     */
    private void setName(final String theName)
    {
        this.myName = theName;
    }
}