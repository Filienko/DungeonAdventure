package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class AttackPotion extends Potion
{
    private long myCurrentFrame;

    private final EntityFactory myEntityFactory;

    public AttackPotion(final EntityFactory theEntityFactory)
    {
        super(5,new Vec2(), theEntityFactory);
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public AttackPotion(final int theStrength, final Vec2 thePosition, final EntityFactory theEntityFactory)
    {
        super(theStrength, thePosition, theEntityFactory);
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }
    public void itemBehavior(final DungeonCharacter theCharacter)
    {
        theCharacter.setMaxDamageRange(theCharacter.getMaxDamageRange() + super.getStrength());
        theCharacter.setMinDamageRange(theCharacter.getMinDamageRange() + super.getStrength());
    }

    @Override
    public void update()
    {
        //itemBehavior(); //what should param be?
        myCurrentFrame++;
    }

}
