package MVC.Model.DungeonItems.Items;

import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Pit extends Item
{
    private Vec2 myLocation;
    private int damageFall;

    public Pit()
    {
        super("Pit", new Vec2((new Random()).nextInt(0, 21),
                (new Random()).nextInt(0, 13)));
        this.damageFall = 15;
    }

    public Pit(final Vec2 myLocation)
    {
        super("Pit", new Vec2());
        this.myLocation = myLocation;
        this.damageFall = 15;
    }

    public Pit(final Vec2 myLocation, final int damageFall)
    {
        super("Pit", new Vec2());
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