package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

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
    private double myBlockChance;
    private int myAgility;
    private double myHitChance;
    private Vec2 myPos;

    DungeonCharacter(final String theName, final String theCharacterType, final boolean theHero, final int theHitPoints, final int theMinDamageRange, final int theMaxDamageRange, final double theBlockChance,
                     final int theAgility, final double theHitChance, final Vec2 thePos)
    {
        this.myCharacterType = theCharacterType;
        this.myName = theName;
        this.isHero = theHero;
        this.myMinDamageRange = theMinDamageRange;
        this.myHitPoints = theHitPoints;
        this.myMaxDamageRange = theMaxDamageRange;
        this.myBlockChance = theBlockChance;
        this.myAgility = theAgility;
        this.myHitChance = theHitChance;
        this.myPos = thePos;
    }

    protected int attack(final DungeonCharacter theOpponent)
    {
        int damage = 0;

        if(this.myHitChance > theOpponent.getBlockChance()) //should hitChances be compared instead ??
        {
            Random rand = new Random();
            damage = rand.nextInt(theOpponent.myMinDamageRange, theOpponent.myMaxDamageRange);
        }

        //should this be here?
//        int attacks = this.myAgility/theOpponent.getAgility();
//        for (int i = 0; i < attacks; i++){
//            damage += attack(theOpponent);
//        }

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

    public double getBlockChance()
    {
        return this.myBlockChance;
    }

    public void setBlockChance(final double theBlockChance)
    {
        this.myBlockChance = theBlockChance;
    }

    public int getAgility()
    {
        return this.myAgility;
    }

    public void setAgility(final int theAgility)
    {
        this.myAgility = theAgility;
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
}
