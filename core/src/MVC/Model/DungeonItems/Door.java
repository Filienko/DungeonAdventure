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
        myMonsterCounter = 4;
    }

    public Door(final int theMonsterCounter, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64), theLocation,"Door", theEntityFactory);
        myMonsterCounter = theMonsterCounter;
    }

    public Door(final Vec2 theRoom,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64),theLocation,"Door", theEntityFactory);
        myMonsterCounter = theMonsterCounter;
    }

    public int getMonsterCounter()
    {
        return myMonsterCounter;
    }

    public void decrementMonsterCounter()
    {
        myMonsterCounter--;
        System.out.println("ENEMY COUNTER " + myMonsterCounter);
        if(myMonsterCounter == 0)
    {
        System.out.println(" WAS 0");
        for (var pillar:getMyEntityFactory().getPillars())
        {
            if(pillar.getRoom().equals(getRoom()))
            {
                pillar.destroy();
            }
        }
        destroy();
    }
    }
}