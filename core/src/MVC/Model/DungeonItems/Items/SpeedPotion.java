package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class SpeedPotion extends Potion
{
    private final StringBuilder myType;

    private long myCurrentFrame;

    private EntityFactory myEntityFactory;

    public SpeedPotion(final EntityFactory theEntityFactory)
    {
        super("Speed Potion",3, theEntityFactory);
        myType = new StringBuilder("Speed Potion");
        myCurrentFrame = 0;
    }

    public SpeedPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Speed Potion",theStrength, theEntityFactory);
        myType = new StringBuilder("Speed Potion");
        myCurrentFrame = 0;
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

    @Override
    public void update()
    {
        //activate();
        myCurrentFrame++;
    }
}