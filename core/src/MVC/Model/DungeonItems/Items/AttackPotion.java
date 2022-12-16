package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public class AttackPotion extends Potion
{
    /**
     * The Attack Potion's constructor that calls its parent constructor to initialize its type, strength, and
     * the Entity Factory that generates it.
     * @param theEntityFactory The Entity Factory that generated the Entity.
     */
    public AttackPotion(final EntityFactory theEntityFactory)
    {
        super("attackPotion",1, theEntityFactory);
    }

    /**
     * The Attack Potion's activate behavior. It increases the hero's damage amount by its strength.
     * Then,the Attack Potion destroys itself.
     * @param theHero The Hero whose damage amount will be increased.
     */
    public void activate(final Hero theHero)
    {
        theHero.setDamage(Math.min(10, theHero.getDamage() + super.getStrength()));
        destroy();
    }
}
