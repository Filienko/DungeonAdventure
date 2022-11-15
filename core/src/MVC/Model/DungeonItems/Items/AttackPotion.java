package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public class AttackPotion extends Potion
{
    public AttackPotion()
    {
        super(15);
    }

    public AttackPotion(final int theStrength)
    {
        super(theStrength);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setMaxDamageRange(theCharacter.getMaxDamageRange() + super.getStrength());
        theCharacter.setMinDamageRange(theCharacter.getMinDamageRange() + super.getStrength());
    }

}
