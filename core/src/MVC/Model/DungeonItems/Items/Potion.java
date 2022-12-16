package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public abstract class Potion extends Item
{
    /**
     * How much this potion alters a stat on the Hero
     */
    private int myStrength;

    /**
     * Constructor that takes three arguments
     * @param theType The type of potion
     * @param theStrength How much this potion alters a stat on the Hero
     * @param theEntityFactory The EntityFactory to which this Potion belongs
     */
    protected Potion(String theType, final int theStrength, final EntityFactory theEntityFactory)
    {
        super(theType, theEntityFactory);
        setStrength(theStrength);
    }

    /**
     * @return How much this potion alters a stat on the Hero
     */
    public int getStrength()
    {
        return myStrength;
    }

    /**
     * @param theStrength How much this potion alters a stat on the Hero
     */
    protected void setStrength(final int theStrength)
    {
        if (theStrength > 0)
        {
            myStrength = theStrength;
        }
    }
}