package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Items.Item;
import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

public abstract class DungeonCharacter extends Entity implements ICollidable
{
    /**
     * Monster's default position.
     */
    protected Vec2 myHomePosition;
    /**
     * The specific character type.
     */
    private String myCharacterType;

    /**
     * Hero status that describes if the character is a Hero or a Monster.
     */
    private boolean myHeroStatus;

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
    private float myMaxSpeed;

    /**
     * The character's velocity.
     */
    private Vec2 myVelocity;

    /**
     * The frame where a Dungeon Character's invincibility ends, and they can start taking damage again.
     */
    private long myInvincibilityEndFrame;

    /**
     * Tells if a Dungeon Character is invincible which determines if they can take damage.
     */
    private boolean myInvincibility;

    /**
     * Tells if the Dungeon Character is being knocked back by an attack.
     */
    private boolean myKnockback;

    /**
     * The frame where a Dungeon Character stops being knocked back.
     */
    private long myKnockbackEndFrame;

    /**
     * Signifies if the character took damage from lava.
     */
    private boolean myBurning;

    /**
     * The last frame this character took damage.
     */
    private long myLastDamageFrame;

    /**
     * The magnitude that this character knocks back other entities with.
     */
    private float myKnockBackPower;

    /**
     * The duration that this character knocks back other entities.
     */
    private long myKnockBackLength;

    /**
     * DungeonCharacter constructor that initializes the Character's character type, hero status, hit points,
     * damage amount, maximum speed, position, and velocity.
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
        setCharacterType(theCharacterType);
        setDamage(theDamage);
        setHitPoints(theHitPoints);
        setMaxSpeed(theMaxSpeed);
        setMyPos(thePos);
        setVelocity(theVelocity);
        setHeroStatus(theHeroStatus);
        myKnockback = false;
        myBurning = false;
        myHomePosition = new Vec2();
    }

    /**
     * The DungeonCharacter's attack behavior.
     * @return Amount of damage the character inflicts, expressed as an int.
     */
    public abstract int attack();

    /**
     * The DungeonCharacter's movement behavior.
     */
    public abstract void movement();

    /**
     * Information about the DungeonCharacter that is to be updated.
     */
    @Override
    public void update()
    {
        if(getCurrentFrame() >= getInvincibilityEndFrame())
        {
            setInvincibility(false);
            myBurning = false;
        }
        if(getCurrentFrame() >= getKnockBackEndFrame())
        {
            setKnockBack(false);
        }

        if(myHitPoints <= 0)
        {
            setDamage(0);
            if(getMyAnimation().hasEnded())
            {
                destroy();
            }
        }
        else if(!isKnockBack())
        {
            movement();
            collide();
        }
        else if (myHitPoints > 0)
        {
            setMyPreviousPos(getMyPos());
            updateMyPos(getVelocity());
            if (!Physics.getRoom(getMyPos().getMyX(), getMyPos().getMyY())
                    .equals(Physics.getRoom(getMyPreviousPos().getMyX(), getMyPreviousPos().getMyY())))
            {
                setMyPos(getMyPreviousPos());
            }
            collide();
        }
    }

