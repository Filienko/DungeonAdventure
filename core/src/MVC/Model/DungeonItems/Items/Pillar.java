package MVC.Model.DungeonItems.Items;

public class Pillar extends Item
{
    final public String myName;

    public Pillar(final String theName)
    {
        super("Pillar");
        myName = theName;
    }

    public String getName()
    {
        return myName;
    }
}

