package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public class Door extends Entity
{
    private int myRoomNumber;

    private Vec2 myLocation;

    private int myMonsterCounter;

    public Door()
    {
        super(new Vec2(),"Door",new Vec2());
        myLocation = new Vec2();
        myMonsterCounter = 4;
    }

    public Door(final int theMonsterCounter, final Vec2 theLocation)
    {
        //TODO:assign correct bounding boxes to all of the Entities inheriting from Entity
        super(theLocation,"Door",theLocation.add(new Vec2(-1,-1)));
        myLocation = theLocation;
        myMonsterCounter = theMonsterCounter;
    }

    public Door(final int theRoomNumber,final int theMonsterCounter,final Vec2 theLocation)
    {
        super(theLocation,"Door",theLocation.add(new Vec2(-1,-1)));
        myRoomNumber = theRoomNumber;
        myLocation = theLocation;
        myMonsterCounter = theMonsterCounter;
    }

    @Override
    public void update()
    {
        if(myMonsterCounter == 0)
        {
            destroy();
        }
        incrementCurrentFrame();
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
    }

    public void decrementMonsterCounter()
    {
        myMonsterCounter--;
    }

    private int getRoomNumber()
    {
        return myRoomNumber;
    }

    private void setRoomNumber(final int theRoomNumber)
    {
        myRoomNumber = theRoomNumber;
    }

}
