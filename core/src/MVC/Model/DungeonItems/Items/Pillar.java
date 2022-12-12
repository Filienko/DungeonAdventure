package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Pillar extends Item
{
    private String myName; //should this be changed back to final?
    private boolean myBroken;

    public Pillar(final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        setName("pillar");
        myBroken = false;
    }

    public Pillar(final String theName, final EntityFactory theEntityFactory)
    {
        super(theName, theEntityFactory);
        setName(theName);
        myBroken = false;
    }

    public void breakPillar()
    {
        setMyAnimation(getMyEntityFactory().getAssets().getAnimation("brokenPillar"));
        getMyEntityFactory().getHero().incrementPillars();
        myBroken = true;
    }


    public String getName()
    {
        return myName;
    }

    public boolean isBroken() { return myBroken; }

    private void setName(final String theName)
    {
        if (theName != null)
        {
            myName = theName;
        }
    }
}