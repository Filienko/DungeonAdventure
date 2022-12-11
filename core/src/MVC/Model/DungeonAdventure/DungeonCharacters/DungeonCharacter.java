package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

public abstract class DungeonCharacter extends Entity implements ICollidable
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

    private long initiatedFrame;
    private long myInvincibilityEndFrame;
    private boolean myInvincibility;
    private boolean myKnockback;
    private long myKnockbackEndFrame;

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
                     final int theDamage, final int theMaxSpeed, final Vec2 theSize, final Vec2 thePos,
                     final Vec2 theVelocity, final EntityFactory theEntityFactory)
    {
        super(theSize, thePos,theCharacterType, theEntityFactory);
        myCharacterType = theCharacterType;
        myHeroStatus = theHeroStatus;
        myDamage = theDamage;
        myHitPoints = theHitPoints;
        myMaxSpeed = theMaxSpeed;
        setMyPos(thePos);
        myVelocity = theVelocity;
        initiatedFrame = 0;
        myKnockback = false;
    }

    public abstract int attack();
    public abstract void movement();

    @Override
    public void update()
    {
        if(getCurrentFrame() >= getInvincibilityEndFrame())
        {
            setInvincibility(false);
        }
        if(getCurrentFrame() >= getKnockbackEndFrame())
        {
            setKnockback(false);
        }

        if(myHitPoints <= 0)
        {
            setDamage(0);
            if(getMyAnimation().hasEnded())
            {
                destroy();
                return;
            }
        }
        else if(myHitPoints > 0 && !isKnockback())
        {
            movement();
            collide();
        }
        else if (myHitPoints > 0)
        {
            setMyPreviousPos(getMyPos());
            updateMyPos(getVelocity());
            collide();
        }
    }

    public void die()
    {
        setMySize(new Vec2(0, 0));
        setMyAnimation(getMyEntityFactory().getAssets().getAnimation("enemyDeath"));
    }

    /**
     * This method subtracts hit points from a character's total hit point count.
     * @param theDamage The number of hit points to be subtracted.
     */
    public void applyDamage(final int theDamage)
    {
        int damageOutcome = getHitPoints() - theDamage;

        if (damageOutcome <= 0)
        {
            setHitPoints(0);
        }
        else if (damageOutcome > 0)
        {
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

    /**
     * This method sets the character's velocity.
     * @param theVelocity The character's new velocity.
     * @param theFramesLong amount of frames for following that velocity.
     */
    public void setVelocity(final Vec2 theVelocity, final long theFramesLong)
    {
        myVelocity = theVelocity;
        setKnockback(true);
        setKnockbackEndFrame(theFramesLong);
    }

    public boolean isKnockback()
    {
        return myKnockback;
    }

    public void setKnockback(final boolean theKnockback)
    {
        myKnockback = theKnockback;
    }

    public long getKnockbackEndFrame()
    {
        return myKnockbackEndFrame;
    }

    public void setKnockbackEndFrame(final long theKnockbackEndFrame)
    {
        myKnockbackEndFrame = getCurrentFrame() + theKnockbackEndFrame;
    }

    public boolean isInvincibility()
    {
        return myInvincibility;
    }

    public void setInvincibility(final boolean theInvincibility)
    {
        myInvincibility = theInvincibility;
    }

    public void setInvincibility(final boolean theInvincibility, final long theFrames)
    {
        myInvincibility = theInvincibility;
        myInvincibilityEndFrame = getCurrentFrame() + theFrames;
    }

    public long getInvincibilityEndFrame()
    {
        return myInvincibilityEndFrame;
    }

    @Override
    public void collide()
    {
        Vec2 overlap;
        Vec2 previousOverlap;

        for(var t:getMyEntityFactory().getEntities())
        {
            overlap = Physics.getOverlap(this, t);
            if (!t.getType().contentEquals(this.getType()) && !t.getType().contentEquals("Sword"))
            {
                if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
                {
                    // If the tile blocks movement
                    if (t.getType().contains("Wall")  || t.getType().contains("Door")  || t.getType().contains("exit")|| t.getType().contains("Monster")
                            || t.getType().contains("Hero"))
                    {
                        previousOverlap = Physics.getPreviousOverlap(this, t);

                        // If the overlap is horizontal
                        if (previousOverlap.getMyY() > 0)
                        {
                            // If the player came from the left, push them out to the left
                            if (this.getMyPos().getMyX() < t.getMyPos().getMyX())
                            {
                                this.getMyPos().setMyX(this.getMyPos().getMyX() - (overlap.getMyX()));
                            }
                            // If the player came from the right push them out to the right
                            else
                            {
                                this.getMyPos().setMyX(this.getMyPos().getMyX() + (overlap.getMyX()));
                            }
                        }

                        // If the overlap is vertical
                        if (previousOverlap.getMyX() > 0)
                        {
                            // If the player came from above push them up
                            if (this.getMyPos().getMyY() < t.getMyPos().getMyY())
                            {
                                this.getMyPos().setMyY(this.getMyPos().getMyY() - (overlap.getMyY()));
                            }
                            else
                            {
                                this.getMyPos().setMyY(this.getMyPos().getMyY() + (overlap.getMyY()));
                            }
                        }
                    }
                    if ((t instanceof Item) && this.getHeroStatus())
                    {
                        ((Item) t).activate((Hero) this);
                    }
                }
            }
        }
    }
}