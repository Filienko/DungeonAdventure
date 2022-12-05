package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Exit extends Item
{
    private static Exit myExit;

    //private static final Exit myExit = new Exit();

    private static EntityFactory myEntityFactory;
    private static long myCurrentFrame;

    private Exit(final EntityFactory theEntityFactory) //double check that constructor should be private
    {
        super("Exit", theEntityFactory);
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public static Exit getInstance(final EntityFactory theEntityFactory) {
        if (myExit == null) {
            // instantiate it with the  constructor
            myExit = new Exit(theEntityFactory);
        }
        // return the instance
        return myExit;
    }

    public boolean checkFinishGame(final Hero theHero) //can this be the itemBehavior?
    {
        return theHero.getPillars() == 4;
    }

    @Override
    public void activate(final Hero theHero)
    {
        if(theHero.getPillars()==4)
            theHero.setActiveStatus(false);
    }

    @Override
    public void update() {
        //itemBehavior();
        myCurrentFrame++;
    }
}
