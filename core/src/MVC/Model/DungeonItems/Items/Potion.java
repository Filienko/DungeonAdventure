package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;


public abstract class Potion extends Item
{
    private final int myStrength;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame; //necessary?

    protected Potion(String theType, final int theStrength, final EntityFactory theEntityFactory)
    {
        super(theType, theEntityFactory);
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