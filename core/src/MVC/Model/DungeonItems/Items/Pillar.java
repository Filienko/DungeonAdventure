package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Pillar extends Item
{
    private final String myName;
    private boolean myBroken;

    public Pillar(final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        myName = "pillar";
        myBroken = false;
    }


    public Pillar(final String theName, final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        myName = theName;
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

    @Override
    public void activate(final Hero theHero)
    {
        new HealingPotion(1,getMyEntityFactory()).activate(getMyEntityFactory().getHero());
    }
}