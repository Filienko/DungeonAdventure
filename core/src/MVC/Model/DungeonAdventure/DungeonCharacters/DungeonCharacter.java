package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.math.collision.BoundingBox;

import java.util.Random;

public abstract class DungeonCharacter extends Entity
{
    private final String myCharacterType;
    private final String myName;
    private final boolean isHero;
    //private int myHealPoints;
    private int myHitPoints;
    private int myMinDamageRange;
    private int myMaxDamageRange;
    private int myMaxSpeed;
    private double myHitChance;
    private Vec2 myPos;
    private Vec2 myVelocity;

    DungeonCharacter(final String theName, final String theCharacterType, final boolean theHero, final int theHitPoints,
                     final int theMinDamageRange, final int theMaxDamageRange, final int theMaxSpeed,
                     final double theHitChance, final Vec2 thePos, final Vec2 theVelocity)
    {
        this.myCharacterType = theCharacterType;
        this.myName = theName;
        this.isHero = theHero;
        this.myMinDamageRange = theMinDamageRange;
        this.myHitPoints = theHitPoints;
        this.myMaxDamageRange = theMaxDamageRange;
        this.myMaxSpeed = theMaxSpeed;
        this.myHitChance = theHitChance;
        this.myPos = thePos;
        this.myVelocity = theVelocity;
    }

    protected int attack(final DungeonCharacter theOpponent, final Vec2 damageArea)
    {

        int damage = 0;

        theOpponent.applyDamage(damage);

        return damage;
    }

    public void applyDamage(final int theDamage) {
        this.setHitPoints(this.getHitPoints() - theDamage);
    }

    public String getMyCharacterType() { return this.myCharacterType; }

    public boolean getHeroStatus()
    {
        return this.isHero;
    }

    public int getHitPoints()
    {
        return this.myHitPoints;
    }

    public void setHitPoints(final int theHitPoints)
    {
        this.myHitPoints = theHitPoints;
    }

//    public int getHealPoints()
//    {
//        return this.myHealPoints;
//    }
//
//    public void setHealPoints(final int theHealPoints)
//    {
//        this.myHealPoints = theHealPoints;
//    }

    public int getMinDamageRange()
    {
        return this.myMinDamageRange;
    }

    public void setMinDamageRange(final int theMinDamageRange)
    {
        this.myMinDamageRange = theMinDamageRange;
    }

    public int getMaxDamageRange()
    {
        return this.myMaxDamageRange;
    }

    public void setMaxDamageRange(final int theMaxDamageRange)
    {
        this.myMaxDamageRange = theMaxDamageRange;
    }

    public int getMaxSpeed()
    {
        return this.myMaxSpeed;
    }

    public void setMaxSpeed(final int theMaxSpeed)
    {
        this.myMaxSpeed = theMaxSpeed;
    }

    public double getHitChance()
    {
        return this.myHitChance;
    }

    public void setHitChance(final double theHitChance)
    {
        this.myHitChance = theHitChance;
    }

    public Vec2 getPos()
    {
        return this.myPos;
    }

    public void setPos(final Vec2 thePos)
    {
        this.myPos = thePos;
    }

    private String getCharacterType()
    {
        return myCharacterType;
    }

    private String getName()
    {
        return myName;
    }

    private boolean isHero()
    {
        return isHero;
    }

    private Vec2 getVelocity()
    {
        return myVelocity;
    }

    private void setVelocity(final Vec2 theVelocity)
    {
        myVelocity = theVelocity;
    }
}
