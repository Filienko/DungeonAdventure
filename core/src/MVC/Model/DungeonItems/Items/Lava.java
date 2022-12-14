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
        theHero.setHitPoints(Math.min(0, theHero.getHitPoints() - myDamage));
        //moved hero.destroy to dungeoncharacter's collide for testing

        destroy();
    }

    public int getDamage()
    {
        return myDamage;
    }

    public void setDamage(final int theDamageFall) {
        if (theDamageFall > 0)
        {
            myDamage = theDamageFall;
        }
    }

}