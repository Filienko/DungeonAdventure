package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Pit extends Item
{
    private Vec2 myLocation;
    private int myDamageFall;

    private long myCurrentFrame;

    private final EntityFactory myEntityFactory;

    public Pit(final EntityFactory theEntityFactory)
    {
        super("Pit", new Vec2(), theEntityFactory);
        myDamageFall = 15;
        myCurrentFrame = 0;
        myEntityFactory = theEntityFactory;
    }

    public Pit(final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super("Pit", new Vec2(), theEntityFactory);
        myLocation = theLocation;
        myDamageFall = 15;
        myCurrentFrame = 0;
        myEntityFactory = theEntityFactory;
    }

    public Pit(final Vec2 theLocation, final int theDamageFall, final EntityFactory theEntityFactory)
    {
        super("Pit", new Vec2(), theEntityFactory);
        myLocation = theLocation;
        myDamageFall = theDamageFall;
        myCurrentFrame = 0;
        myEntityFactory = theEntityFactory;
    }

    public void itemBehavior(final DungeonCharacter theCharacter)
    {
        theCharacter.setHitPoints(theCharacter.getHitPoints() - myDamageFall);
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 theLocation)
    {
        myLocation = theLocation;
    }

    public int getDamageFall()
    {
        return myDamageFall;
    }

    public void setDamageFall(final int theDamageFall) { myDamageFall = theDamageFall; }
    @Override
    public void update()
    {
        //itemBehavior();
        myCurrentFrame++;
    }
}