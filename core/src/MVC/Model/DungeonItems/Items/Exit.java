package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Exit extends Item
{
    private static Exit myExit;

    public Exit()
    {
        super("Exit", new Vec2((new Random()).nextInt(0, 20),
                (new Random()).nextInt(0, 12)));
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
