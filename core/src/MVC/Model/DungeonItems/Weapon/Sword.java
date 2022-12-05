package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity
{
    private static Sword mySword;

    private final EntityFactory myEntityFactory;

    private final int myLifeSpan;

    private Vec2 myBoundingBox;

    private long myCurrentFrame;

    private Hero myHero;

    private int damage; //how much damage it does?

    private Sword(final Vec2 theBoundingBox, final EntityFactory theEntityFactory)
    {
        super(new Vec2(), "Sword", new Vec2(), theEntityFactory);
        this.myEntityFactory = theEntityFactory;
        this.myLifeSpan = 15;
        this.myCurrentFrame = 0;
        this.myBoundingBox = theBoundingBox;
    }

    public static Sword getInstance(final Vec2 theBoundingBox, final EntityFactory theEntityFactory)
    {
        if (mySword == null)
        {
            mySword = new Sword(theBoundingBox, theEntityFactory);
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
        if(myHero instanceof Thief)
        {
            attackThief(theOpponent);
        }
        else if(myHero instanceof Warrior)
        {
            attackWarrior(theOpponent);
        }
        else if(myHero instanceof Priestess)
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
        if (myCurrentFrame >= myLifeSpan)
        {
            destroy();
        } else if (myCurrentFrame < myLifeSpan)
        {
            super.movement();
            myCurrentFrame++;
        }

    }
}
