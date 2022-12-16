package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public class SpeedPotion extends Potion
{
    /**
     * The Speed Potion's constructor that calls its parent constructor to initialize its type, strength, and
     * the Entity Factory that generates it.
     * @param theEntityFactory The Entity Factory that generated the Entity.
     */
    public SpeedPotion(final EntityFactory theEntityFactory)
    {
        super("speedPotion",1, theEntityFactory);
    }

    /**
     * The Speed Potion's overloaded constructor that calls its parent constructor to initialize its type, strength, and
     * the Entity Factory that generates it.
     * @param theEntityFactory The Entity Factory that generated the Entity.
     * @param theStrength The amount that the Hero's speed will be increased by.
     */
    public SpeedPotion(final int theStrength, final EntityFactory theEntityFactory)
    {
        super("speedPotion",theStrength, theEntityFactory);
    }

    /**
     * The Speed Potion's activate behavior. It increases the hero's max speed by its strength.
     * Then,the Speed Potion destroys itself.
     * @param theHero The Hero whose max speed will be increased.
     */
    @Override
    public void activate(final Hero theHero)
    {
        theHero.setMaxSpeed(theHero.getMaxSpeed() + super.getStrength());
        destroy();
    }
}