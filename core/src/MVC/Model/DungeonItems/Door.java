package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonUtils.Graph;
import MVC.Model.Physics.Vec2;

public class Door extends Entity implements Comparable<Door>
{
    private int src;
    private int dest;
    private int weight;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame;

    private Vec2 myLocation;
    private boolean myDoorOpen;

    public Door(final EntityFactory theEntityFactory)
    {
        super(new Vec2(),new Vec2(), theEntityFactory);
        myLocation = new Vec2();
        myDoorOpen = false;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public Door(final boolean theDoorOpen, final EntityFactory theEntityFactory)
    {
        super(new Vec2(),new Vec2(), theEntityFactory);
        myLocation = new Vec2();
        myDoorOpen = theDoorOpen;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    @Override
    public void update()
    {
        super.movement();
        myCurrentFrame++;
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 theLocation)
    {
        myLocation = theLocation;
    }

    public boolean isMyDoorOpen()
    {
        return myDoorOpen;
    }

    public void setMyDoorOpen(final boolean theDoorOpen)
    {
        myDoorOpen = theDoorOpen;
    }

    public int compareTo(final Door compareEdge)
    {
        return this.weight - compareEdge.weight;
    }
}
