package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Pit extends Item
{
    private Vec2 myLocation;
    private int damageFall;

    public Pit()
    {
        super("Pit");
        damageFall = 15;
    }

    public Pit(final Vec2 theLocation)
    {
        super("Pit");
        myLocation = theLocation;
        damageFall = 15;
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(theHero.getHitPoints() - damageFall);
        destroy();
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
    }

    public int getDamageFall()
    {
        return damageFall;
    }

    public void setDamageFall(final int damageFall)
    {
        this.damageFall = damageFall;
    }
}