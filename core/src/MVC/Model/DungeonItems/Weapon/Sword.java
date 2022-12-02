package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity
{
    private Vec2 myBoundingBox;
    private int myLifeSpan; //must initialize this
    private long myCurrentFrame;
    private Hero myHero;

    @Override
    public void update() {
        if (myCurrentFrame >= myLifeSpan)
        {
            //request that the entity factory delete it
            //entity factory does not have a method that does that

            //add a method to Entity
        }
//        super.movement();
//        super.attack();
        this.myCurrentFrame++;
    }

    public Sword(final Hero theHero)
    {
        super(new Vec2(), "Sword", new Vec2());
        myHero = theHero;
    }

    public Sword(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(thePos, "Sword", theBoundingBox);
    }

    public Vec2 getBoundingBox()
    {
        return myBoundingBox;
    }

    public void setBoundingBox(final Vec2 theBoundingBox)
    {
        myBoundingBox = theBoundingBox;
    }

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
}
