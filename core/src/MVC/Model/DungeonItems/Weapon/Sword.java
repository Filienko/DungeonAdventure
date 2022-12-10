package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import MVC.View.Animation;
import com.badlogic.gdx.graphics.Texture;

public class Sword extends Entity implements ICollidable
{
    private static Sword mySword;

    private final int myLifeSpan;

    private Hero myHero;

    private Sword(final EntityFactory theEntityFactory, final Hero theHero)
    {
        super(new Vec2(48, 48), theHero.getMyPos(), "Sword", theEntityFactory);
        myLifeSpan = 10;
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

    public int getMyLifeSpan() { return myLifeSpan; }

    @Override
    public void update()
    {
        myHero.setAttackStatus(true);
        if (getCurrentFrame() >= myLifeSpan)
        {
            destroy();
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
        for (var e: getMyEntityFactory().getMonsters())
        {

            if (e.getActiveStatus() && !e.isInvincibility())
            {
                Vec2 overlap = Physics.getOverlap(this, e);
                if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
                {
                    e.applyDamage(myHero.getDamage());

                    if(e.getHitPoints()<=0)
                    {
                        //Potentially add sound
                        //TODO:Add appropriate animation logic
                        //e.setMyAnimation(new Animation("EnemyDeath",new Texture(""),2,1));
                        e.die();
                    }
                    else
                    {
                        //Potentially add sound
                        long lifespanLeft = (myLifeSpan - (getCurrentFrame() + 1));
                        var v = e.getMyPos().minus(this.getMyPos());
                        var normalizedV = v.multiply(v.quickInverseMagnitude());
                        e.setVelocity(normalizedV.multiply((float) 4),10);
                        e.setInvincibility(true, lifespanLeft);
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
}
