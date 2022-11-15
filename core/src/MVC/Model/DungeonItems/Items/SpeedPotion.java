package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public class SpeedPotion extends Potion
{
    public SpeedPotion()
    {
         super(2);
    }

    public SpeedPotion(final int theStrength)
    {
        super(theStrength);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setMaxSpeed(theCharacter.getMaxSpeed() + super.getStrength());
    }
}
