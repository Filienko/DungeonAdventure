package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Pillar extends Item
{
    private String myName;
    private boolean myBroken;
    private int myMonsterCounter;

    public Pillar(final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        setName("pillar");
        setMySize(new Vec2(64, 64));
        setMonsterCounter(0);
        myBroken = false;
    }

    public Pillar(final String theName, final int theMonsterCounter, final EntityFactory theEntityFactory)
    {
        super(theName, theEntityFactory);
        setName(theName);
        setMySize(new Vec2(64, 64));
        setMonsterCounter(theMonsterCounter);
        myBroken = false;
    }

    public void breakPillar()
    {
        if (myMonsterCounter == 0)
        {
            if (getMyEntityFactory().getAssets() != null) //added for testing, should i delete?
            {
                setMyAnimation(getMyEntityFactory().getAssets().getAnimation("brokenPillar"));
            }
            getMyEntityFactory().getHero().incrementPillars();
            myBroken = true;
        }
    }

    public String getName()
    {
        return myName;
    }

    public boolean isBroken() { return myBroken; }

    public int getMonsterCounter()
    {
        return myMonsterCounter;
    }

    public void setMonsterCounter(final int theMonsterCounter)
    {
        if (theMonsterCounter > 0)
        {
            myMonsterCounter = theMonsterCounter;
        }
        else
        {
            decrementMonsterCounter();
        }
    }

    public void decrementMonsterCounter()
    {
        if (myMonsterCounter > 0)
        {
            myMonsterCounter--;
        }
    }

    private void setName(final String theName)
    {
        if (theName != null)
        {
            myName = theName;
        }
    }
}