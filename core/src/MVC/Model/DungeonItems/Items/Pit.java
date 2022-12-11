package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Pit extends Item
{
    private int myDamageFall;

    public Pit(final EntityFactory theEntityFactory)
    {
        super("pit", theEntityFactory);
        setDamageFall(2);
    }

    public Pit(final Vec2 theLocation, final EntityFactory theEntityFactory) //this constructor is never used?
    {
        super("pit", theEntityFactory);
        setDamageFall(2);
        setMyPos(theLocation);
    }

    @Override
    public void activate(final Hero theHero)
    {
        if(theHero.getHitPoints() > myDamageFall)
        {
            theHero.setHitPoints(theHero.getHitPoints() - myDamageFall);
        }
        destroy();
    }

    public int getDamageFall()
    {
        return myDamageFall;
    }

    public void setDamageFall(final int theDamageFall) {
        if (theDamageFall > 0) //should there be a max on amt of damage
        {
            myDamageFall = theDamageFall;
        }
    }

}