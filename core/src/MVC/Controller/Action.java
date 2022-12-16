package MVC.Controller;

public class Action
{
    /**
     * The name of the action
     */
    private final String myName;
    /**
     * Whether it is the start or end of an action
     */
    private final String myType;

    /**
     * Action constructor that takes two arguments
     * @param theName The name of the action
     * @param theType Whether it is the start or end of an action
     */
    public Action(final String theName, final String theType)
    {
        myName = theName;
        myType = theType;
    }

    /**
     * @return The name of the action
     */
    public String getName()     { return myName; }

    /**
     * @return Whether the action is a start or end
     */
    public String getType()     { return myType; }

    @Override
    public String toString()    { return myName + " " + myType; }
}
