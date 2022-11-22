package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

public class Monster extends DungeonCharacter implements Healable
{
    private final String myCharacterType;
    private final boolean isHero;
    private int myHitPoints;

    Monster(final int theHitPoints,
            String theCharacterType, final int theMinimumRange,
            final int theMaxDamageRange, final int theMaxSpeed,
            final Vec2 thePos, final Vec2 theVelocity)
    {
        super(theCharacterType, false, theHitPoints, theMinimumRange, theMaxDamageRange, theMaxSpeed,
                thePos, theVelocity);
        this.myCharacterType = theCharacterType;
        this.isHero = false;
        this.myHitPoints = theHitPoints;
    }

    protected int heal(Monster theMonster)
    {
        return healCharacter(theMonster);
    }

    protected void attack(Hero theHero){
        super.attack(theHero,super.getMyBoundingBox());
    }

    @Override
    public String toString()
    {
        return "Monster {" +
                "myCharacterType = '" + myCharacterType + '\'' +
                ", Hero status = " + isHero +
                ", myHitPoints = " + myHitPoints +
                '}';
    }
}