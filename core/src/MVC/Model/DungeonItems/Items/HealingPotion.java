package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public class HealingPotion extends Potion
{
    public HealingPotion()
    {
        super(15);
    }

    public HealingPotion(final int theStrength)
    {
        super(theStrength);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setHitPoints(theCharacter.getHitPoints() + myStrength);
    }

}
