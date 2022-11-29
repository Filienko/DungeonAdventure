package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public abstract class Item extends Entity
{
    private Vec2 myLocation;

    private static String myType;

    protected Item(final String theType)
    {
        super(new Vec2((new Random()).nextInt(1, 20),
                (new Random()).nextInt(1, 12)),theType,new Vec2());
        myType = theType;
        myLocation = new Vec2((new Random()).nextInt(1, 20),
                (new Random()).nextInt(1, 12));
    }

    protected Vec2 getMyLocation()
    {
        return myLocation;
    }

    protected void setMyLocation(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
    }

    public String getType()
    {
        return myType;
    }
}