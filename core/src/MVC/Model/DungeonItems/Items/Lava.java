package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Lava extends Item
{
    /**
     * The amount of damage the Lava inflicts on the Hero.
     */
    private int myDamage;

    /**
     * Lava constructor that calls the Item constructor to initialize the Lava's type, damage amount, and the
     * Entity Factory that generated it.
     * @param theEntityFactory The Entity Factory that generated the Lava.
     */
    public Lava(final EntityFactory theEntityFactory)
    {
        super("lava", theEntityFactory);
        setDamage(2);
    }

    /**
     * The Lava's activate behavior. It will subtract its damage amount from the Hero's hit point count, if doing so
     * would not cause the Hero's hit points to go below 0. Otherwise, it sets the Hero's hit points to 0. Finally,
     * the Lava destroys itself.
     * @param theHero The Hero who will be affected by the Item.
     */
    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(Math.max(0, theHero.getHitPoints() - myDamage));
        destroy();
    }

    /**
     * This method retrieves the amount of damage the Lava can inflict.
     * @return The Lava's damage amount.
     */
    public int getDamage()
    {
        return myDamage;
    }

    /**
     * This method sets the amount of damage the Lava can inflict.
     * @param theDamage The Lava's new damage amount.
     */
    public void setDamage(final int theDamage) {
        if (theDamage > 0)
        {
            myDamage = theDamage;
        }
    }

}