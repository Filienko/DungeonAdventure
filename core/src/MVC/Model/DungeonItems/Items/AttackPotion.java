package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class AttackPotion extends Potion
{

    public AttackPotion(final EntityFactory theEntityFactory)
    {
        //super(5, new Vec2(), theEntityFactory);
        super("Attack Potion",5, theEntityFactory);
    }

    public AttackPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Attack Potion",theStrength, theEntityFactory);
    }

    public void activate(final Hero theHero)
    {
        theHero.setDamage(theHero.getDamage() + super.getStrength());
        theHero.setDamage(theHero.getDamage() + super.getStrength());
    }
}
