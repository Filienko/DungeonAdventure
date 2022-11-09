package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

public class Monster extends DungeonCharacter implements Healable
{
    private final String myCharacterType;
    private final String myName;
    private final boolean isHero;
    private final int myHitPoints;
    private double myHealChance;
    private final int myMinHeal;
    private final int myMaxHeal;

    Monster(final double theHealChance, final boolean theHero, final String theName, int theHitPoints, String theCharacterType, final int theMinimumRange,
            final int theMaxDamageRange, final double theBlockChance, final int theAgility, final double theHitChance,
            final Vec2 thePos, int theMinHeal, int theMaxHeal)
    {
        super(theName, theCharacterType, false, theHitPoints, theMinimumRange, theMaxDamageRange, theBlockChance, theAgility, theHitChance, thePos);
        this.myCharacterType = theCharacterType;
        this.myName = theName;
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