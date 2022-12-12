package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public abstract class Potion extends Item
{
    private int myStrength; //should this be changed back to final?

    protected Potion(String theType, final int theStrength, final EntityFactory theEntityFactory)
    {
        super(theType, theEntityFactory);
        setStrength(theStrength);
    }

    public int getStrength()
    {
        return myStrength;
    }
    protected void setStrength(final int theStrength)
    {
        if (theStrength > 0)
        {
            myStrength = theStrength;
        }
    }
}