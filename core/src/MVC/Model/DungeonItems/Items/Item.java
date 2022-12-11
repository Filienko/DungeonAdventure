package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public abstract class Item extends Entity
{
    protected Item(final String theType, final EntityFactory theEntityFactory)
    {
        super(new Vec2(32, 32), new Vec2((new Random()).nextInt(2, 10),
                (new Random()).nextInt(2, 10)), theType,  theEntityFactory);
        if(getMyEntityFactory().getAssets()!=null)
        {
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation(getType()));
        }
    }

    @Override
    public void update() {}

    @Override
    public void setRoom(final Vec2 theRoom)
    {
        super.setRoom(theRoom);
        setMyPos(Physics.getPosition((int) theRoom.getMyX(), (int) theRoom.getMyY(),
                (int) getMyPos().getMyX(),(int) getMyPos().getMyY()));
    }

    public abstract void activate(final Hero theHero);
}