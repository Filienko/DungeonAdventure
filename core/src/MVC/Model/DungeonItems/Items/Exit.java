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
     * Private Exit constructor, which calls the Item constructor to initialize its type and the
     * Entity Factory that generates it.
     * @param theEntityFactory The Entity Factory that generated the Exit.
     */
    private Exit(final EntityFactory theEntityFactory)
    {
        super("exit", theEntityFactory);
    }

    /**
     * This method retrieves an instance of the Exit using the Singleton design pattern.
     * @param theEntityFactory The Entity Factory that generated the Exit.
     * @return The instance of the Exit.
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
     * This method tells if the condition for the Hero to exit the Dungeon have been met. The condition is that
     * the Hero must have defeated the final boss, the Worm.
     * @return True if the condition has been met, false if it has not.
     */
    public static boolean isExitCondition()
    {
        return myExitCondition;
    }

    /**
     * This method sets the status of the exit condition.
     * @param theCondition True if the condition has been met, false if it has not.
     */
    public static void setExitCondition(final boolean theCondition)
    {
        myExitCondition = theCondition;
    }

    /**
     * This method tells if the Hero has exited the Dungeon.
     * @return True if they have, false if they have not.
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

    /**
     * The Exit activate behavior. If the exit condition has been met and if the Hero has obtained all 4 Pillars,
     * the Hero may exit. Otherwise, they will remain in the Dungeon.
     * @param theHero The Hero who is attempting to exit the Dungeon.
     */
    @Override
    public void activate(final Hero theHero)
    {
        if(isExitCondition() && theHero.getPillars() == 4)
        {
            myExited = true;
        }
    }
}
