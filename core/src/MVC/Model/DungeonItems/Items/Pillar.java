package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Pillar extends Item
{
    final public String myName;

    public Pillar(final String theName)
    {
        super("Pillar");
        myName = theName;
    }

    public Pillar(final String theName, final boolean theActive)
    {
        super("Pillar");
        myName = theName;
        destroy();

    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.incrementPillars();
        this.destroy();
    }

    public String getName()
    {
        return myName;
    }
}

