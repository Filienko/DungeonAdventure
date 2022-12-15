package MVC.Controller;

public class Action
{
    private final String myName;
    private final String myType;

    public Action(final String theName, final String theType)
    {
        myName = theName;
        myType = theType;
    }

    public String getName()     { return myName; }
    public String getType()     { return myType; }
    public String toString()    { return myName + " " + myType; }
}
