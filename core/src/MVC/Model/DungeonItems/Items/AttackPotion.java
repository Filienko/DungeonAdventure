package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public class AttackPotion
{
    private final int myStrength;

    public AttackPotion()
    {
        this.myStrength = 10;
    }

    public AttackPotion(final int myStrength)
    {
        this.myStrength = myStrength;
    }

    public void increaseAttack(DungeonCharacter theCharacter)
    {
        theCharacter.setMaxDamageRange(theCharacter.getMaxDamageRange() + myStrength);
        theCharacter.setMinDamageRange(theCharacter.getMinDamageRange() + myStrength);
    }

}
