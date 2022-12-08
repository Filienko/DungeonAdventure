package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public class SpeedPotion extends Potion
{
    public SpeedPotion(final EntityFactory theEntityFactory)
    {
        super("Speed Potion",2, theEntityFactory);
    }

    public SpeedPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Speed Potion",theStrength, theEntityFactory);
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setMaxSpeed(theHero.getMaxSpeed() + super.getStrength());
        destroy();
    }
}