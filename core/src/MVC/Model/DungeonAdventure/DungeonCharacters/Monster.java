package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Vec2;

public class Monster extends DungeonCharacter
{
    /**
     * Hero status that tells that this DungeonCharacter is a Monster.
     */
    private final static boolean MY_HERO_STATUS = false;


    //TODO:Add variability to the monster's aggression
    private final static int MY_AGGRESSION_DISTANCE = 10;

    /**
     * The specific Monster type.
     */
    private final String myCharacterType;

    /**
     * The Monster's hit points (health).
     */
    private final int myHitPoints;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame;

    /**
     * Monster constructor that calls its parent constructor to initialize the Monster's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, chance of healing, and maximum/minimum hit points it can restore.
     * @param theHitPoints The Monster's hit points.
     * @param theCharacterType The Monster's type.
     * @param theDamage The amount of damage Monster can inflict.
     * @param theMaxSpeed The Monster's maximum speed.
     * @param thePos The Monster's location.
     * @param theVelocity The Monster's velocity.
     */
    public Monster(final String theCharacterType, final int theHitPoints, final int theDamage, final int theMaxSpeed,
                   final Vec2 thePos, final Vec2 theVelocity, final EntityFactory theEntityFactory)
    {
        super(theCharacterType, MY_HERO_STATUS, theHitPoints, theDamage, theMaxSpeed, thePos, theVelocity, theEntityFactory);
        myCharacterType = theCharacterType;
        myEntityFactory = theEntityFactory;
        myHitPoints = theHitPoints; //necessary?
        myCurrentFrame = 0;
    }

    protected void attack(final Hero theHero) {
        super.attack(theHero, super.getMyBoundingBox());
    }

    @Override
    public void update()
    {
        //Replace new Vec2 with hero's position
        if((getMyBoundingBox().computeDistance(new Vec2())<MY_AGGRESSION_DISTANCE))
        {
            //Interact with the Hero's velocity
            setVelocity(new Vec2());
        }
        super.update();

        //movement();
        myCurrentFrame++;
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