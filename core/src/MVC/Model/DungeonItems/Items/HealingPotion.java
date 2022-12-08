package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class HealingPotion extends Potion
{
    public HealingPotion(final EntityFactory theEntityFactory)
    {
        super("Healing Potion",15, theEntityFactory);
    }

    public HealingPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Healing Potion", theStrength, theEntityFactory);
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(Math.min(10,theHero.getHitPoints() + super.getStrength()));
    }
}