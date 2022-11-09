package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hero extends DungeonCharacter
{
    private static final Random rand = new Random();
    private final String myName;
    private final String myCharacterType;
    private final boolean isHero;
    private int myHealingPotions = 0;
    private int myVisionPotions = 0 ;
    private List<String> myPillars;
    private final int myHitPoints;

    public Hero(String theName, String theCharacterType, boolean theHero, int theHitPoints, final int theMinDamageRange,
                final int theMaxDamageRange, final double theBlockChance, final int theAgility, final double theHitChance,
                final Vec2 thePos)
    {
        super(theName, theCharacterType, true, theHitPoints, theMinDamageRange, theMaxDamageRange, theBlockChance, theAgility, theHitChance, thePos);
        this.myName = theName;
        this.myCharacterType = theCharacterType;
        this.isHero = true;
        this.myHitPoints = rand.nextInt(75,100);
        this.myPillars = new ArrayList<>();
    }

    protected void moveHero(final Vec2 theCoordinates)
    {
        super.setPos(theCoordinates);
    }

    protected void healHero(final Hero theHero, final int theHealingHP)
    {
        theHero.setHitPoints(theHero.getHitPoints()+theHealingHP);
    }

    @Override
    public String toString()
    {
        return "Name: " + myName +
                " {" +
                "myCharacterType + " + myCharacterType +
                ", Hero status = " + isHero +
                ", myHealingPotions = '" + myHealingPotions + '\'' +
                ", myVisionPotions = '" + myVisionPotions + '\'' +
                ", myPillars = " + myPillars.toString() +
                '}';
    }
}