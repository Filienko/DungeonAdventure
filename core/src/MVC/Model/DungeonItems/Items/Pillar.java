package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Pillar extends Item
{
    private final String myName;

    private final EntityFactory myEntityFactory;

    private long myCurrentFrame;

    public Pillar(final String theName, final EntityFactory theEntityFactory)
    {
        super("Pillar", theEntityFactory);
        myName = theName;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public Pillar(final String theName, final boolean theActive, final EntityFactory theEntityFactory) //what is theActive field for?
    {
        super("Pillar", theEntityFactory);
        myName = theName;
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
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

    @Override
    public void update()
    {
        //activate();
        myCurrentFrame++;
    }



}

