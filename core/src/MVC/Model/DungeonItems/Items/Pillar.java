package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class Pillar extends Item
{
    private final String myName;

    public Pillar(final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        myName = "pillar";
    }


    public Pillar(final String theName, final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        myName = theName;
    }

    @Override
    public void destroy()
    {
        setMyAnimation(getMyEntityFactory().getAssets().getAnimation("brokenPillar"));
        getMyEntityFactory().getHero().incrementPillars();
        if(getMyAnimation().hasEnded())
        {
            super.destroy();
        }
        destroy();
    }


    public String getName()
    {
        return myName;
    }

    @Override
    public void activate(final Hero theHero)
    {
        new HealingPotion(1,getMyEntityFactory()).activate(getMyEntityFactory().getHero());
    }
}

