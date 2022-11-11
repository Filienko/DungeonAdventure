package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public class SpeedPotion
{
    private final int myStrength;

    public SpeedPotion()
    {
        this.myStrength = 2;
    }

    public SpeedPotion(final int myStrength)
    {
        this.myStrength = myStrength;
    }

    public void increaseHealth(DungeonCharacter theCharacter)
    {
        theCharacter.setMaxSpeed(theCharacter.getMaxSpeed() + myStrength);
    }
}
