package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public class HealingPotion extends Potion
{
    public HealingPotion(final EntityFactory theEntityFactory)
    {
        super("Healing Potion",2, theEntityFactory);
    }

    public HealingPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Healing Potion", theStrength, theEntityFactory);
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(Math.min(10,theHero.getHitPoints() + super.getStrength()));
        destroy();
    }
}