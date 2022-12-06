package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Wall;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import MVC.View.Animation;
import com.badlogic.gdx.graphics.Texture;

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
     * The amount of damage the character can inflict.
     */

    private int myDamage;

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
    private boolean myInvincibility;

    /**
     * DungeonCharacter constructor that initializes the Character's character type, hero status, hit points,
     * minimum and maximum damage amounts, maximum speed, and position.
     *
     * @param theCharacterType The character's type.
     * @param theHeroStatus The character's hero status (true means Hero, false means Monster).
     * @param theHitPoints The character's hit points.
     * @param theDamage The amount of damage the character can administer.
     * @param theMaxSpeed The character's maximum speed
     * @param thePos The character's location.
     * @param theVelocity The character's velocity.
     */
    DungeonCharacter(final String theCharacterType, final boolean theHeroStatus, final int theHitPoints,
                     final int theDamage, final int theMaxSpeed, final Vec2 thePos, final Vec2 theVelocity, final EntityFactory theEntityFactory)
    {
        super(new Vec2(),theCharacterType, new Vec2(), theEntityFactory);
        myCharacterType = theCharacterType;
        myHeroStatus = theHeroStatus;
        myDamage = theDamage;
        myHitPoints = theHitPoints;
        myMaxSpeed = theMaxSpeed;
        setMyPos(thePos);
        myVelocity = theVelocity;
        myCurrentFrame = 0;
        initiatedFrame = 0;
    }

    protected int attack() //should this be changed to a weapon? Vec2 theDamageArea is never used
    {

        var theOpponent = new Warrior(new EntityFactory());
        Vec2 overlap = Physics.getOverlap(this, theOpponent);

        int damage = 0;

        if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
        {
            if (theOpponent.getHeroStatus() && !((theOpponent).isInvincibility()))
            {
                damage += myDamage;
                ((Hero)theOpponent).setInvincibility(true);
            } else if (!theOpponent.getHeroStatus())
            {
                damage += myDamage;
            }
        }

        //apply invincibility for Heroes for 30 frames after each hit from Monsters
        long delay = 30;
        if(theOpponent.getHeroStatus() && ((Hero)theOpponent).isInvincibility())
        {
            if (myCurrentFrame <= initiatedFrame + delay)
            {
                initiatedFrame++;
            } else
            {
                ((Hero)theOpponent).setInvincibility(false);
            }
        }

        theOpponent.applyDamage(damage);

        if (theOpponent.getHitPoints() == 0)
        {
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

    public void die()
    {
        setBoundingBox(new Vec2());
        setMyAnimation(new Animation("deathAnimation",new Texture("path?"),15,4));
    }

    /**
     * This method subtracts hit points from a character's total hit point count.
     * @param theDamage The number of hit points to be subtracted.
     */
    public void applyDamage(final int theDamage)
    {
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
    public String getCharacterType() { return myCharacterType; }

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
     * This method retrieves the amount of damage a character can inflict.
     * @return The number of hit points a character's attack can affect.
     */
    public int getDamage()
    {
        return myDamage;
    }

    /**
     * This method sets the character's damage.
     * @param theDamage The number of damage points a character can inflict.
     */
    public void setDamage(final int theDamage)
    {
        this.myDamage = theDamage;
    }

    /**
     * This method retrieves the character's maximum speed.
     * @return How fast a character can move, expressed as an int.
     */
    public int getMaxSpeed()
    {
        return myMaxSpeed;
    }

    /**
     * This method sets the character's speed.
     * @param theMaxSpeed The character's new speed.
     */
    public void setMaxSpeed(final int theMaxSpeed)
    {
        myMaxSpeed = theMaxSpeed;
    }

    /**
     * This method retrieves the character's velocity.
     * @return The character's velocity.
     */

    public Vec2 getVelocity()
    {
        return myVelocity;
    }

    /**
     * This method sets the character's velocity.
     * @param theVelocity The character's new velocity.
     */
    public void setVelocity(final Vec2 theVelocity)
    {
        myVelocity = theVelocity;
    }

    public EntityFactory getMyEntityFactory()
    {
        return myEntityFactory;
    }

    public void setMyEntityFactory(final EntityFactory theEntityFactory)
    {
        myEntityFactory = theEntityFactory;
    }

    public boolean isInvincibility()
    {
        return myInvincibility;
    }

    public void setInvincibility(final boolean theInvincibility)
    {
        myInvincibility = theInvincibility;
    }

}
