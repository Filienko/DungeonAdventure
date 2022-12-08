package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Monster extends DungeonCharacter
{
    /**
     * Monster's default position.
     */
    private final Vec2 myHomePosition;

    /**
     * Hero status that tells that this DungeonCharacter is a Monster.
     */
    private final static boolean MY_HERO_STATUS = false;


    //TODO:Add variability to the monster's aggression
    private final static int MY_AGGRESSION_DISTANCE = 25;

    /**
     * The specific Monster type.
     */
    private final String myMonsterType;

    /**
     * The specific Monster type.
     */
    private final Hero myHero;

    /**
     * Monster constructor that calls its parent constructor to initialize the Monster's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, chance of healing, and maximum/minimum hit points it can restore.
     * @param theMonsterType The Monster's type.
     * @param theHitPoints The Monster's hit points.
     * @param theDamage The amount of damage Monster can inflict.
     * @param theMaxSpeed The Monster's maximum speed.
     * @param thePos The Monster's location.
     * @param theVelocity The Monster's velocity.
     * @param theHero the Hero associated with the Monsters
     */
    public Monster(final String theMonsterType, final int theHitPoints, final int theDamage, final int theMaxSpeed,
                  final Vec2 thePos, final Vec2 theVelocity, final Hero theHero, final Vec2 theDimensions,
                   final EntityFactory theEntityFactory)
    {
        super("Monster", MY_HERO_STATUS, theHitPoints, theDamage, theMaxSpeed,
                theDimensions, thePos, theVelocity, theEntityFactory);
        myHomePosition = thePos;
        myMonsterType = theMonsterType;
        myHero = theHero;
        setInvincibility(false);
        setMyAnimation(getMyEntityFactory().getAssets().getAnimation(myMonsterType));
    }

    @Override
    public void update()
    {
        super.update();
        incrementCurrentFrame();
    }

    @Override
    public int attack()
    {
        return getDamage();
    }

    @Override
    public void movement()
    {
        var heroPosition = myHero.getMyPos();
        float roomX = (float) Math.floor(heroPosition.getMyX() / 1216);
        float roomY = (float) Math.floor(heroPosition.getMyY() / 704);
        var heroRoom = new Vec2(roomX, roomY);

        Vec2 npcPosition = this.getMyPos();
        Vec2 npcRoom = new Vec2((float) Math.floor(npcPosition.getMyX() / 1216),
                (float) Math.floor(npcPosition.getMyX() / 704));
        Vec2 direction;
        Vec2 velocity = new Vec2();
        boolean hasSight = false;
        if(heroRoom.equals(npcRoom))
        {
            hasSight = true;
            for (var e: getMyEntityFactory().getEntities())
            {
                var notMonster = !e.getType().contains("Ogre") && !e.getType().contains("Rat")
                        && !e.getType().contains("Knight") && !e.getType().contains("Gremlin");
                var notHero = !e.getType().contains("Priestess") && !e.getType().contains("Warrior")
                        && !e.getType().contains("Thief");
                var notPotion = !e.getType().contains("Potion");
                var notPit = !e.getType().contains("Pit");
                var isWall = e.getType().contains("Door");
                var isDoor = e.getType().contains("Wall");

                if(notHero)
                {
                    Vec2 ePosition = e.getMyPos();
                    Vec2 eRoom = new Vec2((float) Math.floor(ePosition.getMyX()/1216),
                            (float) Math.floor(ePosition.getMyX()/704));
                    if(eRoom.equals(npcRoom))
                    {
                        var distance = myHero.getMyPos().computeDistance(myHomePosition);
                        // If there's an intersection then the npc does not have sight on the player
                        if (Physics.entityIntersect(heroPosition, npcPosition, e) || MY_AGGRESSION_DISTANCE >= distance)
                        {
                            hasSight = false;
                        }
                    }
                }
            }
        }

        if (hasSight)
        {
            direction = myHero.getMyPos().minus(npcPosition);
        }
        else
        {
            direction= myHomePosition.minus(npcPosition);
        }
        velocity = direction.multiply(direction.quickInverseMagnitude() * this.getMaxSpeed());

        setVelocity(velocity);
        setMyPreviousPos(getMyPos());
        updateMyPos(getVelocity());
    }

    /**
     * Analyzes whether the collision occurred between two objects and performs certain associated logic
     */
    @Override
    public void collide()
    {
        super.collide();
        var overlap = Physics.getOverlap(this, getMyEntityFactory().getHero());
        System.out.println();
        if (Math.abs(overlap.getMyX()) > 0 && Math.abs(overlap.getMyY()) > 0
                && !getMyEntityFactory().getHero().isInvincibility())
        {
            getMyEntityFactory().getHero().applyDamage(attack());
            if (getMyEntityFactory().getHero().getHitPoints() <= 0)
            {
                getMyEntityFactory().getHero().destroy();
            }
            getMyEntityFactory().getHero().setInvincibility(true,15);
        }
    }

    public String getMonsterType()
    {
        return myMonsterType;
    }

    @Override
    public String toString()
    {
        return "Monster {" +
                "myMonsterType = '" + myMonsterType + '\'' +
                ", Hero status = " + MY_HERO_STATUS +
                ", myHitPoints = " + getHitPoints() +
                '}';
    }

}