package MVC.Model.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SuperSQLConnection
{
    public double myHealChance;
    public boolean myHero;
    public int myHitPoints;
    public String myCharacterType;
    public int myMinimumRange;
    public int myMaxDamageRange;
    public int myMaxSpeed;
    public double myHitChance;
    public float myX;
    public float myY;
    public float myVelocityX;
    public float myVelocityY;
    public int myMinHeal;
    public int myMaxHeal;

    public abstract void updateMonsterData(int n);

    public double getHealChance()
    {
        return myHealChance;
    }

    public void setHealChance(final double theHealChance)
    {
        myHealChance = theHealChance;
    }

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

    public int getMinimumRange()
    {
        return myMinimumRange;
    }

    public void setMinimumRange(final int theMinimumRange)
    {
        myMinimumRange = theMinimumRange;
    }

    public int getMaxDamageRange()
    {
        return myMaxDamageRange;
    }

    public void setMaxDamageRange(final int theMaxDamageRange)
    {
        myMaxDamageRange = theMaxDamageRange;
    }

    public int getMaxSpeed()
    {
        return myMaxSpeed;
    }

    public void setMaxSpeed(final int theMaxSpeed)
    {
        myMaxSpeed = theMaxSpeed;
    }

    public double getHitChance()
    {
        return myHitChance;
    }

    public void setHitChance(final double theHitChance)
    {
        myHitChance = theHitChance;
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

    public int getMinHeal()
    {
        return myMinHeal;
    }

    public void setMinHeal(final int theMinHeal)
    {
        myMinHeal = theMinHeal;
    }

    public int getMaxHeal()
    {
        return myMaxHeal;
    }

    public void setMaxHeal(final int theMaxHeal)
    {
        myMaxHeal = theMaxHeal;
    }
}