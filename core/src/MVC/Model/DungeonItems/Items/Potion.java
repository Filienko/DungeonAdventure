package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;


public abstract class Potion extends Item
{
    private final int myStrength;

    protected Potion(String theType, final int theStrength, final EntityFactory theEntityFactory)
    {
        super(theType, theEntityFactory);
        myStrength = theStrength;
    }

    //made public for testing!! change back to default??
    public int getStrength()
    {
        return myStrength;
    }
}