package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class HealingPotion extends Potion
{
    private StringBuilder myType;

    public HealingPotion()
    {
        super("Healing Potion",15);
        myType = new StringBuilder("Healing Potion");
    }

    public HealingPotion(final int theStrength)
    {
        super("Healing Potion",theStrength);
        myType = new StringBuilder("Healing Potion");
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(Math.min(10,theHero.getHitPoints() + super.getStrength()));
    }

    @Override
    public String getType()
    {
        return myType.toString();
    }
}