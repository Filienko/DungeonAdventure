package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.*;
import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity implements ICollidable
{
    private static Sword mySword;

    private EntityFactory myEntityFactory;
    private final long myLifeSpan;

    private final Hero myHero;

    private Sword(final EntityFactory theEntityFactory, final Hero theHero)
    {
        super(new Vec2(48, 48), theHero.getMyPos(), "Sword", theEntityFactory);
        setMyEntityFactory(theEntityFactory);
        myLifeSpan = 15;
        setCurrentFrame(0);
        myHero = theHero;
    }

    public static Sword getInstance(final EntityFactory theEntityFactory, final Hero theHero)
    {
        if (mySword == null || !mySword.getActiveStatus())
        {
            mySword = new Sword(theHero.getMyEntityFactory(), theHero);
        }
        return mySword;
    }

    public long getMyLifeSpan() { return myLifeSpan; }

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

    private void movement()
    {
        Vec2 position = new Vec2();
        position.setMyX(myHero.getMyPos().getMyX()+48*myHero.getFacing().getMyX());
        position.setMyY(myHero.getMyPos().getMyY()+48*myHero.getFacing().getMyY());
        setMyPreviousPos(getMyPos());
        setMyPos(position);
    }

    public Hero getMyHero()
    {
        return myHero;
    }
}
