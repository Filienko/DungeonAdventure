package MVC.Model.Physics;

import java.io.Serializable;

public class Vec2 implements Serializable
{
    /**
     * The x component
     */
    private float myX;
    /**
     * The y component
     */
    private float myY;

    /**
     * Constructor that takes one argument
     * @param thePos The initial x and y values of the Vector
     */
    public Vec2(final Vec2 thePos)
    {
        myX = thePos.getMyX();
        myY = thePos.getMyY();
    }

    /**
     * Checks if two vectors have equal values
     * @param vec1 One of two vectors
     * @param vec2 One of two vectors
     * @return Whether the two vectors are equals
     */
    public static boolean equals(final Vec2 vec1,final Vec2 vec2)
    {
        return vec1.getMyX()==vec2.getMyX() && vec1.getMyY()==vec2.getMyY();
    };

    /**
     * Constructor that takes zero arguments
     * and sets an initial value of zero to the x and y components
     */
    public Vec2()
    {
        this.myX = 0;
        this.myY = 0;
    };

    /**
     * Constructor that takes two arguments
     * @param myX The initial x value
     * @param myY The initial y value
     */
    public Vec2(final float myX, final float myY)
    {
        this.myX = myX;
        this.myY = myY;
    }

    /**
     * Checks whether the passed vector is equal to this vector
     * @param theVector The vector to be checked for equality
     * @return Whether the two vectors are equal
     */
    public boolean equals(final Vec2 theVector)
    {
        return myX == theVector.getMyX() && myY == theVector.getMyY();
    };

    /**
     * Adds this vector and the passed vector
     * @param theVector The vector to be summed with this vector
     * @return The sum of the two vectors
     */
    public Vec2 add(final Vec2 theVector)
    {
        return new Vec2(this.myX + theVector.getMyX(),this.myY + theVector.getMyY());
    };

    /**
     * Adds an x and y value to this vector
     * @param theX The x value to be added to this x value
     * @param theY The y value to be added to this y value
     * @return The sum of this vector and the two passed components
     */
    public Vec2 add(final float theX, final float theY)
    {
        myX = this.myX + theX;
        myY = this.myY + theY;
        return new Vec2(myX + theX,myY + theY);
    };

    /**
     * Subtracts the passed vector from this vector
     * @param theVector The vector to be subtracted from this vector
     * @return The difference between the two vectors
     */
    public Vec2 minus(final Vec2 theVector)
    {
        return new Vec2(this.myX - theVector.getMyX(),this.myY - theVector.getMyY());
    };

    /**
     * Divides this vector by a scalar
     * @param myFloat The scalar this vector is divided by
     * @return The quotient vector
     */
    public Vec2 divide(final float myFloat)
    {
        return new Vec2(this.myX/myFloat,this.myY/myFloat);
    };

    /**
     * Multiplies this vector by a scalar
     * @param myFloat The scalar this vector is multiplied by
     * @return The product vector
     */
    public Vec2 multiply(final float myFloat)
    {
        return new Vec2(this.myX*myFloat,this.myY*myFloat);
    };

    /**
     * Makes this vector a deep copy of the passed vector
     * @param theVector The vector to be copied
     */
    public void copy(final Vec2 theVector)
    {
       myY = theVector.getMyY();
       myX = theVector.getMyX();
    };

    /**
     * Computes the distance between this point and the passed point
     * @param thePoint The point to find the distance from
     * @return The distance between the two points
     */
    public float computeDistance(final Vec2 thePoint)
    {
        return (float) Math.sqrt(getDistanceSquared(thePoint));
    };

    /**
     * Computes the squared distance between this point and the passed point
     * @param thePoint The point to find the squared distance from
     * @return The squared distance between the two points
     */
    public float getDistanceSquared(final Vec2 thePoint)
    {
        return (myY - thePoint.getMyY())*(myY - thePoint.getMyY()) +
                (myX - thePoint.getMyX())*(myX - thePoint.getMyX());
    };

    /**
     * Computes the squared magnitude of this vector
     * @return The squared magnitude of this vector
     */
    public float getMagnitudeSquared()
    {
        return (myX * myX) + (myY * myY);
    };

    // Code by Greg Walsh for the Doom game engine.

    /**
     * Code by Greg Walsh for the Doom game engine
     * that computes an approximated inverse magnitude of this vector
     * @return The approximated inverse magnitude of this vector
     */
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

    /**
     * Computes the two-dimensional cross product between this vector and the passed vector
     * @param theVector The vector to find the cross product with
     * @return The cross product between the two vectors
     */
    public float crossProduct(final Vec2 theVector)
    {
        return myX * theVector.getMyY() - myY * theVector.getMyX();
    };

    /**
     * Computes the dot product between this vector and the passed vector
     * @param theVector The vector to find the dot product with
     * @return The dot product of the two vectors
     */
    public float dotProduct(final Vec2 theVector)
    {

        return myX * theVector.getMyX() + myY * theVector.getMyY();
    };

    /**
     * @return My X component
     */
    public float getMyX()
    {
        return myX;
    }

    /**
     * @param myX The X component
     */
    public void setMyX(final float myX)
    {
        this.myX = myX;
    }

    /**
     * @return My Y component
     */
    public float getMyY()
    {
        return myY;
    }

    /**
     * @param myY The Y component
     */
    public void setMyY(final float myY)
    {
        this.myY = myY;
    }

}