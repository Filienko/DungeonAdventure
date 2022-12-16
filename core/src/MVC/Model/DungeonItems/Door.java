package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Physics;
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
        super(new Vec2(64, 64), theLocation,"Door", theEntityFactory);
        setMonsterCounter(theMonsterCounter);

    }

    public Door(final Vec2 theRoom,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64),theLocation,"Door", theEntityFactory);
        setMonsterCounter(theMonsterCounter);
        setRoom(theRoom); //test this first
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
        else
        {
            decrementMonsterCounter();
        }
    }

    public void decrementMonsterCounter()
    {
        if (myMonsterCounter > 0)
        {
            myMonsterCounter--;
        }
    }

    public void update()
    {
        if (myMonsterCounter <= 0)
        {
            Vec2 overlap;
            for (var d : getMyEntityFactory().getEntities("door"))
            {
                if (d != this && !d.getMySize().equals(new Vec2(0, 0)))
                {
                    overlap = Physics.getOverlap(this, d);
                    if (overlap.getMyX() > -1 && overlap.getMyY() > -1)
                    {
                        d.destroy();
                    }
                }
            }
            this.destroy();
        }
    }
}