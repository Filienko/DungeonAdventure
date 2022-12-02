package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Pillar extends Item
{
    final public String myName;

    public Pillar(final String theName)
    {
        super("Pillar");
        myName = theName;
    }

    @Override
    public void activate(final Hero theHero)
    {
        var pillars = theHero.getPillars();
        pillars.add(this);
        theHero.setPillars(pillars);
    }

    public String getName()
    {
        return myName;
    }
}

