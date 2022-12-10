package MVC.Model.Physics;

import java.io.Serializable;

public class Vec2 implements Serializable
{
private float myX;
private float myY;

    public Vec2(final Vec2 thePos)
    {
        myX = thePos.getMyX();
        myY = thePos.getMyY();
    }

    public static boolean equals(final Vec2 vec1,final Vec2 vec2)
    {
        return vec1.getMyX()==vec2.getMyX() && vec1.getMyY()==vec2.getMyY();
    };

    public Vec2()
    {
        this.myX = 0;
        this.myY = 0;
    };

    public Vec2(final float myX, final float myY)
    {
        this.myX = myX;
        this.myY = myY;
    }

    public boolean equals(final Vec2 theVector)
    {
        return myX == theVector.getMyX() && myY == theVector.getMyY();
    };

    public Vec2 add(final Vec2 theVector)
    {
        return new Vec2(this.myX + theVector.getMyX(),this.myY + theVector.getMyY());
    };

    public Vec2 add(final float theX, final float theY)
    {
        myX = this.myX + theX;
        myY = this.myY + theY;
        return new Vec2(myX + theX,myY + theY);
    };

    public Vec2 minus(final Vec2 theVector)
    {
        return new Vec2(this.myX - theVector.getMyX(),this.myY - theVector.getMyY());
    };

    public Vec2 divide(final float myFloat)
    {
        return new Vec2(this.myX/myFloat,this.myY/myFloat);
    };

    public Vec2 multiply(final float myFloat)
    {
        return new Vec2(this.myX*myFloat,this.myY*myFloat);
    };

    public void copy(final Vec2 theVector)
    {
       myY = theVector.getMyY();
       myX = theVector.getMyX();
    };

    public float computeDistance(final Vec2 theVector)
    {
        return (float) Math.sqrt(getDistanceSquared(theVector));
    };

    public float getDistanceSquared(final Vec2 theVector)
    {
        return (myY - theVector.getMyY())*(myY - theVector.getMyY()) +
                (myX - theVector.getMyX())*(myX - theVector.getMyX());
    };

    public float getMagnitudeSquared()
    {
        return (myX * myX) + (myY * myY);
    };

    //TODO:Research the efficiency, algorithm from StackOverflow
    public float quickInverseMagnitude()
    {
        var x = getMagnitudeSquared();
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x *= (1.5f - xhalf * x * x);
        return x;
    };

    public float crossProduct(final Vec2 theVector)
    {
        return myX * theVector.getMyY() - myY * theVector.getMyX();
    };

    public float dotProduct(final Vec2 theVector)
    {

        return myX * theVector.getMyX() + myY * theVector.getMyY();
    };

    public float getMyX()
    {
        return myX;
    }

    public void setMyX(final float myX)
    {
        this.myX = myX;
    }

    public float getMyY()
    {
        return myY;
    }

    public void setMyY(final float myY)
    {
        this.myY = myY;
    }

}