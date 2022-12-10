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
        super(new Vec2(64, 64), new Vec2((new Random()).nextInt(1, 20),
                (new Random()).nextInt(1, 12)), theType,  theEntityFactory);
        setMyPos(new Vec2((new Random()).nextInt(1, 20),
                (new Random()).nextInt(1, 12)));
        setMyAnimation(getMyEntityFactory().getAssets().getAnimation(getType()));
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