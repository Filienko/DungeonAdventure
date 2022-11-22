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
    private final String myName;
    private final String myCharacterType;
    private final boolean isHero;
    private int myHealingPotions = 0;
    private int myVisionPotions = 0 ;
    private List<String> myPillars;
    private int myHitPoints;

    public Hero(String theName, String theCharacterType, int theHitPoints, final int theMinDamageRange,
                final int theMaxDamageRange, final int theMaxSpeed, final double theHitChance,
                final Vec2 thePos, final Vec2 theVelocity)
    {
        super(theCharacterType, true, theHitPoints, theMinDamageRange, theMaxDamageRange, theMaxSpeed,
                thePos, theVelocity);
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

    public Attackable getWeapon()
    {
        return myWeapon;
    }

    public void setWeapon(final Attackable theWeapon)
    {
        myWeapon = theWeapon;
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