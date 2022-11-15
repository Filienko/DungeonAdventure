package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Healable;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Priestess extends Hero implements Healable
{
    private String myName;

    public Priestess()
    {
        super("Priestess", "Priestess", 75, 25, 45, 5, 0.7, new Vec2(), new Vec2());
    }

    public Priestess(final String theName, final Vec2 thePos)
    {
        super(theName,"Priestess",75, 25, 45, 5, 0.7, thePos, new Vec2());
        this.myName = theName;
    }

    public int healHero(Hero theHero)
    {
        return healCharacter(theHero);
    }

    public int attack(DungeonCharacter theOpponent)
    {
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.2) {
            return damage;
        } else if (chance < 0.6) {
            damage = attack(theOpponent, super.getWeapon().getBoundingBox());
        } else if (chance < 1.0) {
            damage += attack(theOpponent);
        }
        return damage;
    }

    @Override
    public String getName()
    {
        return myName;
    }

    private void setName(final String theName)
    {
        myName = theName;
    }
}