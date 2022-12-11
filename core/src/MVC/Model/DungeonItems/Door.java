package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Physics;
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

        if (myMonsterCounter <= 0)
        {
            System.out.println(" WAS 0");

            for (var p : getMyEntityFactory().getEntities("pillar"))
            {
                Pillar pillar = (Pillar) p;
                if(!pillar.isBroken() && pillar.getRoom().equals(getRoom()))
                {
                    pillar.breakPillar();
                }
            }

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