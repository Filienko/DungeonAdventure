package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Weapon.Attackable;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hero extends DungeonCharacter
{
    private Attackable myWeapon = new Sword();;
    private static final Random rand = new Random();
    private final String myName;
    private final String myCharacterType;
    private final boolean isHero;
    private int myHealingPotions = 0;
    private int myVisionPotions = 0 ;
    private List<String> myPillars;
    private final int myHitPoints;

    public Hero(String theName, String theCharacterType, int theHitPoints, final int theMinDamageRange,
                final int theMaxDamageRange, final int theMaxSpeed, final double theHitChance,
                final Vec2 thePos, final Vec2 theVelocity)
    {
        super(theName, theCharacterType, true, theHitPoints, theMinDamageRange, theMaxDamageRange, theMaxSpeed,
                theHitChance, thePos, theVelocity);
        this.myName = theName;
        this.myCharacterType = theCharacterType;
        this.isHero = true;
        this.myHitPoints = theHitPoints;
        this.myPillars = new ArrayList<>();
    }

    public void moveHero(final Vec2 theCoordinates)
    {
        super.setPos(theCoordinates);
    }

    public void healHero(final Hero theHero, final int theHealingHP)
    {
        theHero.setHitPoints(theHero.getHitPoints()+theHealingHP);
    }

    public Attackable getWeapon()
    {
        return myWeapon;
    }

    public void setWeapon(final Attackable theWeapon)
    {
        myWeapon = theWeapon;
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

    public String getName()
    {
        return myName;
    }

    public String getCharacterType()
    {
        return myCharacterType;
    }

    public boolean isHero()
    {
        return isHero;
    }

    public int getHealingPotions()
    {
        return myHealingPotions;
    }

    public int getVisionPotions()
    {
        return myVisionPotions;
    }

    public List<String> getPillars()
    {
        return myPillars;
    }

    @Override
    public int getHitPoints()
    {
        return myHitPoints;
    }

    public void setHealingPotions(final int theHealingPotions)
    {
        myHealingPotions = theHealingPotions;
    }

    public void setVisionPotions(final int theVisionPotions)
    {
        myVisionPotions = theVisionPotions;
    }

    public void setPillars(final List<String> thePillars)
    {
        myPillars = thePillars;
    }
}