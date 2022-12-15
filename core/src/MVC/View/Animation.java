package MVC.View;

import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Animation
{
    // Instance fields
    final private Sprite    mySprite;
    final private long      myFrameCount;      // total number of frames of animation
    private long            myCurrentFrame;    // the current frame of animation
    private long            mySpeed;           // the speed to play this animation
    final private Vec2      mySize;            // the size of the animation frame
    final private String    myName;
    private boolean         myRepeat;

    // Constructor
    public Animation(final String theAnimationName, final Texture theTexture, final int theFrameCount, final int theSpeed)
    {
        myFrameCount    = theFrameCount;
        myCurrentFrame  = 0;
        mySpeed         = theSpeed;
        myName          = theAnimationName;
        mySize          = new Vec2((float) theTexture.getWidth() / theFrameCount, theTexture.getHeight());
        mySprite        = new Sprite(theTexture, 0, 0, (int) mySize.getMyX(), (int) mySize.getMyY());
        mySprite.setOrigin(mySize.getMyX() / 2, mySize.getMyY() / 2);
        myRepeat        = true;
    }

    // Shallow Copy Constructor
    public Animation(final Animation other)
    {
        myFrameCount    = other.myFrameCount;
        myCurrentFrame  = 0;
        mySpeed         = other.mySpeed;
        myName          = other.myName;
        mySize          = other.mySize;
        mySprite        = other.mySprite;
        myRepeat        = other.myRepeat;
    }


    // updates the animation to show the next frame, depending on its speed
    // animation loops when it reaches the end
    public void update()
    {
        myCurrentFrame++;

        if (mySpeed > 0)
        {
            long frame = (myCurrentFrame / mySpeed) % myFrameCount;
            int leftX = (int) (Math.floor(frame) * mySize.getMyX());
            mySprite.setRegion(leftX, 0, (int) mySize.getMyX(), (int) mySize.getMyY());
        }
    }

    public boolean hasEnded()  { return !myRepeat && myCurrentFrame >= (mySpeed * myFrameCount) - 1; }

    public String getName()    { return myName; }

    public Vec2 getSize()      { return mySize; }

    public Sprite getSprite()  { return mySprite; }

    public void setPos(float x, float y)
    {
        float adjustedX = x - (mySize.getMyX() - 64) / 2;
        float adjustedY = y - (mySize.getMyY() - 64) / 2;


        mySprite.setPosition(adjustedX, adjustedY);
    }

    public void setSpeed(final long theSpeed)
    {
        if (theSpeed > 0 && mySpeed != 0)
        {
            mySpeed = theSpeed;
        }
    }

    public long getSpeed()
    {
        return mySpeed;
    }

    public void setRepeat(final boolean theRepeat)
    {
        myRepeat = theRepeat;
    }

}
