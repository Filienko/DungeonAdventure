package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Door extends Entity
{
    private Vec2 myLocation;

    private int myMonsterCounter;

    public Door(final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64), new Vec2(),"Door", theEntityFactory);
        myLocation = new Vec2();
        myMonsterCounter = 4;
    }

    public Door(final int theMonsterCounter, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        //TODO:assign correct bounding boxes to all of the Entities inheriting from Entity
        super(new Vec2(64, 64), theLocation,"Door", theEntityFactory);
        myLocation = theLocation;
        myMonsterCounter = theMonsterCounter;

    }

    public Door(final Vec2 theRoom,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64),theLocation,"Door", theEntityFactory);
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
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 theLocation)
    {
        myLocation = theLocation;
    }

    public int getMonsterCounter()
    {
        return myMonsterCounter;
    }

    public void decrementMonsterCounter()
    {
        myMonsterCounter--;
    }
}
