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

    public Animation getMyAnimation()
    {
        return myAnimation;
    }

    public void setMyAnimation(final Animation theAnimation)
    {
        myAnimation = theAnimation;
    }
}