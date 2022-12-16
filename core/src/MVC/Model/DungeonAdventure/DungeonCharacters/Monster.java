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
    /**
     * The maximum distance an enemy can be from a monster to still take damage from them.
     */
    private final static int MY_AGGRESSION_DISTANCE = 25;

    /**
     * The specific Monster type.
     */
    private String myMonsterType;

    /**
     * The Monster's Hero opponent.
     */
    private Hero myHero;

    /**
     * Monster constructor that calls its parent constructor to initialize the Monster's name, character type, hero status, hit points,
     * minimum/maximum damage it can inflict, max speed, position, chance of healing, and maximum/minimum hit points it can restore.
     * @param theMonsterType The Monster's type.
     * @param theHitPoints The Monster's hit points.
     * @param theDamage The amount of damage Monster can inflict.
     * @param theMaxSpeed The Monster's maximum speed.
     * @param thePos The Monster's location.
     * @param theVelocity The Monster's velocity.
     */
    public Monster(final String theMonsterType, final int theHitPoints, final int theDamage, final int theMaxSpeed,
                  final Vec2 thePos, final Vec2 theVelocity, final Vec2 theDimensions,
                   final EntityFactory theEntityFactory)
    {
        super("Monster", MY_HERO_STATUS, theHitPoints, theDamage, theMaxSpeed,
                theDimensions, thePos, theVelocity, theEntityFactory);

        setMonsterType(theMonsterType);
        setHero(getMyEntityFactory().getHero());
        setMaxSpeed(theMaxSpeed);
        if(getMyEntityFactory().getAssets()!=null)
        {
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation(myMonsterType));
        }
        setMyKnockBackPower(3);
        setMyKnockBackLength(8);
    }

    /**
     * This method specifies what should be done when a monster is destroyed (killed in combat).
     */
    @Override
    public void destroy()
    {
        var destroyPillar = true;
        for (var e : getMyEntityFactory().getEntities("door"))
        {
            if (e.getRoom().equals(getRoom()))
            {
                ((Door) e).decrementMonsterCounter();
                if(((Door) e).getMonsterCounter()<0)
                {
                    destroyPillar = false;
                }

            }
        }

        if(destroyPillar)
        {
            for (var p : getMyEntityFactory().getEntities("pillar"))
            {
                if (p.getRoom().equals(getRoom()))
                {
                    Pillar thePillar = (Pillar) p;
                    thePillar.decrementMonsterCounter();
                    if (!thePillar.isBroken())
                    {
                        thePillar.breakPillar();
                    }
                }
            }
        }

        super.destroy();
    }

    /**
     * This method updates information about the Monster every frame.
     */
    @Override
    public void update()
    {
        super.update();
        incrementCurrentFrame();
    }

    /**
     * This method determines how much damage a Monster inflicts during its attack.
     * @return The amount of damage the Monster inflicts on a Hero.
     */
    @Override
    public int attack()
    {
        return getDamage();
    }

    /**
     * This method specifies the Monster's movement behavior.
     */
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
            myHero.knockBack(this, getMyKnockBackPower(), getMyKnockBackLength());
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
     * This method retrieves the Monster's aggression distance, which is ..
     * @return The Monster's aggression distance.
     */
    public int getAggressionDistance()
    {
        return MY_AGGRESSION_DISTANCE;
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

    /**
     * This method sets the Monster's current Room.
     * @param theRoom The Monster's new Room, expressed as a Vec2.
     */
    @Override
    public void setRoom(final Vec2 theRoom)
    {
        super.setRoom(theRoom);
        var allowedTiles = new ArrayList<Vec2>();
        //Starting at the middle row
        for (int i = 6; i < 12; i++)
        {
            if(i==8||i==9||i==10) {continue;}
            allowedTiles.add(new Vec2(i,5));
        }

        for (int i = 6; i < 12; i++)
        {
            if(i==8||i==9||i==10) {continue;}
            allowedTiles.add(new Vec2(i,4));
            allowedTiles.add(new Vec2(i,6));
        }

        for (int i = 6; i < 12; i++)
        {
            allowedTiles.add(new Vec2(i,2));
            allowedTiles.add(new Vec2(i,3));
            allowedTiles.add(new Vec2(i,7));
        }

        var monsterPos = allowedTiles.get(new Random().nextInt(0,allowedTiles.size()));
        setMyPos(Physics.getPosition((int) theRoom.getMyX(), (int) theRoom.getMyY(),
                (int) monsterPos.getMyX(), (int) monsterPos.getMyY()));
        setHomePosition(getMyPos());
    }

    /**
     * This method sets the Monster's current Room, when there are rats in the Room.
     * @param theRoom The Monster's new Room, expressed as a Vec2.
     * @param theRatPos The position of the rats in the Room.
     */
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
        setHomePosition(getMyPos());
    }

    /**
     * @param theHero The hero character of the Scene to which this monster belongs
     */
    private void setHero(final Hero theHero)
    {
        if (theHero != null)
        {
            myHero = theHero;
        }
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