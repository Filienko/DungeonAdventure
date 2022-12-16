package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Worm (final boss).
 * @author
 * @version
 */
public class Worm extends DungeonCharacter
{
    final private ArrayList<Body> mySegments;
    final private Tail myTail;
    final private ArrayList<Vec2> myControlPoints;
    final private ArrayList<Vec2> myPath;
    private float myTParam;
    private double myTurnSpeed;
    private boolean myTurnDirection;
    private double myAngle;
    private long myNextCourseChange;
    final private Random myRand;
    private boolean mySpawned;

    /**
     *
     * @param thePos The Worm's position.
     * @param theEntityFactory The Entity Factory that generates the Worm.
     */
    public Worm(final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        super("Worm", false, 1,
        0, 0, new Vec2(0, 0), thePos,
        new Vec2(0, 0), theEntityFactory);

        myControlPoints = new ArrayList<>();
        myRand = new Random();
        setRoom(Physics.getRoom(thePos.getMyX(), thePos.getMyY()));
        myTParam = 0;
        mySegments = new ArrayList<>();
        mySegments.add(new Body(new Vec2( 0, 0), getMyPos(), getMyEntityFactory(), this, 16));
        mySegments.add(new Body(new Vec2( 0, 0), getMyPos(), getMyEntityFactory(),  this, 28));
        mySegments.add(new Body(new Vec2( 0, 0), getMyPos(), getMyEntityFactory(), this, 39));
        myTail = new Tail(new Vec2(32, 32), getMyPos(), getMyEntityFactory(), this);

        getMyEntityFactory().addEntity(mySegments.get(0));
        getMyEntityFactory().addEntity(mySegments.get(1));
        getMyEntityFactory().addEntity(mySegments.get(2));
        getMyEntityFactory().addEntity(myTail);

        myPath = new ArrayList<>();

        mySpawned = false;
    }

    /**
     *
     * @return True if the Worm has been spawned, false if it has not.
     */
    public boolean isSpawned() { return mySpawned; }

