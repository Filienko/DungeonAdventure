package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public class AttackPotion extends Potion
{

    public AttackPotion(final EntityFactory theEntityFactory)
    {
        super("attackPotion",1, theEntityFactory);
    }

    public AttackPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("attackPotion",theStrength, theEntityFactory);
    }

    public void activate(final Hero theHero)
    {
        theHero.setDamage(theHero.getDamage() + super.getStrength());
        destroy();
    }
}
