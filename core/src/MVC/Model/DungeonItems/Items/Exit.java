package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;

public class Exit extends Item
{
    private static Exit myExit;

    public Exit(Vec2 theCoordinate)
    {
        super("Exit", theCoordinate);
    } //should constructor be private if singleton??

    public static Exit getInstance(Vec2 theCoordinate) {
        if (myExit == null) {
            // instantiate it with the  constructor
            myExit = new Exit(theCoordinate);
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
