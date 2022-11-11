package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Animation
{
    // Instance fields
    private Sprite  mySprite;
    private long    myFrameCount;      // total number of frames of animation
    private long    myCurrentFrame;    // the current frame of animation
    private long    mySpeed;           // the speed to play this animation
    private Vec2    mySize;            // the size of the animation frame
    private String  myName;

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
    void update()
    {
        myCurrentFrame++;

        if (mySpeed > 0)
        {
            long frame = (myCurrentFrame / mySpeed) % myFrameCount;
            int leftX = (int) (Math.floor(frame) * mySize.getMyX());
            mySprite.setRegion(leftX, 0, (int) mySize.getMyX(), (int) mySize.getMyY());
        }
    }

    boolean hasEnded()  { return myCurrentFrame == (mySpeed * myFrameCount) - 1; }

    String getName()    { return myName; }

    Vec2 getSize()      { return mySize; }

    Sprite getSprite()  { return mySprite; }
}
