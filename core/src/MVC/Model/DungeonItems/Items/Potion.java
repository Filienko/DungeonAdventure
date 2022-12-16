package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public abstract class Potion extends Item
{
    /**
     * How much this potion alters a stat on the Hero
     */
    private int myStrength;

    /**
     * Potion constructor that calls the Item constructor to initialize its type and the Entity Factory that generated
     * it. It also sets the Potion's strength.
     * @param theType The specific Potion type.
     * @param theStrength The Potion's strength.
     * @param theEntityFactory The Entity Factory that generated the Potion.
     */
    protected Potion(String theType, final int theStrength, final EntityFactory theEntityFactory)
    {
        super(theType, theEntityFactory);
        setStrength(theStrength);
    }

    /**
     * This method retrieves the Potion's strength.
     * @return The Potion's strength.
     */
    public int getStrength()
    {
        return myStrength;
    }

    /**
     * This method sets the Potion's strength.
     * @param theStrength The Potion's strength.
     */
    protected void setStrength(final int theStrength)
    {
        if (theStrength > 0)
        {
            myStrength = theStrength;
        }
    }
}