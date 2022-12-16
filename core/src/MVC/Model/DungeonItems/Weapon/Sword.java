package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.*;
import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity implements ICollidable
{
    /**
     * The Sword.
     */
    private static Sword mySword;

    /**
     * The Entity Factory that generated the Sword.
     */
    private EntityFactory myEntityFactory;

    /**
     * The Sword's lifespan.
     */
    private final long myLifeSpan;

    /**
     * The Hero that is using the Sword.
     */
    private final Hero myHero;

    /**
     * Sword constructor, which calls Entity's constructor to initialize its size, position, type, lifespan,
     * current frame counter, the Hero using it, and the Entity Factory that generated it.
     * @param theEntityFactory The Entity Factory that generated the Sword.
     * @param theHero The Hero who is using the Sword.
     */
    private Sword(final EntityFactory theEntityFactory, final Hero theHero)
    {
        super(new Vec2(48, 48), theHero.getMyPos(), "Sword", theEntityFactory);
        setMyEntityFactory(theEntityFactory);
        myLifeSpan = 15;
        setCurrentFrame(0);
        myHero = theHero;
    }

    /**
     * This method retrieves an instance of the Sword using the Singleton design pattern.
     * @param theEntityFactory The Entity Factory that generated the Sword.
     * @param theHero The Hero that is using the Sword.
     * @return
     */
    public static Sword getInstance(final EntityFactory theEntityFactory, final Hero theHero)
    {
        if (mySword == null || !mySword.getActiveStatus())
        {
            mySword = new Sword(theHero.getMyEntityFactory(), theHero);
        }
        return mySword;
    }

    /**
     * This method retrieves the Sword's lifespan.
     * @return
     */
    public long getMyLifeSpan() { return myLifeSpan; }

    /**
     * Information about the Sword that is to be updated.
     */
    @Override
    public void update()
    {
        myHero.setAttackStatus(true);
        if (getCurrentFrame() >= myLifeSpan)
        {
            destroy();
            getMyEntityFactory().renewSword();
        }
        else if (getCurrentFrame() < myLifeSpan)
        {
            movement();
            collide();
            incrementCurrentFrame();
        }
    }

    /**
     * This method destroys the Sword and marks its Hero to be destroyed as well.
     */
    @Override
    public void destroy()
    {
        super.destroy();
        myHero.setAttackStatus(false);
    }

    /**
     * Analyzes whether the collision occurred between two objects and performs certain associated logic
     */
    @Override
    public void collide()
    {
        for (var e: getMyEntityFactory().getEntities("monster"))
        {
            Monster m = (Monster) e;
            if (m.getActiveStatus() && !m.isInvincibility())
            {
                Vec2 overlap = Physics.getOverlap(this, m);
                if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
                {
                    m.applyDamage(myHero.getDamage());

                    if(m.getHitPoints()<=0)
                    {
                        m.die();
                    }
                    else
                    {
                        long lifespanLeft = (myLifeSpan - (getCurrentFrame() + 1));
                        m.setInvincibility(true, lifespanLeft);
                        m.knockBack(this, myHero.getMyKnockBackPower(), myHero.getMyKnockBackLength());
                    }
                }
            }
        }

        for (var e: getMyEntityFactory().getEntities("worm"))
        {
            Worm w = (Worm) e;
            Entity t = (w).getTail();

            if (w.getActiveStatus() && !w.isInvincibility())
            {
                Vec2 overlap = Physics.getOverlap(this, t);
                if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
                {
                    w.applyDamage(1);
                    if(w.getHitPoints() > 0)
                    {
                        w.setInvincibility(true, 60);
                        w.knockBack(this, 0, 60);
                        w.setMaxSpeed(w.getMaxSpeed() + Math.signum(w.getMaxSpeed()) * .5f);
                        w.decreaseLag();
                    }
                }
            }
        }
    }

    /**
     * This method retrieves the Hero that is using the Sword.
     * @return The Hero using the Sword.
     */
    public Hero getMyHero()
    {
        return myHero;
    }

    /**
     * This method specifies the Sword's movement behavior.
     */
    private void movement()
    {
        Vec2 position = new Vec2();
        position.setMyX(myHero.getMyPos().getMyX()+48*myHero.getFacing().getMyX());
        position.setMyY(myHero.getMyPos().getMyY()+48*myHero.getFacing().getMyY());
        setMyPreviousPos(getMyPos());
        setMyPos(position);
    }
}
