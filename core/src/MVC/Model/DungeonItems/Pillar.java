package MVC.Model.DungeonItems;


import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;

public class Pillar extends Entity
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
