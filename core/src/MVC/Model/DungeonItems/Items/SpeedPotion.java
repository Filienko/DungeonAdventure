package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

public class SpeedPotion extends Potion
{
    public SpeedPotion()
    {
        super(5,new Vec2());
    }

    public SpeedPotion(final int theStrength, Vec2 thePosition)
    {
        super(theStrength, thePosition);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setMaxSpeed(theCharacter.getMaxSpeed() + super.getStrength());
    }
}