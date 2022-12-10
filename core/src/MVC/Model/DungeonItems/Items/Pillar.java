package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Pillar extends Item
{
    private final String myName;

    public Pillar(final String theName, final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        myName = theName;
    }

    public Pillar(final String theName, final boolean theActive, final EntityFactory theEntityFactory) //what is theActive field for?
    {
        super("pillar", theEntityFactory);
        myName = theName;
    }

    public String getName()
    {
        return myName;
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.incrementPillars();
        destroy();
    }
}

