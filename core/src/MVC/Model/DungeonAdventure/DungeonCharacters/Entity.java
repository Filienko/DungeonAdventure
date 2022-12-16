package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import MVC.View.Animation;

import java.io.Serializable;

/**
 * Entity class that all Dungeon Characters, Items, and Weapons inherit from. It defines attributes and
 * behaviors that are used in animations and
 *
 * @author
 * @version 1.1 ??
 */
public abstract class Entity implements Serializable
{
    private Vec2 mySize;
    private EntityFactory myEntityFactory;
    private Vec2 myPos;
    private Vec2 myPreviousPos;
    private boolean myEntityAnimated;
    private transient Animation myAnimation;
    private float myRotation;
    private String myType;
    private boolean myActiveStatus;
    private long myCurrentFrame;
    private Vec2 myRoom;

    /**
     * Entity constructor that initializes the Entity's size, position, type, and the Entity Factory that generates
     * it.
     * @param theSize The Entity's size.
     * @param thePos The Entity's position.
     * @param theType The Entity's type.
     * @param theEntityFactory The Entity Factory that generated the Entity.
     */
    protected Entity(final Vec2 theSize, final Vec2 thePos, final String theType, final EntityFactory theEntityFactory)
    {
        setMyEntityFactory(theEntityFactory);
        setMySize(theSize);
        setMyPos(thePos);
        setMyPreviousPos(thePos);
        setType(theType);
        setRotation(0);
        setActiveStatus(true);
        setCurrentFrame(0);
    }

    /**
     * Entity's update behavior.
     */
    public void update() {}

    /**
     * This method sets an Entity's active status to false and size to 0 (in effect, destroying it).
     */
    public void destroy()
    {
        myActiveStatus = false;
        setMySize(new Vec2(0, 0));
    }

    /**
     * This method retrieves the Entity's position.
     * @return The Entity's position, represented by a Vec2.
     */
    public Vec2 getMyPos()
    {
        return myPos;
    }

    /**
     * This method sets the Entity's position.
     * @param thePos The Entity's new position.
     */
    public void setMyPos(Vec2 thePos)
    {
        if (thePos != null)
        {
            myPos = thePos;
        }
    }

    /**
     * This method updates the Entity's position by adding the update vector to the Entity's current
     * position.
     * @param theUpdate The vector to update the position with.
     */
    public void updateMyPos(final Vec2 theUpdate)
    {
        if (theUpdate != null)
        {
            myPos = myPos.add(theUpdate);
        }
    }

    /**
     * This method retrieves the Entity's size, represented by a Vec2.
     * @return The Entity's size.
     */
    public Vec2 getMySize()
    {
        return mySize;
    }

    /**
     * This method sets the Entity's size.
     * @param theSize The Entity's new size.
     */
    public void setMySize(final Vec2 theSize) {
        if (theSize != null)
        {
            mySize = theSize;
        }
    }

    /**
     * This method retrieves the Entity's previous position, represented by a Vec2.
     * @return The Entity's previous position.
     */
    public Vec2 getMyPreviousPos()
    {
        return myPreviousPos;
    }

    /**
     * This method sets Entity's previous position.
     * @param thePreviousPos The Entity's previous position, represented by a Vec2.
     */
    public void setMyPreviousPos(final Vec2 thePreviousPos)
    {
        if (thePreviousPos != null)
        {
            myPreviousPos = thePreviousPos;
        }
    }

    /**
     * This method tells whether the Entity is animated.
     * @return True if the Entity is animated, false if it is not.
     */
    public boolean isMyEntityAnimated()
    {
        return myEntityAnimated;
    }

    /**
     * This method retrieves the Entity's Animation.
     * @return The Entity's Animation.
     */
    public Animation getMyAnimation()
    {
        return myAnimation;
    }

    /**
     * This method sets the Entity's Animation using an Animation object.
     * @param theAnimation The Entity's new Animation
     */
    public void setMyAnimation(final Animation theAnimation)
    {
        if (theAnimation != null)
        {
            myAnimation = new Animation(theAnimation);
        }
    }
    public void setMyAnimation(final Animation theAnimation, final boolean theRepeat)
    {
        if (theAnimation != null)
        {
            myAnimation = new Animation(theAnimation);
            myAnimation.setRepeat(theRepeat);
        }
    }

    /**
     * This method sets the Entity's specific type, which must be longer than 1 character.
     * @param theType The Entity's new type.
     */
    public void setType(final String theType)
    {
        if (theType != null && theType.length() > 1)
        {
            myType = theType;
        }
    }

    /**
     * This method retrieves the Entity's type, represented by a String.
     * @return The Entity's type.
     */
    public String getType()                     { return myType; }

    /**
     * This method increments an Entity's currentFrame counter.
     */
    protected void incrementCurrentFrame()
    {
        myCurrentFrame++;
    }

    /**
     * This method retrieves the Entity's rotation amount.
     * @return The Entity's rotation.
     */
    public float getRotation() { return myRotation; }

    /**
     * This method sets the Entity's rotation amount.
     * @param theRotation The Entity's rotation.
     */
    public void setRotation(float theRotation) {
        if (theRotation >= 0) // can it be negative?
        {
            myRotation = theRotation;
        }
    }

    /**
     * This method sets the Entity's activeStatus, which tells whether the Entity is alive or not.
     * @param theActiveStatus The Entity's new activeStatus.
     */
    public void setActiveStatus(final boolean theActiveStatus)
    {
        myActiveStatus = theActiveStatus;
    }

    /**
     * This method returns the Entity's activeStatus.
     * @return True if the Entity is active (alive), false if the Entity is inactive (dead).
     */
    public boolean getActiveStatus()
    {
        return myActiveStatus;
    }

    /**
     * This method retrieves the Entity's currentFrame counter.
     * @return The Entity's currentFrame, represented by a long.
     */
    public long getCurrentFrame()
    {
        return myCurrentFrame;
    }

    /**
     * This method sets the Entity's currentFrame counter.
     * @param theCurrentFrame The Entity's new currentFrame, represented by a long.
     */
    public void setCurrentFrame(final long theCurrentFrame)
    {
        if (theCurrentFrame >= 0)
        {
            myCurrentFrame = theCurrentFrame;
        }
    }

    /**
     * This method retrieves the Entity Factory that generated the Entity.
     * @return The Entity's Entity Factory.
     */
    public EntityFactory getMyEntityFactory()
    {
        return myEntityFactory;
    }

    /**
     * This method sets the Entity Factory that generated the Entity.
     * @param theEntityFactory The Entity's new Entity Factory.
     */
    protected void setMyEntityFactory(final EntityFactory theEntityFactory)
    {
        if (theEntityFactory != null)
        {
            myEntityFactory = theEntityFactory;
        }
    }

    /**
     * This method retrieves the Room that the Entity is located in, represented by a Vec2.
     * @return The Entity's Room.
     */
    public Vec2 getRoom()
    {
        return myRoom;
    }

    /**
     * This method sets the Room that the Entity should be located in.
     * @param theRoom The Entity's new Room, represented by a Vec2.
     */
    public void setRoom(final Vec2 theRoom)
    {
        if (theRoom != null) {
            myRoom = theRoom;
        }
    }
}