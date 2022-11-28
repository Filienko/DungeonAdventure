package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.math.collision.BoundingBox;

import java.util.Random;

public abstract class DungeonCharacter extends Entity
{
    /**
     * The specific character type.
     */
    private final String myCharacterType;

    /**
     * Hero status that describes if the character is a Hero or a Monster.
     */
    private final boolean myHeroStatus;

    /**
     * The minimum amount of damage the character can inflict.
     */

    private int myMinDamageRange;

    /**
     * The maximum amount of damage the character can inflict.
     */
    private int myMaxDamageRange;

    /**
     * The character's hit points (health).
     */
    private int myHitPoints;

    /**
     * The maximum speed of the character.
     */
    private int myMaxSpeed;

    /**
     * The character's position.
     */
    private Vec2 myPos;

    /**
     * The character's velocity.
     */
    private Vec2 myVelocity;

    /**
     * DungeonCharacter constructor that initializes the Character's character type, hero status, hit points,
     * minimum and maximum damage amounts, maximum speed, and position.
     *
     * @param theCharacterType The character's type.
     * @param theHeroStatus The character's hero status (true means Hero, false means Monster).
     * @param theHitPoints The character's hit points.
     * @param theMinDamageRange The minimum amount of damage the character can administer.
     * @param theMaxDamageRange The minimum amount of damage the character can administer.
     * @param theMaxSpeed The character's maximum speed
     * @param thePos The character's location.
     * @param theVelocity The character's velocity.
     */
    DungeonCharacter(final String theCharacterType, final boolean theHeroStatus, final int theHitPoints, final int theMinDamageRange, final int theMaxDamageRange,
                     final int theMaxSpeed, final Vec2 thePos, final Vec2 theVelocity)
    {
        super(new Vec2(), new Vec2());
        this.myCharacterType = theCharacterType;
        this.myHeroStatus = theHeroStatus;

        this.myMinDamageRange = theMinDamageRange;
        this.myHitPoints = theHitPoints;
        this.myMaxDamageRange = theMaxDamageRange;
        this.myMaxSpeed = theMaxSpeed;
        this.myPos = thePos;
        this.myVelocity = theVelocity;
    }

    protected int attack(final DungeonCharacter theOpponent, final Vec2 theDamageArea)
    {
        //while (Physics.isInside(theDamageArea, theOpponent)) {
        //  -- attack the opponent
        //}
        int damage = 1; //damage should be generated between min/max damage range ? may need to bring down min/max


        //apply invincibility for Heros for 30 frames after each hit from Monsters


        //get overlap between opponenets
        //if overlap.x > 0 and overlap.y > 0, this means an overlap

        theOpponent.applyDamage(damage);

        return damage;
    }

    /**
     * This method subtracts hit points from a character's total hit point count.
     * @param theDamage The number of hit points to be subtracted.
     */
    public void applyDamage(final int theDamage) {
        this.setHitPoints(this.getHitPoints() - theDamage);
    }

    /**
     * This method returns a String describing what character it is.
     * @return The specific type of character it is.
     */
    public String getMyCharacterType() { return this.myCharacterType; }

    /**
     * This method tells whether the character is a Hero.
     * @return The Character's hero status - True if the character is a Hero, false if it is a Monster.
     */
    public boolean getHeroStatus()
    {
        return this.myHeroStatus;
    }

    /**
     * This method retrieves the character's hit points.
     * @return The number of hit points a character has, represented by an int.
     */
    public int getHitPoints()
    {
        return this.myHitPoints;
    }

    /**
     * This method sets the character's hit point count.
     * @param theHitPoints The number of hit points a character has.
     */
    public void setHitPoints(final int theHitPoints)
    {
        this.myHitPoints = theHitPoints;
    }

    /**
     * This method retrieves the minimum amount of damage a character can inflict.
     * @return The minimum number of hit points a character's attack can affect.
     */
    public int getMinDamageRange()
    {
        return this.myMinDamageRange;
    }

    /**
     * This method sets the minimum amount of damage a character can inflict.
     * @param theMinDamageRange The minimum number of hit points a character's attack can affect.
     */
    public void setMinDamageRange(final int theMinDamageRange)
    {
        this.myMinDamageRange = theMinDamageRange;
    }

    /**
     * This method retrieves the maximum amount of damage a character can inflict.
     * @return The maximum number of hit points a character's attack can affect.
     */
    public int getMaxDamageRange()
    {
        return this.myMaxDamageRange;
    }

    /**
     * This method sets the maximum amount of damage a character can inflict.
     * @param theMaxDamageRange The maximum number of hit points a character's attack can affect.
     */
    public void setMaxDamageRange(final int theMaxDamageRange)
    {
        this.myMaxDamageRange = theMaxDamageRange;
    }

    /**
     * This method retrieves the character's maximum speed.
     * @return How fast a character can move, expressed as an int.
     */
    public int getMaxSpeed()
    {
        return this.myMaxSpeed;
    }

    /**
     * This method sets the character's maximum speed.
     * @param theMaxSpeed How fast a character can move, expressed as an int.
     */
    public void setMaxSpeed(final int theMaxSpeed)
    {
        this.myMaxSpeed = theMaxSpeed;
    }

    /**
     * This method retrieves the character's position.
     * @return The character's position.
     */
    public Vec2 getPos()
    {
        return this.myPos;
    }

    /**
     * This method sets the character's position.
     * @param thePos The character's new position.
     */
    public void setPos(final Vec2 thePos)
    {
        this.myPos = thePos;
    }

    /**
     * This method retrieves the character's velocity.
     * @return The character's velocity.
     */

    public Vec2 getVelocity()
    {
        return this.myVelocity;
    }

    /**
     * This method sets the character's velocity.
     * @param theVelocity The character's new velocity.
     */
    public void setVelocity(final Vec2 theVelocity)
    {
        this.myVelocity = theVelocity;
    }

}
