package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;

public class HealingPotion extends Potion
{
    private StringBuilder myType;

    public HealingPotion()
    {
        super("Healing Potion",15);
        myType = new StringBuilder("Healing Potion");
    }

    public HealingPotion(final int theStrength)
    {
        super("Healing Potion",theStrength);
        myType = new StringBuilder("Healing Potion");
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setHitPoints(Math.min(10,theCharacter.getHitPoints() + super.getStrength()));
    }

    @Override
    public String getType()
    {
        return myType.toString();
    }
}