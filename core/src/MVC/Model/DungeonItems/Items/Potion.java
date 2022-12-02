package MVC.Model.DungeonItems.Items;

public abstract class Potion extends Item
{
    private final int myStrength;

    protected Potion(String theType, final int theStrength)
    {
        super(theType);
        myStrength = theStrength;
    }

    int getStrength()
    {
        return myStrength;
    }
}
