package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Door extends Entity
{
    private int myMonsterCounter;

    public Door(final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64), new Vec2(),"Door", theEntityFactory);
        setMonsterCounter(4);
    }

    public Door(final int theMonsterCounter, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        //TODO:assign correct bounding boxes to all of the Entities inheriting from Entity
        super(new Vec2(64, 64), theLocation,"Door", theEntityFactory);
        setMonsterCounter(theMonsterCounter);

    }

    public Door(final Vec2 theRoom,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64),theLocation,"Door", theEntityFactory);
        setMonsterCounter(theMonsterCounter);
    }

    @Override
    public void update()
    {
        if(myMonsterCounter == 0)
        {
            getMyEntityFactory().getHero().setHitPoints(1000);
            getMyEntityFactory().getHero().setMaxSpeed(20);
            destroy();
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
}