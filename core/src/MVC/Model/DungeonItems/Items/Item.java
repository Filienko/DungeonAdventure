package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public abstract class Item extends Entity
{
    private Vec2 myLocation;

    protected Item(final String theType, final EntityFactory theEntityFactory)
    {
        super(new Vec2(64, 64), new Vec2((new Random()).nextInt(1, 20),
                (new Random()).nextInt(1, 12)), theType,  theEntityFactory);
        myLocation = new Vec2((new Random()).nextInt(1, 20),
                (new Random()).nextInt(1, 12));
    }

    @Override
    public void update() {}

    public abstract void activate(final Hero theHero);

    protected Vec2 getMyLocation()
    {
        return myLocation;
    }

    protected void setMyLocation(final Vec2 theLocation)
    {
        myLocation = theLocation;
    }
}