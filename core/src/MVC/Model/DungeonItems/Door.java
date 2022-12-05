package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Door extends Entity
{
    private int myRoomNumber;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame;

    private Vec2 myLocation;

    private int myMonsterCounter;

    public Door(final EntityFactory theEntityFactory)
    {
        super(new Vec2(),"Door", new Vec2(), theEntityFactory);
        myLocation = new Vec2();
        myMonsterCounter = 4;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public Door(final int theMonsterCounter, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        //TODO:assign correct bounding boxes to all of the Entities inheriting from Entity
        super(theLocation,"Door",theLocation.add(new Vec2(-1,-1)), theEntityFactory);
        myLocation = theLocation;
        myMonsterCounter = theMonsterCounter;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public Door(final int theRoomNumber,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(theLocation,"Door",theLocation.add(new Vec2(-1,-1)), theEntityFactory);
        myRoomNumber = theRoomNumber;
        myLocation = theLocation;
        myMonsterCounter = theMonsterCounter;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    @Override
    public void update()
    {
        if(myMonsterCounter == 0)
        {
            destroy();
        }
        myCurrentFrame++;

        //incrementCurrentFrame();
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 theLocation)
    {
        myLocation = theLocation;
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
