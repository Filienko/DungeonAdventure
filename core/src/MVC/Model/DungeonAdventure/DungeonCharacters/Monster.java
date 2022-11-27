package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

public class Monster extends DungeonCharacter
{
    /**
     * Hero status that tells that this DungeonCharacter is a Monster.
     */
    private final static boolean MY_HERO_STATUS = false; // should just be static ?

    /**
     * The specific Monster type.
     */
    private final String myCharacterType;

    /**
     * The Monster's hit points (health).
     */
    private final int myHitPoints;

    //^^ should this be deleted or should maxSpeed, min/max damage range be fields

    /**
     * Monster constructor that calls its parent constructor to initialize the Monster's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, chance of healing, and maximum/minimum hit points it can restore.
     * @param theHitPoints The Monster's hit points.
     * @param theCharacterType The Monster's type.
     * @param theMinimumRange The minimum amount of damage the Monster can inflict.
     * @param theMaxDamageRange The maximum amount of damage the Monster can inflict.
     * @param theMaxSpeed The Monster's maximum speed.
     * @param thePos The Monster's location.
     * @param theVelocity The Monster's velocity.
     */
    public Monster(final String theCharacterType, final int theHitPoints, final int theMinimumRange,
            final int theMaxDamageRange, final int theMaxSpeed, final Vec2 thePos, final Vec2 theVelocity)
    {
        super(theCharacterType, false, theHitPoints, theMinimumRange, theMaxDamageRange, theMaxSpeed, thePos, theVelocity);
        this.myCharacterType = theCharacterType;
        //this.myHeroStatus = false;
        this.myHitPoints = theHitPoints; //necessary?
        //is hero status necessary ?? should it just be a static final field?
    }

    protected void attack(Hero theHero){
        super.attack(theHero,super.getMyBoundingBox());
    }

    @Override
    public String toString()
    {
        return "Monster {" +
                "myCharacterType = '" + myCharacterType + '\'' +
                ", Hero status = " + MY_HERO_STATUS +
                ", myHitPoints = " + myHitPoints +
                '}';
    }
}