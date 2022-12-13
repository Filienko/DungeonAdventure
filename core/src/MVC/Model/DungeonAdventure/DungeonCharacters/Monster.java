package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Door;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
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
    private final Vec2 myHomePosition;

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

        myHomePosition = new Vec2();
        setMonsterType(theMonsterType);
        setHero(theHero);
        if(getMyEntityFactory().getAssets()!=null)
        {
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation(myMonsterType));
        }
        setMyKnockbackPower(3);
        setMyKnockbackLength(8);
    }

    @Override
    public void destroy()
    {
        var destroyPillar = true;
        for (var e : getMyEntityFactory().getEntities("door"))
        {
            if (e.getRoom().equals(getRoom()))
            {
                ((Door) e).decrementMonsterCounter();
                if(((Door) e).getMonsterCounter()<0){ destroyPillar = false; }
            }
        }

        if(destroyPillar)
        {
            for (var p : getMyEntityFactory().getEntities("pillar"))
            {
                Pillar pillar = (Pillar) p;
                if(!pillar.isBroken() && pillar.getRoom().equals(getRoom()))
                {
                    pillar.breakPillar();
                }
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

        boolean hasSight = heroRoom.equals(npcRoom);
        if (hasSight)
        {
            direction = myHero.getMyPos().minus(npcPosition);
            velocity = direction.multiply(direction.quickInverseMagnitude() * this.getMaxSpeed());
        }
        else if (myHomePosition.getDistanceSquared(npcPosition) > MY_AGGRESSION_DISTANCE)
        {
            direction = myHomePosition.minus(npcPosition);
            velocity = direction.multiply(direction.quickInverseMagnitude() * this.getMaxSpeed());
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
            myHero.knockback(this, getMyKnockbackPower(), getMyKnockbackLength());
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
        var allowedTiles = new ArrayList<Vec2>();
        allowedTiles.add(new Vec2(9,9));
        allowedTiles.add(new Vec2(9,1));
        allowedTiles.add(new Vec2(1,5));
        allowedTiles.add(new Vec2(17,5));

        //Starting at the middle row
        for (int i = 2; i <= 16; i++)
        {
            if(i==8||i==9||i==10) {continue;}
            allowedTiles.add(new Vec2(i,5));
        }

        for (int i = 5; i <= 13; i++)
        {
            if(i==8||i==9||i==10) {continue;}
            allowedTiles.add(new Vec2(i,4));
            allowedTiles.add(new Vec2(i,6));
        }

        for (int i = 6; i <= 12; i++)
        {
            allowedTiles.add(new Vec2(i,2));
            allowedTiles.add(new Vec2(i,3));
            allowedTiles.add(new Vec2(i,7));
            allowedTiles.add(new Vec2(i,8));
        }

        var monsterPos = allowedTiles.get(new Random().nextInt(0,allowedTiles.size()));
        setMyPos(Physics.getPosition((int) theRoom.getMyX(), (int) theRoom.getMyY(),
                (int) monsterPos.getMyX(), (int) monsterPos.getMyY()));
        myHomePosition.copy(getMyPos());
    }

    public void setRoom(final Vec2 theRoom, int theRatPos)
    {
        super.setRoom(theRoom);
        var allowedTiles = new ArrayList<Vec2>();
        allowedTiles.add(new Vec2(9,9));
        allowedTiles.add(new Vec2(9,1));
        allowedTiles.add(new Vec2(1,5));
        allowedTiles.add(new Vec2(17,5));
        var monsterPos = allowedTiles.get(theRatPos);
        setMyPos(Physics.getPosition((int) theRoom.getMyX(), (int) theRoom.getMyY(),
                (int) monsterPos.getMyX(), (int) monsterPos.getMyY()));
        myHomePosition.copy(getMyPos());
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