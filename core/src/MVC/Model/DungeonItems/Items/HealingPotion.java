package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Dungeon;

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
