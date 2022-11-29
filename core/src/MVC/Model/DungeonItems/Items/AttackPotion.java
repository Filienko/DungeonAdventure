package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

public class AttackPotion extends Potion
{
    public AttackPotion()
    {
        super("Attack Potion",5);
    }

    public AttackPotion(final int theStrength)
    {
        super("Attack Potion",theStrength);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setDamage(theCharacter.getDamage() + super.getStrength());
        theCharacter.setDamage(theCharacter.getDamage() + super.getStrength());
    }
}
