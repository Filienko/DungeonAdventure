package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Lava extends Item
{
    private int myDamage;

    public Lava(final EntityFactory theEntityFactory)
    {
        super("lava", theEntityFactory);
        setDamage(2);
    }

    @Override
    public void activate(final Hero theHero)
    {
        theHero.setHitPoints(Math.max(0, theHero.getHitPoints() - myDamage));
        destroy();
    }

    public int getDamage()
    {
        return myDamage;
    }

    public void setDamage(final int theDamage) {
        if (theDamage > 0)
        {
            myDamage = theDamage;
        }
    }

}