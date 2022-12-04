package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;


public abstract class Potion extends Item
{
    private final int myStrength;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame;

    protected Potion(final EntityFactory theEntityFactory)
    {
        super("Potion", new Vec2(), theEntityFactory);
        myStrength = 15;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }
    protected Potion(final int theStrength, final Vec2 thePosition, final EntityFactory theEntityFactory)
    {
        super("Potion", thePosition, theEntityFactory);
        myStrength = theStrength;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    //made public for testing!! change back to default??
    public int getStrength()
    {
        return myStrength;
    }
}

