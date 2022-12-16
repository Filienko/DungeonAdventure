package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.Random;

public class Worm extends DungeonCharacter
{
    /**
     * ArrayList of Body instances that follow this Worm
     */
    final private ArrayList<Body> mySegments;
    /**
     * Tail that follows this Worm
     */
    final private Tail myTail;
    final private ArrayList<Vec2> myControlPoints;
    /**
     * ArrayList of previous positions
     */
    final private ArrayList<Vec2> myPath;
    private float myTParam;
    /**
     * How fast this Worm turns
     */
    private double myTurnSpeed;
    /**
     * What direction this Worm turns
     */
    private boolean myTurnDirection;
    /**
     * The current angle of the movement vector
     */
    private double myAngle;
    /**
     * The frame on which a new turn speed and turn direction will be randomly generated
     */
    private long myNextCourseChange;
    /**
     * Random number generator
     */
    final private Random myRand;

    /**
     * Constructor that takes two arguments and initializes the Bodies and Tail to follow the Worm
     * @param thePos Initial position
     * @param theEntityFactory The EntityFactory that generated this Worm
     */
    public Worm(final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        // Constructor call to DungeonCharacter
        super("Worm", false, 10,
        1, 4, new Vec2(96, 96), thePos,
        new Vec2(0, 0), theEntityFactory);

        myControlPoints = new ArrayList<>();
        myRand = new Random();
        setRoom(Physics.getRoom(thePos.getMyX(), thePos.getMyY()));
        myTParam = 0;
        mySegments = new ArrayList<>();
        mySegments.add(new Body(new Vec2( 64, 64), getMyPos(), getMyEntityFactory(), this, 16));
        mySegments.add(new Body(new Vec2( 64, 64), getMyPos(), getMyEntityFactory(),  this, 28));
        mySegments.add(new Body(new Vec2( 48, 48), getMyPos(), getMyEntityFactory(), this, 39));
        myTail = new Tail(new Vec2(48, 48), getMyPos(), getMyEntityFactory(), this);

        if (getMyEntityFactory().getAssets() != null)
        {
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation("head"));
            mySegments.get(0).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("body1"));
            mySegments.get(1).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("body1"));
            mySegments.get(2).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("body3"));
            myTail.setMyAnimation(getMyEntityFactory().getAssets().getAnimation("tail"));
        }

        getMyEntityFactory().addEntity(mySegments.get(0));
        getMyEntityFactory().addEntity(mySegments.get(1));
        getMyEntityFactory().addEntity(mySegments.get(2));
        getMyEntityFactory().addEntity(myTail);

        setMyKnockBackPower(10);
        setMyKnockBackLength(10);

        myTurnSpeed = myRand.nextFloat(.05f);
        myTurnDirection = myRand.nextBoolean();
        myAngle = 0;
        myNextCourseChange = getCurrentFrame() + myRand.nextInt(61) + 15;

        generateControlPoints();

        myPath = new ArrayList<>();
    }

    /**
     * @return The Body instances that follow this Worm
     */
    public ArrayList<Body> getSegments()
    {
        return mySegments;
    }

    /**
     * @return The Tail instance that follows this Worm
     */
    public Tail getTail() { return myTail; }

    /**
     * Method called every frame that governs the behavior of this Worm
     */
    public void update()
    {
        if (getCurrentFrame() >= getInvincibilityEndFrame())
        {
            setInvincibility(false);
        }
        if (getCurrentFrame() >= getKnockBackEndFrame())
        {
            setKnockBack(false);
        }
        if (getHitPoints() <= 0)
        {
            die();
        }
        movement();
        collide();
        incrementCurrentFrame();
    }

    @Override
    public int attack() { return 0; }

    public void movement2()
    {
        if (getHitPoints() > 0 && !isKnockBack())
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
            Vec2 newPos = Physics.calculateBezierPoint(myTParam, p0, p1, p2, p3);

            setMyPreviousPos(getMyPos());
            setMyPos(newPos);
            Vec2 newVelocity = getMyPos().minus(getMyPreviousPos());
            setVelocity(newVelocity);
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

    @Override
    public void movement()
    {
        if (getHitPoints() > 0 && !isKnockBack())
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
            if (myPath.size() > 50)
            {
                myPath.remove(0);
            }
        }
    }

    @Override
    public void collide()
    {
        Vec2 overlap;

        // Collision with tiles
        ArrayList<Entity> tiles = new ArrayList<>();
        tiles.addAll(getMyEntityFactory().getEntities("Wall"));
        tiles.addAll(getMyEntityFactory().getEntities("Door"));
        tiles.addAll(getMyEntityFactory().getEntities("exit"));
        for(var t: tiles) {
            overlap = Physics.getOverlap(this, t);

            if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
            {

                Physics.tileResolution(overlap, this, t);
                setMaxSpeed(getMaxSpeed() * -1);
            }
        }

        // Collision with the hero
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
            hero.knockBack(this, getMyKnockBackPower(), getMyKnockBackLength());
        }
    }

    @Override
    public void die()
    {
        setMySize(new Vec2(0, 0));
        mySegments.get(0).setMySize(new Vec2(0, 0));
        mySegments.get(1).setMySize(new Vec2(0, 0));
        mySegments.get(2).setMySize(new Vec2(0, 0));
        myTail.setMySize((new Vec2(0, 0)));

        // Dominoes the animations so the segments explode one at a time
        if (!myTail.getMyAnimation().getName().equals("bossDeath"))
        {
            myTail.setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"), false);
        }
        else if (!mySegments.get(2).getMyAnimation().getName().equals("bossDeath") && myTail.getMyAnimation().hasEnded())
        {
            myTail.destroy();
            mySegments.get(2).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"), false);
        }
        else if (!mySegments.get(1).getMyAnimation().getName().equals("bossDeath") && mySegments.get(2).getMyAnimation().hasEnded())
        {
            mySegments.get(2).destroy();
            mySegments.get(1).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"), false);
        }
        else if (!mySegments.get(0).getMyAnimation().getName().equals("bossDeath") && mySegments.get(1).getMyAnimation().hasEnded())
        {
            mySegments.get(1).destroy();
            mySegments.get(0).setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"), false);
        }
        else if (!getMyAnimation().getName().equals("bossDeath") && mySegments.get(0).getMyAnimation().hasEnded())
        {
            mySegments.get(0).destroy();
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation("bossDeath"), false);
        }
        else if (getMyAnimation().hasEnded())
        {
            destroy();
            Exit.setExitCondition(true);
        }
    }

    /**
     * Decreases the element that the following Body and Tail instances access in myPath
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


    public class Body extends Entity
    {
        /**
         * The Worm that this Body follows
         */
        final private Worm myHead;
        /**
         * How far behind this Body trails the Worm
         */
        private float myLag;

        /**
         * Constructor that takes five arguments
         * @param theSize The size of the bounding box
         * @param thePos The initial position
         * @param theEntityFactory The EntityFactory this belongs to
         * @param theHead The Worm that this follows
         * @param theLag How far behind the Worm this trails
         */
        public Body(final Vec2 theSize, final Vec2 thePos, final EntityFactory theEntityFactory, final Worm theHead, final int theLag)
        {
            // Constructor call to Entity
            super(theSize, thePos, "Body", theEntityFactory);
            myHead = theHead;
            myLag = theLag;
        }

        @Override
        public void update()
        {
            movement();
            collision();
            incrementCurrentFrame();
        }

        /**
         * Sets a new position to the Worm's previous position with lag
         */
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
         * Resolves collisions between this and the Hero
         */
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
                hero.knockBack(this, 3, 8);
            }
        }

        /**
         * @return The Worm that this follows
         */
        public Worm getHead() { return myHead; }

    }

    public class Tail extends Entity
    {
        /**
         * The Worm that this follows
         */
        final private Worm myHead;
        /**
         * How far behind this Tail trails the Worm
         */
        private float myLag;

        /**
         * Constructor that takes four arguments
         * @param theSize The size of the bounding box
         * @param thePos The initial position
         * @param theEntityFactory The EntityFactory this belongs to
         * @param theHead The Worm that this follows
         */
        public Tail(final Vec2 theSize, final Vec2 thePos, final EntityFactory theEntityFactory, final Worm theHead)
        {
            // Constructor call to Entity
            super(theSize, thePos, "Tail", theEntityFactory);
            myHead = theHead;
            myLag = 48;
        }

        @Override
        public void update()
        {
            movement();
            incrementCurrentFrame();
        }

        /**
         * Sets a new position to the Worm's previous position with lag
         */
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
         * @return The Worm that this follows
         */
        public Worm getHead() { return myHead; }
    }
}
