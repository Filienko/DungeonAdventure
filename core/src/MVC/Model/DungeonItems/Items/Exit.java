package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Exit extends Item
{
    private static Exit myExit;

    public Exit()
    {
        super("Exit");
    }

    public static Exit getInstance() {
        if (myExit == null) {
            // instantiate it with the  constructor
            myExit = new Exit();
        }
        // return the instance
        return myExit;
    }

    public boolean checkFinishGame(Hero theHero)
    {
        if (theHero.getPillars().size()==4)
        {
            return true;
        }
        return false;
    }
}
