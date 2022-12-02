package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;

import java.util.Random;

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
        return theHero.getPillars().size() == 4;
    }

    @Override
    public void activate(final Hero theHero)
    {
        if(theHero.getPillars().size()==4)
            theHero.setActiveStatus(false);
    }
}
