package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public abstract class Item extends Entity
{
    /**
     * Item constructor that calls Entity's constructor to initialize its size, position, type, Entity Factory, and
     * animation.
     * @param theType The type of Item it is.
     * @param theEntityFactory The Entity Factory that generated the Item.
     */
    protected Item(final String theType, final EntityFactory theEntityFactory) {
        //(final Vec2 theSize, final Vec2 thePos, final String theType, final EntityFactory theEntityFactory)
        super(new Vec2(32, 32), new Vec2((new Random()).nextInt(2, 10),
                (new Random()).nextInt(2, 10)), theType, theEntityFactory);
        if (getMyEntityFactory().getAssets() != null) {
            setMyAnimation(getMyEntityFactory().getAssets().getAnimation(getType()));
        }
    }

    /**
     * This method sets the Room the Entity is located in.
     * @param theRoom The Entity's new Room, represented by a Vec2.
     */
    @Override
    public void setRoom(final Vec2 theRoom)
    {
        super.setRoom(theRoom);
        setMyPos(Physics.getPosition((int) theRoom.getMyX(), (int) theRoom.getMyY(),
                (int) getMyPos().getMyX(),(int) getMyPos().getMyY()));
        setMyPreviousPos(getMyPos());
    }

    /**
     * The Item's activate behavior.
     * @param theHero The Hero who will be affected by the Item.
     */
    public void activate(final Hero theHero){};
}