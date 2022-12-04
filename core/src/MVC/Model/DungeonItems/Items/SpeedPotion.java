package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class SpeedPotion extends Potion
{
    private long myCurrentFrame;

    private EntityFactory myEntityFactory;

    public SpeedPotion(final EntityFactory theEntityFactory)
    {
        super(5,new Vec2(), theEntityFactory);
        myCurrentFrame = 0;
    }

    public SpeedPotion(final int theStrength, final Vec2 thePosition, final EntityFactory theEntityFactory)
    {
        super(theStrength, thePosition, theEntityFactory);
        myCurrentFrame = 0;
    }

    public void itemBehavior(final DungeonCharacter theCharacter)
    {
        theCharacter.setMaxSpeed(theCharacter.getMaxSpeed() + super.getStrength());
    }

    @Override
    public void update()
    {
        //itemBehavior();
        myCurrentFrame++;
    }
}