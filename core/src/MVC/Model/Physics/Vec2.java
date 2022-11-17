package MVC.Model.Physics;

public class Vec2
{
private float myX;
private float myY;

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

    public boolean equals(final Vec2 myVector)
    {
        return myX == myVector.getMyX() && myY == myVector.getMyY();
    };

    public Vec2 add(final Vec2 myVector)
    {
        return new Vec2(this.myX + myVector.getMyX(),this.myY + myVector.getMyY());
    };

    public Vec2 add(final float theX, final float theY)
    {
        return new Vec2(this.myX + theX,this.myY + theY);
    };

    public Vec2 minus(final Vec2 myVector)
    {
        return new Vec2(this.myX - myVector.getMyX(),this.myY - myVector.getMyY());
    };

    public Vec2 divide(final float myFloat)
    {
        return new Vec2(this.myX/myFloat,this.myY/myFloat);
    };

    public Vec2 multiply(final float myFloat)
    {
        return new Vec2(this.myX*myFloat,this.myY*myFloat);
    };

    public void copy(final Vec2 myVector)
    {
       myY = myVector.getMyY();
       myX = myVector.getMyX();
    };

    public float computeDistance(final Vec2 myVector)
    {
        return (float) Math.sqrt(getDistanceSquared(myVector));
    };

    public float getDistanceSquared(final Vec2 myVector)
    {
        return (myY - myVector.getMyY())*(myY - myVector.getMyY()) +
                (myX - myVector.getMyX())*(myX - myVector.getMyX());
    };

    public float getMagnitudeSquared()
    {
        return (myX * myX) + (myY * myY);
    };

    //TODO:Research the efficiency, algorithm from StackOverflow
    public float quickInverseSquareRoot(float x)
    {
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x *= (1.5f - xhalf * x * x);
        return x;
    };

    public float crossProduct(final Vec2 myVector)
    {
        return myX * myVector.getMyY() - myY * myVector.getMyX();
    };

    public float dotProduct(final Vec2 myVector)
    {

        return myX * myVector.getMyX() + myY * myVector.getMyY();
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