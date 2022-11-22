package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

import java.util.Random;

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
        theCharacter.setHitPoints(theCharacter.getHitPoints() + super.getStrength());
    }

}
