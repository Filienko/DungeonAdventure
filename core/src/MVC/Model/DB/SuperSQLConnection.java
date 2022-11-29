package MVC.Model.DB;

public abstract class SuperSQLConnection
{
    private boolean myHero;
    private int myHitPoints;
    private String myCharacterType;
    private int myDamage;
    private int myMaxSpeed;
    private float myX;
    private float myY;
    private float myVelocityX;
    private float myVelocityY;

    public abstract void updateMonsterData(int n);

    public boolean isHero()
    {
        return myHero;
    }

    public void setHero(final boolean theHero)
    {
        myHero = theHero;
    }

    public int getHitPoints()
    {
        return myHitPoints;
    }

    public void setHitPoints(final int theHitPoints)
    {
        myHitPoints = theHitPoints;
    }

    public String getCharacterType()
    {
        return myCharacterType;
    }

    public void setCharacterType(final String theCharacterType)
    {
        myCharacterType = theCharacterType;
    }

    public int getDamage()
    {
        return myDamage;
    }

    public void setDamage(final int theDamage)
    {
        myDamage = theDamage;
    }

    public int getMaxSpeed()
    {
        return myMaxSpeed;
    }

    public void setMaxSpeed(final int theMaxSpeed)
    {
        myMaxSpeed = theMaxSpeed;
    }

    public float getX()
    {
        return myX;
    }

    public void setX(final float theX)
    {
        myX = theX;
    }

    public float getY()
    {
        return myY;
    }

    public void setY(final float theY)
    {
        myY = theY;
    }

    public float getVelocityX()
    {
        return myVelocityX;
    }

    public void setVelocityX(final float theVelocityX)
    {
        myVelocityX = theVelocityX;
    }

    public float getVelocityY()
    {
        return myVelocityY;
    }

    public void setVelocityY(final float theVelocityY)
    {
        myVelocityY = theVelocityY;
    }
}
