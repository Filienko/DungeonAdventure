package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public class Door extends Entity
{
    private Vec2 myLocation;
    private boolean myDoorOpen;

    public Door()
    {
        super(new Vec2(),new Vec2());
        this.myLocation = new Vec2();
        this.myDoorOpen = false;
    }

    public Door(final boolean myDoorOpen)
    {
        super(new Vec2(),new Vec2());
        this.myLocation = new Vec2();
        this.myDoorOpen = myDoorOpen;
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
    }

    public boolean isMyDoorOpen()
    {
        return myDoorOpen;
    }

    public void setMyDoorOpen(final boolean myDoorOpen)
    {
        this.myDoorOpen = myDoorOpen;
    }
}
