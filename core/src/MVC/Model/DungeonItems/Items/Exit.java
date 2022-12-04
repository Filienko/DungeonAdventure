package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;

public class Exit extends Item
{
    private static Exit myExit;

    private static EntityFactory myEntityFactory;
    private static long myCurrentFrame;

    private Exit(final Vec2 theCoordinate, final EntityFactory theEntityFactory) //double check that constructor should be private
    {
        super("Exit", theCoordinate, theEntityFactory);
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public static Exit getInstance(final Vec2 theCoordinate, final EntityFactory theEntityFactory) {
        if (myExit == null) {
            // instantiate it with the  constructor
            myExit = new Exit(theCoordinate, theEntityFactory);
        }
        // return the instance
        return myExit;
    }

    public void itemBehavior(DungeonCharacter theCharacter) { }

    public boolean checkFinishGame(final Hero theHero) //can this be the itemBehavior?
    {
        if (theHero.getPillars().size()==4)
        {
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        //itemBehavior();
        myCurrentFrame++;
    }
}
