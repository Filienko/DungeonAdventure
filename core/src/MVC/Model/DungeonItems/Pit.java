package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public class Pit extends Entity
{
    private Vec2 myLocation;
    private int damageFall;

    public Pit()
    {
        this.myLocation = new Vec2();
        this.damageFall = 15;
    }

    public Pit(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
        this.damageFall = 15;
    }

    public Pit(final Vec2 myLocation, final int damageFall)
    {
        this.myLocation = myLocation;
        this.damageFall = damageFall;
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