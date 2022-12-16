package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Exit extends Item
{

    /**
     * Reference to the singleton Exit instance
     */
    private static Exit myExit;
    /**
     * Whether the hero has activated the Exit
     */
    private static boolean myExited = false;
    /**
     * Whether the hero can activate the Exit
     */
    private static boolean myExitCondition = false;

    /**
     * Constructor for singleton Exit that takes one argument
     * @param theEntityFactory The EntityFactory to which the Exit belongs
     */
    private Exit(final EntityFactory theEntityFactory)
    {
        super("exit", theEntityFactory);
    }

    /**
     * @param theEntityFactory The EntityFactory to which the Exit belongs
     * @return The singleton instance of the Exit
     */
    public static Exit getInstance(final EntityFactory theEntityFactory) {
        if (myExit == null) {
            // instantiate it with the  constructor
            myExit = new Exit(theEntityFactory);
        }
        // return the instance
        return myExit;
    }

    /**
     * @return Whether the hero can activate the Exit
     */
    public static boolean isExitCondition()
    {
        return myExitCondition;
    }

    /**
     * @param theCondition Whether the hero can activate the Exit
     */
    public static void setExitCondition(final boolean theCondition)
    {
        myExitCondition = theCondition;
    }

    /**
     * @return Whether the hero has activated the Exit
     */
    public static boolean isExited() { return myExited; }

    /**
     * Resets the Exit's static class fields to an initial game state
     */
    public static void clean()
    {
        myExitCondition = false;
        myExited = false;
        myExit = null;
    }

    @Override
    public void activate(final Hero theHero)
    {
        if(isExitCondition() && theHero.getPillars() == 4)
        {
            myExited = true;
        }
    }
}
