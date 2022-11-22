package MVC.Model.DungeonItems.Items;

import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Pillar extends Item
{
    private String myName;

    public Pillar(final String theName)
    {
        super("Pillar", new Vec2((new Random()).nextInt(0, 20),
                (new Random()).nextInt(0, 12)));
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
