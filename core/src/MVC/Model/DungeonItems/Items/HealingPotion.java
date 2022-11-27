package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

public class HealingPotion extends Potion
{
    public HealingPotion()
    {
        super(15,new Vec2());
    }

    public HealingPotion(final int theStrength, Vec2 thePosition)
    {
        super(theStrength, thePosition);
    }

    @Override
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setHitPoints(theCharacter.getHitPoints() + super.getStrength());
    }

}