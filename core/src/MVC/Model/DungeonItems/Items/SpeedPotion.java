package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class SpeedPotion extends Potion
{
    private final StringBuilder myType;

    public SpeedPotion()
    {
        super("Speed Potion",3);
        myType = new StringBuilder("Speed Potion");
    }

    public SpeedPotion(final int theStrength)
    {
        super("Speed Potion",theStrength);
        myType = new StringBuilder("Speed Potion");
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setMaxSpeed(theHero.getMaxSpeed() + super.getStrength());
    }

    @Override
    public String getType()
    {
        return myType.toString();
    }
}