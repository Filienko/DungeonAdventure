package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.math.collision.BoundingBox;

import java.util.Random;

public abstract class DungeonCharacter extends Entity
{
    private EntityFactory myEntityFactory;

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
     * The character's velocity.
     */
    private Vec2 myVelocity;

    private long myCurrentFrame;
    private long initiatedFrame;

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
                     final int theMaxSpeed, final Vec2 thePos, final Vec2 theVelocity, final EntityFactory theEntityFactory)
    {
        super(new Vec2(), new Vec2(), theEntityFactory);
        myCharacterType = theCharacterType;
        myHeroStatus = theHeroStatus;

        myMinDamageRange = theMinDamageRange;
        myHitPoints = theHitPoints;
        myMaxDamageRange = theMaxDamageRange;
        myMaxSpeed = theMaxSpeed;
        setMyPos(thePos);
        myVelocity = theVelocity;
        myCurrentFrame = 0;
        initiatedFrame = 0;
    }


    protected int attack(final DungeonCharacter theOpponent, final Vec2 theDamageArea) //is the damage area needed? never used
    {
        Vec2 overlap = Physics.getOverlap(this, theOpponent);

        int damage = 0;

        if (overlap.getMyX() > 0 && overlap.getMyY() > 0) {
            Random rand = new Random();
            damage += rand.nextInt(myMaxDamageRange) + myMinDamageRange;

            long delay = 30;
            if(theOpponent.getHeroStatus())
            {
                if (myCurrentFrame <= initiatedFrame + delay)
                {
                    initiatedFrame++;
                }
            }
            //apply invincibility for Heros for 30 frames after each hit from Monsters

        }

        theOpponent.applyDamage(damage);

        if (theOpponent.getHitPoints() == 0) {
            theOpponent.destroy();
        }

        return damage;
    }


    @Override
    public void update()
    {
        movement();
        //attack();
        myCurrentFrame++;
    }

    /**
     * This method subtracts hit points from a character's total hit point count.
     * @param theDamage The number of hit points to be subtracted.
     */
    public void applyDamage(final int theDamage) {
        int damageOutcome = getHitPoints() - theDamage;
        if (damageOutcome <= 0) {
            setHitPoints(0);
        } else if (damageOutcome > 0) {
            setHitPoints(damageOutcome);
        }

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


    public EntityFactory getMyEntityFactory()
    {
        return myEntityFactory;
    }

    public void setMyEntityFactory(final EntityFactory theEntityFactory)
    {
        myEntityFactory = theEntityFactory;
    }

}
