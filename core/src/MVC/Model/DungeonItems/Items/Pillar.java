package MVC.Model.DungeonItems.Items;


import MVC.Model.Physics.Vec2;

public class Pillar extends Item
{
    private String myName;

    public Pillar(final String theName, Vec2 theLocation)
    {
        super("Pillar", theLocation);
        myName = theName;
    }

    public String getMyName()
    {
        return myName;
    }

    public void setMyName(final String myName)
    {
        this.myName = myName;
    }
}