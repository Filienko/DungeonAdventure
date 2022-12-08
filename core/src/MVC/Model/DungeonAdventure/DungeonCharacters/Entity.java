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
        myEntityFactory = theEntityFactory; //things that inherit Entity should also keep track of what Entity Factory spawns it
        mySize = theSize;
        myPos = thePos;
        myType = theType;
        myRotation = 0;
        myActiveStatus = true;
        myCurrentFrame = 0;
    }

    private Entity(final Vec2 theSize, final boolean theEntityAnimated,
                   final Animation theAnimation, final EntityFactory theEntityFactory)
    {
        myEntityFactory = theEntityFactory;
        myPos = new Vec2();
        myPreviousPos = new Vec2();
        mySize = theSize;
        myEntityAnimated = theEntityAnimated;
        myAnimation = theAnimation;

        myActiveStatus = true;
        myCurrentFrame = 0;
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

    public void setMyPos(Vec2 myPos)
    {
        this.myPos = myPos;
    }

    public void updateMyPos(final Vec2 theUpdate)
    {
        myPos.add(theUpdate);
    }

    public Vec2 getMySize()
    {
        return mySize;
    }

    public void setMySize(final Vec2 theSize) { mySize = theSize; }

    public Vec2 getMyPreviousPos()
    {
        return myPreviousPos;
    }

    public void setMyPreviousPos(final Vec2 myPreviousPos)
    {
        this.myPreviousPos = myPreviousPos;
    }

    public boolean isMyEntityAnimated()
    {
        return myEntityAnimated;
    }

    public void setMyEntityAnimated(final boolean myEntityAnimated)
    {
        this.myEntityAnimated = myEntityAnimated;
    }

    public Animation getMyAnimation()
    {
        return myAnimation;
    }

    public void setMyAnimation(final Animation theAnimation)
    {
        myAnimation = theAnimation;
    }

    public String getType() { return myType; }

    protected void incrementCurrentFrame() //?
    {
        myCurrentFrame++;
    }

    public int getRotation() { return myRotation; }

    public void setRotation(int scale) { myRotation = scale; }

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
        myCurrentFrame = theCurrentFrame;
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