    /**
     * This method spawns the Worm.
     */
    public void spawn()
    {
        setMySize(new Vec2(96, 96));
        setMaxSpeed(4);
        myTurnSpeed = myRand.nextFloat(.05f);
        myTurnDirection = myRand.nextBoolean();
        myAngle = 0;
        myNextCourseChange = getCurrentFrame() + myRand.nextInt(61) + 15;
        setMyAnimation(getMyEntityFactory().getAssets().getAnimation("head"));

        mySegments.get(0).setMySize(new Vec2(64, 64));
        mySegments.get(0).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("body1"));
        mySegments.get(1).setMySize(new Vec2(64, 64));
        mySegments.get(1).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("body1"));
        mySegments.get(2).setMySize(new Vec2(32, 32));
        mySegments.get(2).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("body3"));

        myTail.setMySize(new Vec2(32, 32));
        myTail.setMyAnimation(getMyEntityFactory().getAssets().getAnimation("tail"));

        setDamage(1);
        setMyKnockbackPower(10);
        setMyKnockbackLength(10);
        setHitPoints(10);
        mySpawned = true;
    }

    /**
     * This method retrieves the Worm's body (segments).
     * @return The Worm's body.
     */
    public ArrayList<Body> getBody() { return mySegments; }

    /**
     * This method returns the Worm's tail.
     * @return The Worm's tail.
     */
    public Tail getTail() { return myTail; }

    /**
     * This method updates the Worm every frame.
     */
    public void update()
    {
        if (mySpawned)
        {
            if (getCurrentFrame() >= getInvincibilityEndFrame())
            {
                setInvincibility(false);
            }
            if (getCurrentFrame() >= getKnockbackEndFrame())
            {
                setKnockback(false);
            }
            if (getHitPoints() <= 0)
            {
                die();
            }
            movement();
            collision();
            incrementCurrentFrame();
        }
    }

    /**
     * This method specifies how much damage the Worm inflicts during an attack.
     * @return The Worm's damage amount.
     */
    public int attack() { return 0; }

    /**
     * The Worm's movement behavior ???
     */
    public void movement2()
    {
        if (myTParam >= 1)
        {
            generateControlPoints();
        }

        Vec2 p0 = myControlPoints.get(0);
        Vec2 p1 = myControlPoints.get(1);
        Vec2 p2 = myControlPoints.get(2);
        Vec2 p3 = myControlPoints.get(3);

        myTParam += getMaxSpeed() * .0015f;
        Vec2 newPos = Physics.calculateBezierPoint( myTParam, p0, p1, p2, p3);

        setMyPreviousPos(getMyPos());
        setMyPos(newPos);
    }

    /**
     * The Worm's movement behavior ???
     */
    public void movement()
    {
        if (getHitPoints() > 0 && !isKnockback())
        {
            if (getCurrentFrame() >= myNextCourseChange)
            {
                myTurnSpeed = myRand.nextFloat(.5f) / 10;
                myTurnDirection = myRand.nextBoolean();
                myNextCourseChange = getCurrentFrame() + myRand.nextInt(91) + 20;
            }

            if (myTurnDirection)
            {
                myAngle += myTurnSpeed;
            }
            else
            {
                myAngle -= myTurnSpeed;
            }

            double velocityX = getMaxSpeed() * Math.cos(myAngle);
            double velocityY = getMaxSpeed() * Math.sin(myAngle);

            setMyPreviousPos(getMyPos());
            setVelocity(new Vec2((float) velocityX, (float) velocityY));
            updateMyPos(getVelocity());
        }

        if (getHitPoints() > 0)
        {
            myPath.add(getMyPreviousPos());
            if (myPath.size() > 200)
            {
                myPath.remove(0);
            }
        }
    }

    /**
     * This method specifies what should happen when a Worm collides with specific Entities.
     */
    public void collision()
    {
        Vec2 overlap;
        Vec2 previousOverlap;

        ArrayList<Entity> tiles = new ArrayList<>();
        tiles.addAll(getMyEntityFactory().getEntities("Wall"));
        tiles.addAll(getMyEntityFactory().getEntities("Door"));
        tiles.addAll(getMyEntityFactory().getEntities("exit"));
        for(var t: tiles) {
            overlap = Physics.getOverlap(this, t);

            if (overlap.getMyX() > 0 && overlap.getMyY() > 0) {

                previousOverlap = Physics.getPreviousOverlap(this, t);

                // If the overlap is horizontal
                if (previousOverlap.getMyY() > 0)
                {
                    // If the player came from the left, push them out to the left
                    if (this.getMyPos().getMyX() < t.getMyPos().getMyX())
                    {
                        this.getMyPos().setMyX(this.getMyPos().getMyX() - (overlap.getMyX()));
                    }
                    // If the player came from the right push them out to the right
                    else
                    {
                        this.getMyPos().setMyX(this.getMyPos().getMyX() + (overlap.getMyX()));
                    }
                }

                // If the overlap is vertical
                if (previousOverlap.getMyX() > 0)
                {
                    // If the player came from above push them up
                    if (this.getMyPos().getMyY() < t.getMyPos().getMyY())
                    {
                        this.getMyPos().setMyY(this.getMyPos().getMyY() - (overlap.getMyY()));
                    }
                    else
                    {
                        this.getMyPos().setMyY(this.getMyPos().getMyY() + (overlap.getMyY()));
                    }
                }

                setMaxSpeed(getMaxSpeed() * -1);
            }
        }

        Hero hero = getMyEntityFactory().getHero();
        overlap = Physics.getOverlap(this, hero);
        if (overlap.getMyX() > 0 && overlap.getMyY() > 0 && !hero.isInvincibility())
        {
            hero.applyDamage(getDamage());
            if (hero.getHitPoints() <= 0)
            {
                hero.destroy();
            }
            hero.setInvincibility(true,45);
            hero.knockback(this, getMyKnockbackPower(), getMyKnockbackLength());
        }
    }

    /**
     * This method specifies what should happen when a Worm dies.
     */
    @Override
    public void die()
    {
        setMySize(new Vec2(0, 0));
        mySegments.get(0).setMySize(new Vec2(0, 0));
        mySegments.get(1).setMySize(new Vec2(0, 0));
        mySegments.get(2).setMySize(new Vec2(0, 0));
        myTail.setMySize((new Vec2(0, 0)));
        if (!myTail.getMyAnimation().getName().equals("bossDeath"))
        {
            myTail.setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"));
        }
        else if (!mySegments.get(2).getMyAnimation().getName().equals("bossDeath") && myTail.getMyAnimation().hasEnded())
        {
            myTail.destroy();
            mySegments.get(2).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"));
        }
        else if (!mySegments.get(1).getMyAnimation().getName().equals("bossDeath") && mySegments.get(2).getMyAnimation().hasEnded())
        {
            mySegments.get(2).destroy();
            mySegments.get(1).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"));
        }
        else if (!mySegments.get(0).getMyAnimation().getName().equals("bossDeath") && mySegments.get(1).getMyAnimation().hasEnded())
        {
            mySegments.get(1).destroy();
            mySegments.get(0).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"));
        }
        else if (!getMyAnimation().getName().equals("bossDeath") && mySegments.get(0).getMyAnimation().hasEnded())
        {
            mySegments.get(0).destroy();
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"));
        }
        else if (getMyAnimation().hasEnded())
        {
            destroy();
        }
    }

    /**
     * This method decreases lag to improve the Worm's animation.
     */
    public void decreaseLag()
    {
        mySegments.get(0).myLag -= 1.25;
        mySegments.get(1).myLag -= 2;
        mySegments.get(2).myLag -= 2.6;
        myTail.myLag -= 3.25;
    }

    private void generateControlPoints()
    {
        myTParam = 0;
        myControlPoints.removeAll(myControlPoints);
        myControlPoints.add(getMyPos());
        Vec2 controlPoint;
        for (int i = 0; i < 3; i++)
        {
            int tileX = myRand.nextInt(17) + 1;
            int tileY = myRand.nextInt(9) + 1;
            controlPoint = Physics.getPosition((int) getRoom().getMyX(), (int) getRoom().getMyY(), tileX, tileY);
            myControlPoints.add(controlPoint);
        }
    }


    /**
     * The Worm's body.
     */
    public class Body extends Entity
    {
        final private Worm myHead;
        private float myLag;

        /**
         *
         * @param theSize The Body's size.
         * @param thePos The Body's position.
         * @param theEntityFactory The Entity Factory that generates the Worm's Body.
         * @param theHead The Head.
         * @param theLag The lag amount.
         */
        public Body(final Vec2 theSize, final Vec2 thePos, final EntityFactory theEntityFactory, final Worm theHead, final int theLag)
        {
            super(theSize, thePos, "Body", theEntityFactory);
            myHead = theHead;
            myLag = theLag;
        }

        /**
         * This method updates the Body every frame.
         */
        public void update()
        {
            movement();
            collision();
            incrementCurrentFrame();
        }

        private void movement()
        {
            if (myHead.myPath.size() > Math.floor(myLag))
            {
                int size = myHead.myPath.size();
                setMyPreviousPos(getMyPos());
                setMyPos(myHead.myPath.get(size - (int) Math.floor(myLag)));
            }
        }

        private void collision()
        {

            Hero hero = getMyEntityFactory().getHero();
            Vec2 overlap = Physics.getOverlap(this, hero);
            if (overlap.getMyX() > 0 && overlap.getMyY() > 0 && !hero.isInvincibility())
            {
                hero.applyDamage(myHead.getDamage());
                if (hero.getHitPoints() <= 0)
                {
                    hero.destroy();
                }
                hero.setInvincibility(true,45);
                hero.knockback(this, 3, 8);
            }
        }

        /**
         * This method retrieves the Head of the Body.
         * @return The Head.
         */
        public Worm getHead() { return myHead; }

    }

    /**
     * The Worm's tail.
     */
    public class Tail extends Entity
    {
        final private Worm myHead;
        private float myLag;

        /**
         *
         * @param theSize The Tail's size.
         * @param thePos The Tail's position.
         * @param theEntityFactory The Entity Factory that generates the Tail.
         * @param theHead The Head.
         */
        public Tail(final Vec2 theSize, final Vec2 thePos, final EntityFactory theEntityFactory, final Worm theHead)
        {
            super(theSize, thePos, "Tail", theEntityFactory);
            myHead = theHead;
            myLag = 48;
        }

        /**
         * This method updates the Tail every frame.
         */
        public void update()
        {
            movement();
            incrementCurrentFrame();
        }

        private void movement()
        {
            if (myHead.myPath.size() > Math.floor(myLag))
            {
                int size = myHead.myPath.size();
                setMyPreviousPos(getMyPos());
                setMyPos(myHead.myPath.get(size - (int) Math.floor(myLag)));
            }
        }

        /**
         * This method retrieves the Head of the Body.
         * @return The Head.
         */
        public Worm getHead() { return myHead; }
    }

    /**
     * This method retrieves the Body in segments, represented as items in an ArrayList.
     * @return The Worm's Body.
     */
    public ArrayList<Body> getSegments()
    {
        return mySegments;
    }
}
