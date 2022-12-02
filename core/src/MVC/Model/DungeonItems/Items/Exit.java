package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Exit extends Item
{
    private static Exit myExit = new Exit();

    private Exit()
    {
        super("Exit");
    }

    public static Exit getInstance() {
        // return the instance
        return myExit;
    }

    public boolean checkFinishGame(Hero theHero)
    {
        return theHero.getPillars().size() == 4;
    }

    @Override
    public void activate(final Hero theHero)
    {
        if(theHero.getPillars().size()==4)
            theHero.setActiveStatus(false);
    }
}