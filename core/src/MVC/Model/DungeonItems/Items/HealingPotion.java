package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class HealingPotion extends Potion
{
    private StringBuilder myType;

    private long myCurrentFrame;

    private final EntityFactory myEntityFactory;

    public HealingPotion(final EntityFactory theEntityFactory)
    {
        super("Healing Potion",15, theEntityFactory);
        myType = new StringBuilder("Healing Potion");
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public HealingPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Healing Potion", theStrength, theEntityFactory);
        myType = new StringBuilder("Healing Potion");
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
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
    @Override
    public void update()
    {
        //activate();
        myCurrentFrame++;
    }
}