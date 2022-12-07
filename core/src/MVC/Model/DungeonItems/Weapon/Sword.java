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

    private final EntityFactory myEntityFactory;

    private final int myLifeSpan;

    private Vec2 myBoundingBox;

    private Hero myHero;

    private int damage; //how much damage it does?

    private Sword(final EntityFactory theEntityFactory, final Hero theHero)
    {
        super(new Vec2(48, 48), new Vec2(), "Sword", theEntityFactory);
        myEntityFactory = theEntityFactory;
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

    public void attack(final DungeonCharacter theOpponent)
    {
        if(theOpponent instanceof Thief)
        {
            attackThief(theOpponent);
        }
        else if(theOpponent instanceof Warrior)
        {
            attackWarrior(theOpponent);
        }
        else if(theOpponent instanceof Priestess)
        {
            attackPriestess(theOpponent);
        }
    }

    private void attackPriestess(final DungeonCharacter theOpponent)
    {
    }

    private void attackWarrior(final DungeonCharacter theOpponent)
    {
    }

    private void attackThief(final DungeonCharacter theOpponent)
    {
    }

    @Override
    public void update() {
        if (getCurrentFrame() >= myLifeSpan)
        {
            destroy();
        }
        else if (getCurrentFrame() < myLifeSpan)
        {
            movement();
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
            if (e.getActiveStatus()==true)
            {
                Vec2 overlap = Physics.getOverlap(this, e);
                if (overlap.getMyX() > 0 && overlap.getMyY() > 0)
                {
                    attack(e);
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
                        var lifespanLeft = (myLifeSpan - (getCurrentFrame() + 1));
                        //e.setInvincibility(true, lifespanLeft);
                    }
                }
            }
        }
    }

    private void movement() {}
}
