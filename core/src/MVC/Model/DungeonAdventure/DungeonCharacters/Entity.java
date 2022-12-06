package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import MVC.View.Animation;


public abstract class Entity
{
    private final Vec2 mySize;
    private EntityFactory myEntityFactory;
    private Vec2 myPos;
    private Vec2 myPreviousPos;
    private Vec2 myBoundingBox;
    private boolean myEntityAnimated;
    private Animation myAnimation;
    private int myRotation;
    private String myType;
    private boolean myActiveStatus;
    private Vec2 myVector;
    private long myCurrentFrame;

    protected Entity(final Vec2 thePos, final String theType,  final Vec2 theBoundingBox, final EntityFactory theEntityFactory)
    {
        myEntityFactory = theEntityFactory; //things that inherit Entity should also keep track of what Entity Factory spawns it
        myPos = thePos;
        mySize = new Vec2();
        myBoundingBox = theBoundingBox;
        myType = theType;
        myRotation = 0;
        myActiveStatus = true;
        myVector = new Vec2(0, 0);
        myCurrentFrame = 0;
    }

    private Entity(final Vec2 theSize, final Vec2 theBoundingBox, final boolean theEntityAnimated,
                   final Animation theAnimation, final EntityFactory theEntityFactory)
    {
        myEntityFactory = theEntityFactory;
        myPos = new Vec2();
        myPreviousPos = new Vec2();
        mySize = theSize;
        myBoundingBox = theBoundingBox;
        myEntityAnimated = theEntityAnimated;
        myAnimation = theAnimation;

        myActiveStatus = true;
        myVector = new Vec2();
        myCurrentFrame = 0;
    }

    public abstract void update();

    public void movement()
    {
        myPreviousPos = myPos;
        myPos = myPos.add(myVector);
    }

    public void destroy()
    {
        myActiveStatus = false; //if an entity is killed, use this method
    }

    public Vec2 getMyPos()
    {
        return myPos;
    }

    public void setMyPos(Vec2 myPos)
    {
        this.myPos = myPos;
    }

    public Vec2 getMySize()
    {
        return mySize;
    }

    public Vec2 getMyPreviousPos()
    {
        return myPreviousPos;
    }

    public void setMyPreviousPos(final Vec2 myPreviousPos)
    {
        this.myPreviousPos = myPreviousPos;
    }

    public Vec2 getMyBoundingBox()
    {
        return myBoundingBox;
    }

    public void setBoundingBox(final Vec2 theBoundingBox)
    {
        myBoundingBox = theBoundingBox;
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

}