    /**
     * This method tells what must be done when a Dungeon Character dies. Its size is set to 0 and
     * its animation is updated to a death animation.
     */
    public void die()
    {
        setMySize(new Vec2(0, 0));
        if (getMyEntityFactory().getAssets() != null)
        {
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation("enemyDeath"),false);
        }
    }

    /**
     * This method subtracts hit points from a character's total hit point count. If this action
     * would cause the character to have a negative number of hit points, their hit point count is set to 0 and
     * their myBurning field is set to false. Then it sets the character's lastDamageFrame to its currentFrame.
     * @param theDamage The number of hit points to be subtracted.
     */
    public void applyDamage(final int theDamage)
    {
        int damageOutcome = getHitPoints() - theDamage;

        if (damageOutcome <= 0)
        {
            setHitPoints(0);
            myBurning = false;
            //destroy();
        }
        else
        {
            setHitPoints(damageOutcome);
        }
        myLastDamageFrame = getCurrentFrame();
    }

    /**
     * This method returns a String describing what character it is.
     * @return The specific type of character it is.
     */
    public String getCharacterType() { return myCharacterType; }

    /**
     * This method sets the Dungeon Character's type.
     * @param theCharacterType The specific type of character it is.
     */
    protected void setCharacterType(final String theCharacterType)
    {
        if (theCharacterType != null)
        {
            myCharacterType = theCharacterType;
        }
    }

    /**
     * This method tells whether the character is a Hero.
     * @return The Character's hero status - True if the character is a Hero, false if it is a Monster.
     */
    public boolean getHeroStatus()
    {
        return myHeroStatus;
    }

    /**
     * This method tells whether the character is a Hero.
     * @param theHeroStatus The Character's hero status - True if the character is a Hero, false if it is a Monster.
     */
    private void setHeroStatus(final boolean theHeroStatus)
    {
        myHeroStatus = theHeroStatus;
    }

    /**
     * This method retrieves the character's hit points.
     * @return The number of hit points a character has, represented by an int.
     */
    public int getHitPoints()
    {
        return myHitPoints;
    }

    /**
     * This method sets the character's hit point count.
     * @param theHitPoints The number of hit points a character has.
     */
    public void setHitPoints(final int theHitPoints)
    {
        if (theHitPoints >= 0 && theHitPoints <=10)
        {
            myHitPoints = theHitPoints;
        }
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
        if (theDamage >= 0 && theDamage <=10)
        {
            myDamage = theDamage;
        }
    }

    /**
     * This method retrieves the character's maximum speed.
     * @return How fast a character can move, expressed as an int.
     */
    public float getMaxSpeed()
    {
        return myMaxSpeed;
    }

    /**
     * This method sets the character's speed.
     * @param theMaxSpeed The character's new speed.
     */
    public void setMaxSpeed(final float theMaxSpeed)
    {
        if (myMaxSpeed <= 100 && theMaxSpeed >= -100)
        {
            myMaxSpeed = theMaxSpeed;
        }
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
        if (theVelocity != null)
        {
            myVelocity = theVelocity;
        }
    }

    /**
     * This method sets the character's velocity.
     * @param theVelocity The character's new velocity.
     * @param theFramesLong amount of frames for following that velocity.
     */
    public void setVelocity(final Vec2 theVelocity, final long theFramesLong)
    {
        if (theVelocity != null && theFramesLong >= 0)
        {
            myVelocity = theVelocity;
        }
    }

    /**
     * When a character takes damage, this method determines the knockback velocity and sets the duration of the
     * knockback.
     * @param e The Dungeon Character being knocked back.
     * @param power The strength of the knockback.
     * @param duration The duration of the knockback.
     */
    public void knockBack(Entity e, float power, long duration)
    {
        var v = this.getMyPos().minus(e.getMyPos());
        var normalizedV = v.multiply(v.quickInverseMagnitude());
        setVelocity(normalizedV.multiply(power), duration);

        setKnockBack(true);
        setKnockBackEndFrame(duration);
    }

    /**
     * This method tells if the character is being knocked back by an attack.
     * @return True if the character is being knocked back, false if not.
     */
    public boolean isKnockBack()
    {
        return myKnockback;
    }

    /**
     * This method sets the character's knock back status.
     * @param theKnockBack True if the character is being knocked back, false if not.
     */
    public void setKnockBack(final boolean theKnockBack)
    {
        myKnockback = theKnockBack;
    }

    /**
     * This method retrieves the frame when a character will stop being knocked back.
     * @return The frame where a character stops being knocked back.
     */
    public long getKnockBackEndFrame()
    {
        return myKnockbackEndFrame;
    }

    /**
     * This method sets the final frame where a character gets knocked back
     * @param theKnockBackEndFrame The final frame of a knock back.
     */
    public void setKnockBackEndFrame(final long theKnockBackEndFrame)
    {
        if (theKnockBackEndFrame >= 0) {
            myKnockbackEndFrame = getCurrentFrame() + theKnockBackEndFrame;
        }
    }

    /**
     * This method sets the knock back magnitude of this character
     * @param thePower The magnitude applied to knock back from this character.
     */
    public void setMyKnockBackPower(float thePower)     { myKnockBackPower = thePower; }

    /**
     * @return The magnitude applied to knock back from this character.
     */
    public float getMyKnockBackPower()                  { return myKnockBackPower; }

    /**
     * @param theDuration How long a knock back from this character lasts.
     */
    public void setMyKnockBackLength(long theDuration)  { myKnockBackLength = theDuration; }

    /**
     * @return The duration of a knock back from this character.
     */
    public long getMyKnockBackLength()                  { return myKnockBackLength; }

    /**
     * Retrieves the Dungeon Character's invincible status which tells and if they can take damage.
     * @return True if they are, false if they are not.
     */
    public boolean isInvincibility()
    {
        return myInvincibility;
    }

    /**
     * This method sets the DungeonCharacter's invincibility status.
     * @param theInvincibility True if they are invincible and cannot take damage, false if they are not
     *                         invincible and can take damage.
     */
    public void setInvincibility(final boolean theInvincibility)
    {
        myInvincibility = theInvincibility;
    }

    /**
     * This method sets the DungeonCharacter's invincibility status for a specific number of frames.
     * @param theInvincibility True if they are invincible and cannot take damage, false if they are not
     *                         invincible and can take damage.
     * @param theFrames The number of frames the Dungeon Character is invincible for.
     */
    public void setInvincibility(final boolean theInvincibility, final long theFrames)
    {

        if(theFrames >=0)
        {
            myInvincibility = theInvincibility; //should this be moved out of if statement
            myInvincibilityEndFrame = getCurrentFrame() + theFrames;
        }

    }

    /**
     * This method retrieves the frame where a Dungeon Character's invincibility ends.
     * @return The frame where a Dungeon Character stops being invincible.
     */
    public long getInvincibilityEndFrame()
    {
        return myInvincibilityEndFrame;
    }

    /**
     * This method retrieves the Character's home position.
     * @return The Character's home position, expressed as a Vec2.
     */
    public Vec2 getHomePosition()
    {
        return myHomePosition;
    }

    /**
     * This method sets the Character's home position.
     * @param theHomePosition  The Character's new home position, expressed as a Vec2.
     */
    public void setHomePosition(final Vec2 theHomePosition)
    {
        myHomePosition.copy(theHomePosition);
    }

    /**
     * This method tells whether the character is burning due to Lava.
     * @return True if the character is burning, false if it is not.
     */
    public boolean isBurning() { return myBurning; }

    /**
     * This method retrieves the frame when the Dungeon Character last took damage. Used to ensure that
     * a character cannot constantly take damage.
     * @return The character's lastDamageFrame, expressed as a long.
     */
    public long getLastDamageFrame() { return myLastDamageFrame; }

    /**
     * Dungeon Character's collision behavior.
     */
    @Override
    public void collide()
    {
        Vec2 overlap;

        for(var t:getMyEntityFactory().getEntities())
        {
            overlap = Physics.getOverlap(this, t);

            if (!t.getType().contentEquals(this.getType()) && !t.getType().contentEquals("Sword"))
            {
                if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
                {
                    // If the tile blocks movement
                    if (t.getType().contains("Wall")  || t.getType().contains("Door")  || t.getType().contains("exit")
                            || t.getType().contains("Monster")  || t.getType().contains("pillar")
                            || t.getType().contains("Hero")
                            || (t.getType().equals("Lava") && this.getType().equals("Monster")))
                    {
                        Physics.tileResolution(overlap, this, t);
                    }
                    else if (t.getType().equals("Lava") && this.getType().equals("Hero"))
                    {
                        if (!this.isInvincibility())
                        {
                            this.applyDamage(1);
                            if (this.getHitPoints() <= 0)
                            {
                                this.destroy();
                            }
                            this.setInvincibility(true, 45);
                            myBurning = true;
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