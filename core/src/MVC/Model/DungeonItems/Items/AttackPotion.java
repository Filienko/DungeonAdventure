package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;

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
    public void increase(DungeonCharacter theCharacter)
    {
        theCharacter.setDamage(theCharacter.getDamage() + super.getStrength());
        theCharacter.setDamage(theCharacter.getDamage() + super.getStrength());
    }

    @Override
    public String getType()
    {
        return myType.toString();
    }
}
