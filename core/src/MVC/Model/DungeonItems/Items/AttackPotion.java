package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public class AttackPotion extends Potion
{

    public AttackPotion(final EntityFactory theEntityFactory)
    {
        super("Attack Potion",2, theEntityFactory);
    }

    public AttackPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Attack Potion",theStrength, theEntityFactory);
    }

    public void activate(final Hero theHero)
    {
        theHero.setDamage(Math.max(10, theHero.getDamage() + super.getStrength()));
        destroy();
    }

}
