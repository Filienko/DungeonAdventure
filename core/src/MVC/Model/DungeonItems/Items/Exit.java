package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Exit extends Item
{
    private static Exit myExit;
    private static boolean myExited = false;
    private static boolean myExitCondition = false;

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

    public static boolean isExitCondition()
    {
        return myExitCondition;
    }

    public static void setExitCondition(final boolean theCondition)
    {
        myExitCondition = theCondition;
    }

    public static boolean isExited() { return myExited; }

    public static void clean()
    {
        myExitCondition = false;
        myExited = false;
        myExit = null;
    }

    @Override
    public void activate(final Hero theHero)
    {
        //System.out.println(theHero.getPillars());
        if(isExitCondition() && theHero.getPillars() == 4)
        {
            myExited = true;
        }
    }
}
