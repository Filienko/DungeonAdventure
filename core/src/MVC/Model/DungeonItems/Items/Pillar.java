package MVC.Model.DungeonItems.Items;

public class Pillar extends Item
{
    private String myName;

    public Pillar(final String theName)
    {
        super("Pillar");
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
