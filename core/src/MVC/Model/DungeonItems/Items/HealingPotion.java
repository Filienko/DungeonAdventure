package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public class HealingPotion extends Potion
{
    public HealingPotion()
    {
        super("Healing Potion",15);
    }

    public HealingPotion(final int theStrength)
    {
        super("Healing Potion",theStrength);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setHitPoints(Math.min(10,theCharacter.getHitPoints() + super.getStrength()));
    }

}