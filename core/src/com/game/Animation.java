package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Animation
{
    // Instance fields
    private Sprite mySprite;
    private long myFrameCount;      // total number of frames of animation
    private long myCurrentFrame;    // the current frame of animation
    private long mySpeed;           // the speed to play this animation
    private Vec2 mySize;            // the size of the animation frame
    private String myName;

    // Constructor
    public Animation(final String animationName, final Texture texture, final int frameCount, final int speed)
    {
        myFrameCount = frameCount;
        myCurrentFrame = 0;
        mySpeed = speed;
        myName = animationName;
        mySize = Vec2(texture.getWidth() / frameCount, texture.getHeight());
        mySprite = new Sprite(texture, 0, 0, mySize.x, mySize.y);
        mySprite.setOrigin(mySize.x / 2, mySize.y / 2);

    }

    // updates the animation to show the next frame, depending on its speed
    // animation loops when it reaches the end
    void update()
    {
        myCurrentFrame++;

        if (mySpeed > 0)
        {
            long frame = (myCurrentFrame / mySpeed) % myFrameCount;
            mySprite.setRegion(Math.floor(frame) * mySize.x, 0, mySize.x, mySize.y);
        }
    }

    boolean hasEnded()  { return myCurrentFrame == (mySpeed * myFrameCount) - 1; }

    String getName()    { return myName; }

    Vec2 getSize()      { return mySize; }

    Sprite getSprite()  { return mySprite; }
}
