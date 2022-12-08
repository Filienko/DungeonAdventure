package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.Interfaces.ICollidable;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import MVC.View.Animation;
import com.badlogic.gdx.graphics.Texture;

public class Sword extends Entity implements ICollidable
{
    private static Sword mySword;

    private EntityFactory myEntityFactory; //should this be made final again? changed for constructor

    private final int myLifeSpan;

    private Vec2 myBoundingBox;

    private Hero myHero;

    private Sword(final EntityFactory theEntityFactory, final Hero theHero)
    {
        super(new Vec2(48, 48), new Vec2(), "Sword", theEntityFactory);
        setMyEntityFactory(theEntityFactory);
        myLifeSpan = 15;
        setCurrentFrame(0);
        myHero = theHero;
    }

    public static Sword getInstance(final Vec2 theBoundingBox, final EntityFactory theEntityFactory, final Hero theHero)
    {
        if (mySword == null)
        {
            mySword = new Sword(theEntityFactory, theHero);
        }
        return mySword;
    }

    public Vec2 getBoundingBox()
    {
        return myBoundingBox;
    }

    public void setBoundingBox(final Vec2 theBoundingBox)
    {
        myBoundingBox = theBoundingBox;
    }

    public int getMyLifeSpan() { return myLifeSpan; }

    @Override
    public void update()
    {
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

    /**
     * Analyzes whether the collision occurred between two objects and performs certain associated logic
     */
    @Override
    public void collide()
    {
        for (var e: myEntityFactory.getMonsters())
        {
            if (e.getActiveStatus()==true && !e.isInvincibility())
            {
                Vec2 overlap = Physics.getOverlap(this, e);
                if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
                {
                    e.applyDamage(myHero.attack());
                    if(e.getHitPoints()<=0)
                    {
                        //Potentially add sound
                        //TODO:Add appropriate animation logic
                        e.setMyAnimation(new Animation("EnemyDeath",new Texture(""),2,1));
                        e.destroy();
                    }
                    else
                    {
                        //Potentially add sound
                        long lifespanLeft = (myLifeSpan - (getCurrentFrame() + 1));
                        var v = e.getMyPos().minus(this.getMyPos());
                        var normalizedV = v.multiply(v.quickInverseMagnitude());
                        e.setVelocity(normalizedV.multiply((float) -1.5),10);
                        e.setInvincibility(true, lifespanLeft);
                    }
                }
            }
            //so i think it can take place in the same spot as invincibility logic,
            // we just need to set the vector of the enemy to a vector away from the sword,
            // so like take the vector between the enemy and the sword and set it to the reverse of that,
            // then while the enemy is knockback we dont update its vector
        }
    }

    private void movement()
    {
        Vec2 position = new Vec2();
        position.setMyX(myHero.getMyPos().getMyX()+56*myHero.getFacing().getMyX());
        position.setMyY(myHero.getMyPos().getMyX()+56*myHero.getFacing().getMyY());
        setMyPreviousPos(getMyPos());
        setMyPos(position);
    }
}
