package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class HealingPotion extends Potion
{
    private long myCurrentFrame;

    private final EntityFactory myEntityFactory;

    public HealingPotion(final EntityFactory theEntityFactory)
    {
        super(15,new Vec2(), theEntityFactory);
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public HealingPotion(final int theStrength, final Vec2 thePosition, final EntityFactory theEntityFactory)
    {
        super(theStrength, thePosition, theEntityFactory);
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public void itemBehavior(final DungeonCharacter theCharacter)
    {
        theCharacter.setHitPoints(theCharacter.getHitPoints() + super.getStrength());
    }

    @Override
    public void update()
    {
        //itemBehavior();
        myCurrentFrame++;
    }
}