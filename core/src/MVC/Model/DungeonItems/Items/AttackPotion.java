package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;

public class AttackPotion extends Potion
{
    private StringBuilder myType;

    public AttackPotion()
    {
        super("Attack Potion",5);
        myType = new StringBuilder("Attack Potion");
    }

    public AttackPotion(final int theStrength)
    {
        super("Attack Potion",theStrength);
        myType = new StringBuilder("Attack Potion");
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setDamage(theHero.getDamage() + super.getStrength());
        theHero.setDamage(theHero.getDamage() + super.getStrength());
    }

    @Override
    public String getType()
    {
        return myType.toString();
    }
}
