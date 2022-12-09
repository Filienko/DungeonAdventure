package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Door extends Entity
{
    private int myRoomNumber;

    private Vec2 myLocation;

    private int myMonsterCounter;

    public Door(final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64), new Vec2(),"Door", theEntityFactory);
        setMyLocation(new Vec2());
        setMonsterCounter(4);
    }

    public Door(final int theMonsterCounter, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        //TODO:assign correct bounding boxes to all of the Entities inheriting from Entity
        super(new Vec2(64, 64), theLocation,"Door", theEntityFactory);
        setMyLocation(theLocation);
        setMonsterCounter(theMonsterCounter);

    }

    public Door(final int theRoomNumber,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64),theLocation,"Door", theEntityFactory);
        setRoomNumber(theRoomNumber);
        setMyLocation(theLocation);
        setMonsterCounter(theMonsterCounter);
    }

    @Override
    public void update()
    {
        if(myMonsterCounter == 0)
        {
            destroy();
        }
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 theLocation)
    {
        if (theLocation != null)
        {
            myLocation = theLocation;
        }
    }

    public int getMonsterCounter()
    {
        return myMonsterCounter;
    }

    public void setMonsterCounter(final int theMonsterCounter)
    {
        if (theMonsterCounter >= 0)
        {
            myMonsterCounter = theMonsterCounter;
        }
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
        if (theRoomNumber > 0)
        {
            myRoomNumber = theRoomNumber;
        }
    }
}
