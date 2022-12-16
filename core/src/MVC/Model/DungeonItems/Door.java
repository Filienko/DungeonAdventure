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
     * Constructor that takes one argument
     * @param theEntityFactory The EntityFactory to which this Door belongs
     */
    public Door(final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64), new Vec2(),"Door", theEntityFactory);
        setMonsterCounter(4);
    }

    /**
     * Constructor that takes three arguments
     * @param theMonsterCounter The number of monsters linked to this Door that must be destroyed to break it
     * @param theLocation The initial position
     * @param theEntityFactory The EntityFactory to which this Door belongs
     */
    public Door(final int theMonsterCounter, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64), theLocation,"Door", theEntityFactory);
        setMonsterCounter(theMonsterCounter);

    }

    /**
     * Constructor that takes four arguments
     * @param theRoom The room this Door is located in
     * @param theMonsterCounter The number of monsters linked to this Door that must be destroyed to break it
     * @param theLocation The initial position
     * @param theEntityFactory The EntityFactory to which this Door belongs
     */
    public Door(final Vec2 theRoom,final int theMonsterCounter,final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64),theLocation,"Door", theEntityFactory);
        setMonsterCounter(theMonsterCounter);
        setRoom(theRoom);
    }

    /**
     * @return The number of monsters linked to this Door that must be destroyed to break it
     */
    public int getMonsterCounter()
    {
        return myMonsterCounter;
    }

    /**
     * @param theMonsterCounter The number of monsters linked to this Door that must be destroyed to break it
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