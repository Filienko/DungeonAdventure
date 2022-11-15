package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.math.collision.BoundingBox;

import java.util.Random;

public abstract class DungeonCharacter extends Entity
{
    private final String myCharacterType;
    private final boolean isHero;
    private int myHitPoints;
    private int myMinDamageRange;
    private int myMaxDamageRange;
    private int myMaxSpeed;
    private Vec2 myPos;
    private Vec2 myVelocity;

    DungeonCharacter(final String theCharacterType, final boolean theHero, final int theHitPoints,
                     final int theMinDamageRange, final int theMaxDamageRange, final int theMaxSpeed, final Vec2 thePos,
                     final Vec2 theVelocity)
    {
        this.myCharacterType = theCharacterType;
        this.isHero = theHero;
        this.myMinDamageRange = theMinDamageRange;
        this.myHitPoints = theHitPoints;
        this.myMaxDamageRange = theMaxDamageRange;
        this.myMaxSpeed = theMaxSpeed;
        this.myPos = thePos;
        this.myVelocity = theVelocity;
    }

    protected int attack(final DungeonCharacter theOpponent, final Vec2 damageArea)
    {
        int damage = 10;

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

    public Vec2 getPos()
    {
        return this.myPos;
    }

    public void setPos(final Vec2 thePos)
    {
        this.myPos = thePos;
    }

<<<<<<< HEAD
    protected String getCharacterType()
=======
    public String getCharacterType()
>>>>>>> remote_main
    {
        return myCharacterType;
    }

<<<<<<< HEAD
    protected String getName()
    {
        return myName;
    }

    protected boolean isHero()
=======
    public boolean isHero()
>>>>>>> remote_main
    {
        return isHero;
    }

<<<<<<< HEAD
    protected Vec2 getVelocity()
=======
    public Vec2 getVelocity()
>>>>>>> remote_main
    {
        return myVelocity;
    }

    public void setVelocity(final Vec2 theVelocity)
    {
        myVelocity = theVelocity;
    }

    public void setVelocity(final float x, final float y) { myVelocity.setMyX(x); myVelocity.setMyY(y); }


}
