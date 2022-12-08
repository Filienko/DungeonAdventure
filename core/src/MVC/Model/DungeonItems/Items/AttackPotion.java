package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class AttackPotion extends Potion
{
    private StringBuilder myType;

    private long myCurrentFrame;

    private final EntityFactory myEntityFactory;

    public AttackPotion(final EntityFactory theEntityFactory)
    {
        //super(5, new Vec2(), theEntityFactory);
        super("Attack Potion",5, theEntityFactory);
        myType = new StringBuilder("Attack Potion");
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public AttackPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("Attack Potion",theStrength, theEntityFactory);
        myType = new StringBuilder("Attack Potion");
        myEntityFactory = theEntityFactory;
        myCurrentFrame = 0;
    }

    public void activate(final Hero theHero)
    {
        theHero.setDamage(Math.max(10, theHero.getDamage() + super.getStrength()));
    }

    @Override
    public String getType()
    {
        return myType.toString();
    }
    @Override
    public void update()
    {
        //activate(); //what should param be?
        myCurrentFrame++;
    }

}
