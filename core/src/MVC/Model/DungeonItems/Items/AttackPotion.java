package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

public class AttackPotion extends Potion
{
    public AttackPotion()
    {
        super(5,new Vec2());
    }

    public AttackPotion(final int theStrength, Vec2 thePosition)
    {
        super(theStrength, thePosition);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setMaxDamageRange(theCharacter.getMaxDamageRange() + super.getStrength());
        theCharacter.setMinDamageRange(theCharacter.getMinDamageRange() + super.getStrength());
    }

}
