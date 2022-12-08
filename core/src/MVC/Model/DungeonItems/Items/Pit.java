package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Pit extends Item
{
    private Vec2 myLocation;
    private int myDamageFall;

    public Pit(final EntityFactory theEntityFactory)
    {
        super("Pit", theEntityFactory);
        myDamageFall = 15;
    }

    public Pit(final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super("Pit", theEntityFactory);
        setMyLocation(theLocation);
        myDamageFall = 15;
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(theHero.getHitPoints() - myDamageFall);
        destroy();
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 theLocation)
    {
        if (theLocation != null)
        {
            myLocation = theLocation;
        }
    }

    public int getDamageFall()
    {
        return myDamageFall;
    }

    public void setDamageFall(final int theDamageFall) { myDamageFall = theDamageFall; }
}