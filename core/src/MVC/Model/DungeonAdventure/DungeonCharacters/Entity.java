package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import MVC.View.Animation;


public abstract class Entity
{
    private Vec2 mySize;
    private EntityFactory myEntityFactory;
    private Vec2 myPos;
    private Vec2 myPreviousPos;
    private boolean myEntityAnimated;
    private Animation myAnimation;
    private int myRotation;
    private String myType;
    private boolean myActiveStatus;
    private long myCurrentFrame;

    protected Entity(final Vec2 theSize, final Vec2 thePos, final String theType, final EntityFactory theEntityFactory)
    {
        setMyEntityFactory(theEntityFactory); //things that inherit Entity should also keep track of what Entity Factory spawns it
        setMySize(theSize);
        setMyPos(thePos);
        setMyPreviousPos(thePos);
        if (theType != null && theType.length() > 1)
        {
            myType = theType;
        }
        setRotation(0);
        setActiveStatus(true);
        setCurrentFrame(0);
    }

    private Entity(final Vec2 theSize, final boolean theEntityAnimated,
                   final Animation theAnimation, final EntityFactory theEntityFactory) //is this constructor necessary?
    {
        setMyEntityFactory(theEntityFactory);
        myPos = new Vec2();
        myPreviousPos = new Vec2();
        setMySize(theSize);
        myEntityAnimated = theEntityAnimated;
        setMyAnimation(theAnimation);

        setActiveStatus(true);
        setCurrentFrame(0);
    }

    public abstract void update();

    public void destroy()
    {
        myActiveStatus = false;
    }

    public Vec2 getMyPos()
    {
        return myPos;
    }

    public void setMyPos(Vec2 thePos)
    {
        if (thePos != null)  //can myPos ever be negative
        {
            myPos = thePos;
        }
    }

    public void updateMyPos(final Vec2 theUpdate)
    {
        if (theUpdate != null)
        {
            myPos = myPos.add(theUpdate);
        }
    }

    public Vec2 getMySize()
    {
        return mySize;
    }

    public void setMySize(final Vec2 theSize) {
        if (theSize != null)
        {
            mySize = theSize;
        }
    }

    public Vec2 getMyPreviousPos()
    {
        return myPreviousPos;
    }

    public void setMyPreviousPos(final Vec2 thePreviousPos)
    {
        if (thePreviousPos != null)
        {
            myPreviousPos = thePreviousPos;
        }
    }

    public boolean isMyEntityAnimated()
    {
        return myEntityAnimated;
    }

    public void setMyEntityAnimated(final boolean theEntityAnimated)
    {
        myEntityAnimated = theEntityAnimated;
    }

    public Animation getMyAnimation()
    {
        return myAnimation;
    }

    public void setMyAnimation(final Animation theAnimation)
    {
        if (theAnimation != null)
        {
            myAnimation = theAnimation;
        }
    }

    public String getType() { return myType; }

    protected void incrementCurrentFrame() //?
    {
        myCurrentFrame++;
    }

    public int getRotation() { return myRotation; }

    public void setRotation(int theScale) {
        if (theScale >= 0) // can scale be negative?
        {
            myRotation = theScale;
        }
    }

    public void setActiveStatus(final boolean theActiveStatus)
    {
        myActiveStatus = theActiveStatus;
    }

    public boolean getActiveStatus()
    {
        return myActiveStatus;
    }

    public long getCurrentFrame()
    {
        return myCurrentFrame;
    }

    public void setCurrentFrame(final long theCurrentFrame)
    {
        if (theCurrentFrame >= 0)
        {
            myCurrentFrame = theCurrentFrame;
        }
    }

    public EntityFactory getMyEntityFactory()
    {
        return myEntityFactory;
    }

    public void setMyEntityFactory(final EntityFactory theEntityFactory)
    {
        if (theEntityFactory != null)
        {
            myEntityFactory = theEntityFactory;
        }
    }
}