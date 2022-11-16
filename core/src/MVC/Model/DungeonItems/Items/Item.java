package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public abstract class Item extends Entity
{
    private Vec2 myLocation;

    private static String myType;

    protected Item(final String theType, Vec2 theLocation)
    {
        super(theLocation,new Vec2());
        myType = theType;
        myLocation = theLocation;
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