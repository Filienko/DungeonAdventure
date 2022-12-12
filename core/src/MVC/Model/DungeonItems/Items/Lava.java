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

    public Lava(final Vec2 theLocation, final EntityFactory theEntityFactory) //this constructor is never used?
    {
        super("lava", theEntityFactory);
        setDamage(2);
        setMyPos(theLocation);
    }

    @Override
    public void activate(final Hero theHero)
    {
        if(theHero.getHitPoints() >= myDamage)
        {
            theHero.setHitPoints(theHero.getHitPoints() - myDamage);
        }
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