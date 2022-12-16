package MVC.View;

import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Animation
{
    // Instance fields
    /**
     * The libgdx Sprite that is used for rendering
     */
    final private Sprite    mySprite;
    /**
     * The total number of frames of Animation
     */
    final private long      myFrameCount;
    /**
     * The current frame of Animation
     */
    private long            myCurrentFrame;
    /**
     * The speed to play this Animation
     */
    private long            mySpeed;
    /**
     * The size of a single animation frame
     */
    final private Vec2      mySize;
    /**
     * The name of this animation
     */
    final private String    myName;
    /**
     * Whether this Animation repeats
     */
    private boolean         myRepeat;

    /**
     * Constructor that takes four arguments
     * @param theAnimationName The name of the Animation
     * @param theTexture The libgdx Texture the Animation uses
     * @param theFrameCount The total number of frames of animation
     * @param theSpeed The speed to play this animation
     */
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


    /**
     * Copy Constructor that makes a shallow copy
     * @param other The Animation to be copied
     */
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




    /**
     * Updates the animation to show the next frame, depending on its speed
     * animation loops when it reaches the end
     */
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

    /**
     * @return Whether the animation has reached its end
     */
    public boolean hasEnded()  { return !myRepeat && myCurrentFrame >= (mySpeed * myFrameCount) - 1; }

    /**
     * @return The name of this Animation
     */
    public String getName()    { return myName; }

    /**
     * @return The size of a single animation frame
     */
    public Vec2 getSize()      { return mySize; }

    /**
     * @return The libgdx Sprite used for rendering
     */
    public Sprite getSprite()  { return mySprite; }

    /**
     * Sets the position of the texture adjusted to correspond to an Entity's position
     * @param x The x component of the Entity's position
     * @param y The y component of the Entity's position
     */
    public void setPos(float x, float y)
    {
        float adjustedX = x - (mySize.getMyX() - 64) / 2;
        float adjustedY = y - (mySize.getMyY() - 64) / 2;


        mySprite.setPosition(adjustedX, adjustedY);
    }

    /**
     * Sets the speed to play this animation
     * @param theSpeed The speed to play this animation
     */
    public void setSpeed(final long theSpeed)
    {
        if (theSpeed > 0 && mySpeed != 0)
        {
            mySpeed = theSpeed;
        }
    }

    /**
     * @return The speed to play this animation
     */
    public long getSpeed()
    {
        return mySpeed;
    }

    /**
     * @param theRepeat Whether the animation should repeat
     */
    public void setRepeat(final boolean theRepeat)
    {
        myRepeat = theRepeat;
    }

}
