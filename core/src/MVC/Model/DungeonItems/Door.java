package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

public class Door extends Entity
{
    /**
     * The number of monsters linked to this Door that must be destroyed to break it
     */
    private int myMonsterCounter;

    /**
     * Door constructor that calls the Entity constructor to initialize its size, position, type, and the Entity
     * Factory that generated it. It also sets its monster counter.
     * @param theRoom The Room the Door is in.
     * @param theMonsterCounter The number of Monsters in the same Room as the Door.
     * @param theLocation The location of the Door.
     * @param theEntityFactory The Entity Factory that generated the Door.
     */
    public Door(final Vec2 theRoom,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64),theLocation,"Door", theEntityFactory);
        setMonsterCounter(theMonsterCounter);
        setRoom(theRoom);
    }

    /**
     * This method retrieves the number of Monsters in the same Room as the Door.
     * @return The number of Monsters.
     */
    public int getMonsterCounter()
    {
        return myMonsterCounter;
    }

    /**
     * This method sets the number of Monsters in the same Room as the Door.
     * @param theMonsterCounter The new number of Monsters.
     */
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

    /**
     * Decrements the number of monsters linked to this Door that must be destroyed to break it
     * if doing so would not cause the counter to go below 0.
     */
    public void decrementMonsterCounter()
    {
        if (myMonsterCounter > 0)
        {
            myMonsterCounter--;
        }
    }


    @Override
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