package MVC.Model.DungeonItems.Items;

import MVC.Model.Physics.Vec2;

public class Pit extends Item
{
    private Vec2 myLocation;
    private int damageFall;

    public Pit()
    {
        super("Pit");
        this.myLocation = new Vec2();
        this.damageFall = 15;
    }

    public Pit(final Vec2 myLocation)
    {
        super("Pit");
        this.myLocation = myLocation;
        this.damageFall = 15;
    }

    public Pit(final Vec2 myLocation, final int damageFall)
    {
        super("Pit");
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