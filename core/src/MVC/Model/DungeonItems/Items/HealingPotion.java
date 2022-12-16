package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public class HealingPotion extends Potion
{
    /**
     * The Healing Potion's constructor that calls its parent constructor to initialize its type, strength, and
     * the Entity Factory that generates it.
     * @param theEntityFactory The Entity Factory that generated the Entity.
     */
    public HealingPotion(final EntityFactory theEntityFactory)
    {
        super("healthPotion",2, theEntityFactory);
    }

    /**
     * The Healing Potion's activate behavior. It increases the hero's hit point count by its strength.
     * Then,the Healing Potion destroys itself.
     * @param theHero The Hero whose health will be increased.
     */
    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(Math.min(10,theHero.getHitPoints() + super.getStrength()));
        destroy();
    }
}