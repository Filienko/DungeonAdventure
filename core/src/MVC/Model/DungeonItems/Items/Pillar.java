package MVC.Model.DungeonItems.Items;


import MVC.Model.DungeonItems.Items.Item;

public class Pillar extends Item
{
    private String myName;

    public Pillar(final String theName)
    {
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
