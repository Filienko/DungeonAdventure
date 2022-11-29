package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

public class SpeedPotion extends Potion
{
    public SpeedPotion()
    {
        super(5);
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