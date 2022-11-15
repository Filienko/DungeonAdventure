package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import MVC.View.Animation;


public abstract class Entity
{
    private Vec2 myPos;
    private Vec2 myPreviousPos;
    private Vec2 mySize;
    private Vec2 myBoundingBox;
    private boolean myEntityAnimated;
    private Animation myAnimation;

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

    public void setMySize(Vec2 mySize)
    {
        this.mySize = mySize;
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

    public void setMyBoundingBox(final Vec2 myBoundingBox)
    {
        this.myBoundingBox = myBoundingBox;
    }

    public boolean isMyEntityAnimated()
    {
        return myEntityAnimated;
    }

    public void setMyEntityAnimated(final boolean myEntityAnimated)
    {
        this.myEntityAnimated = myEntityAnimated;
    }

    private Vec2 getPos()
    {
        return myPos;
    }

    private void setPos(final Vec2 thePos)
    {
        myPos = thePos;
    }

    private Vec2 getPreviousPos()
    {
        return myPreviousPos;
    }

    private void setPreviousPos(final Vec2 thePreviousPos)
    {
        myPreviousPos = thePreviousPos;
    }

    private Vec2 getSize()
    {
        return mySize;
    }

    private void setSize(final Vec2 theSize)
    {
        mySize = theSize;
    }

    private Vec2 getBoundingBox()
    {
        return myBoundingBox;
    }

    private void setBoundingBox(final Vec2 theBoundingBox)
    {
        myBoundingBox = theBoundingBox;
    }

    private boolean isEntityAnimated()
    {
        return myEntityAnimated;
    }

    private void setEntityAnimated(final boolean theEntityAnimated)
    {
        myEntityAnimated = theEntityAnimated;
    }

    public Animation getAnimation()
    {
        return myAnimation;
    }

    public void setAnimation(final Animation theAnimation)
    {
        myAnimation = theAnimation;
    }
}