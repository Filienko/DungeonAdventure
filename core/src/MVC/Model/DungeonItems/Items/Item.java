package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public abstract class Item extends Entity
{
    private Vec2 myLocation;

    private static String myType;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame;

    protected Item(final String theType, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super(theLocation,new Vec2(), theEntityFactory);
        myType = theType;
        myLocation = theLocation;
        myCurrentFrame = 0;
        myEntityFactory = theEntityFactory;
    }

    //make abstract method for whatever each item does to the hero
    protected abstract void itemBehavior(final DungeonCharacter theCharacter);


    protected Vec2 getMyLocation()
    {
        return myLocation;
    }

    protected void setMyLocation(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
    }

    public String getType()
    {
        return myType;
    }
}