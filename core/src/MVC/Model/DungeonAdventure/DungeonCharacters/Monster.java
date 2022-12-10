package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Door;
import MVC.Model.DungeonItems.Door;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Monster extends DungeonCharacter
{
    /**
     * Hero status that tells that this DungeonCharacter is a Monster.
     */
    private final static boolean MY_HERO_STATUS = false;

    //TODO:Add variability to the monster's aggression
    private final static int MY_AGGRESSION_DISTANCE = 25;

    /**
     * Monster's default position.
     */
    private Vec2 myHomePosition;

    /**
     * The specific Monster type.
     */
    private String myMonsterType; //should this be changed back to final?

    /**
     * The Monster's Hero opponent.
     */
    private Hero myHero; //should this be changed back to final?

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

        setMonsterType(theMonsterType);
        setHero(theHero);
        //setMyAnimation(getMyEntityFactory().getAssets().getAnimation(myMonsterType));
    }

    @Override
    public void destroy()
    {
        for (var e : getMyEntityFactory().getDoors())
        {
            if (e.getRoom().equals(getRoom()))
            {
                var d = (Door)e;
                d.decrementMonsterCounter();
            }
        }
        super.destroy();
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
                (float) Math.floor(npcPosition.getMyY() / 704));
        Vec2 direction;
        Vec2 velocity = new Vec2();
        boolean hasSight = false;
        if(heroRoom.equals(npcRoom))
        {
            hasSight = true;
            for (var e: getMyEntityFactory().getEntities())
            {
                var notMonster = !e.getType().contains("ogre") && !e.getType().contains("rat")
                        && !e.getType().contains("knight") && !e.getType().contains("gremlin");
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
//                        // If there's an intersection then the npc does not have sight on the player
//                        if (Physics.entityIntersect(heroPosition, npcPosition, e))
//                        {
//                            hasSight = false;
//                            break;
//                        }
                    }
                }
            }
        }

        if (hasSight)
        {
            direction = myHero.getMyPos().minus(npcPosition);
            velocity = direction.multiply(direction.quickInverseMagnitude() * this.getMaxSpeed());
        }
        else if (myHomePosition.getDistanceSquared(npcPosition) > MY_AGGRESSION_DISTANCE)
        {
            direction= myHomePosition.minus(npcPosition);
            velocity= direction.multiply(direction.quickInverseMagnitude() * this.getMaxSpeed());
        }
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

        Vec2 overlap;
        overlap = Physics.getOverlap(myHero, this);
        if (overlap.getMyX() >= 0 && overlap.getMyY() >= 0 && !myHero.isInvincibility())
        {
            myHero.applyDamage(attack());
            if (myHero.getHitPoints() <= 0)
            {
                myHero.destroy();
            }
            myHero.setInvincibility(true,45);
        }
    }

    /**
     * This method retrieves the Monster's type.
     * @return The Monster's type.
     */
    public String getMonsterType()
    {
        return myMonsterType;
    }

    /**
     * This method sets the Monster's type
     * @param theMonsterType The Monster's new type.
     */
    private void setMonsterType(final String theMonsterType)
    {
        if (theMonsterType != null)
        {
            myMonsterType = theMonsterType;
        }
    }

    @Override
    public void setRoom(final Vec2 theRoom)
    {
        super.setRoom(theRoom);
        myHomePosition = Physics.getPosition((int) theRoom.getMyX(), (int) theRoom.getMyY(),
                (int) getMyPos().getMyX(),(int) getMyPos().getMyY());
        setMyPos(myHomePosition);
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

    private void setHero(final Hero theHero)
    {
        if (theHero != null)
        {
            myHero = theHero;
        }
    }
}