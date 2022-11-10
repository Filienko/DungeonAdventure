package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

public class Monster extends DungeonCharacter implements Healable
{
    private final String myCharacterType;
    private final boolean isHero;
    private final int myHitPoints;
    private double myHealChance;
    private final int myMinHeal;
    private final int myMaxHeal;

    Monster(final double theHealChance, final String theName, int theHitPoints,
            String theCharacterType, final int theMinimumRange,
            final int theMaxDamageRange, final int theMaxSpeed, final double theHitChance,
            final Vec2 thePos, final Vec2 theVelocity, final int theMinHeal, final int theMaxHeal)
    {
        super(theName, theCharacterType, false, theHitPoints, theMinimumRange, theMaxDamageRange, theMaxSpeed,
                theHitChance, thePos, theVelocity);
        this.myCharacterType = theCharacterType;
        this.isHero = false;
        this.myHitPoints = theHitPoints;
        this.myHealChance = theHealChance;
        this.myMaxHeal = theMaxHeal;
        this.myMinHeal = theMinHeal;
    }

    public int heal(Monster theMonster)
    {
        return healCharacter(theMonster);
    }

    protected double getMyHealChance()
    {
        return myHealChance;
    }

    private void setMyHealChance(final double myHealChance)
    {
        this.myHealChance = myHealChance;
    }

    protected int getMinHeal()
    {
        return myMinHeal;
    }

    protected int getMaxHeal()
    {
        return myMaxHeal;
    }

    @Override
    public String toString()
    {
        return "Monster {" +
                "myCharacterType = '" + myCharacterType + '\'' +
                ", Hero status = " + isHero +
                ", myHitPoints = " + myHitPoints +
                ", myHealChance = " + myHealChance +
                '}';
    }
}