package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Exit extends Item
{
    private static Exit myExit;

    private Exit(final EntityFactory theEntityFactory)
    {
        super("exit", theEntityFactory);
    }

    public static Exit getInstance(final EntityFactory theEntityFactory) {
        if (myExit == null) {
            // instantiate it with the  constructor
            myExit = new Exit(theEntityFactory);
        }
        // return the instance
        return myExit;
    }

    public boolean checkFinishGame(final Hero theHero)
    {
        return theHero.getPillars() == 4;
    }

    @Override
    public void activate(final Hero theHero)
    {
        System.out.println(theHero.getPillars());
        if(checkFinishGame(theHero)) //is getMyEntityFactory().getHero() instead of theHero ok?
        {
            destroy();
            theHero.setActiveStatus(false);
        }
    }
}
