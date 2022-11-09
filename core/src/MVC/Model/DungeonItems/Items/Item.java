package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public abstract class Item extends Entity
{
    private Vec2 myLocation;

    protected Vec2 getMyLocation()
    {
        return myLocation;
    }

    protected void setMyLocation(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
    }
}