package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import MVC.View.Animation;

import java.io.Serializable;


public abstract class Entity implements Serializable
{
    private Vec2 myPos;
    private Vec2 myPreviousPos;
    private final Vec2 mySize;
    private final Vec2 myBoundingBox;
    private boolean myEntityAnimated;
    private Animation myAnimation;
    private static final long serialVersionUID = 42L;

    protected Entity(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        myPos = thePos;
        mySize = new Vec2();
        myBoundingBox = theBoundingBox;
    }

    private Entity(final Vec2 theSize, final Vec2 theBoundingBox, final boolean theEntityAnimated, final Animation theAnimation)
    {
        myPos = new Vec2();
        myPreviousPos = new Vec2();
        mySize = theSize;
        myBoundingBox = theBoundingBox;
        myEntityAnimated = theEntityAnimated;
        myAnimation = theAnimation;
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
}