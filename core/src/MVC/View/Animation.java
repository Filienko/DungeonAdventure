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
    final private long      mySpeed;           // the speed to play this animation
    final private Vec2      mySize;            // the size of the animation frame
    final private String    myName;

    // Constructor
    public Animation(final String animationName, final Texture texture, final int frameCount, final int speed)
    {
        myFrameCount    = frameCount;
        myCurrentFrame  = 0;
        mySpeed         = speed;
        myName          = animationName;
        mySize          = new Vec2((float) texture.getWidth() / frameCount, texture.getHeight());
        mySprite        = new Sprite(texture, 0, 0, (int) mySize.getMyX(), (int) mySize.getMyY());
        mySprite.setOrigin(mySize.getMyX() / 2, mySize.getMyY() / 2);
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

    public boolean hasEnded()  { return myCurrentFrame == (mySpeed * myFrameCount) - 1; }

    public String getName()    { return myName; }

    public Vec2 getSize()      { return mySize; }

    public Sprite getSprite()  { return mySprite; }

    public void setPos(float x, float y)
    {
        float adjustedX = x;
        float adjustedY = y;

        if (myName.equals(""))
        mySprite.setPosition(adjustedX, adjustedY);
    }

}
