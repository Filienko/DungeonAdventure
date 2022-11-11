package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public class HealingPotion extends Item
{
    private final int myStrength;

    public HealingPotion()
    {
        this.myStrength = 15;
    }

    public HealingPotion(final int myStrength)
    {
        this.myStrength = myStrength;
    }

    public void increaseHealth(DungeonCharacter theCharacter)
    {
        theCharacter.setHitPoints(theCharacter.getHitPoints() + myStrength);
    }

}
