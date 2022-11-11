package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Thief extends Hero
{
    private String myName;

    public Thief()
    {
        super("Thief", "Thief", 75, 20, 40, 6, 0.8, new Vec2(), new Vec2());
    }

    public Thief(final String theName, final Vec2 thePos)
    {
        super(theName, "Thief",75, 20, 40, 6, 0.8, thePos, new Vec2());
        this.myName = theName;
    }

    public int surpriseAttack(DungeonCharacter theOpponent)
    {
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.2) {
            return damage;
        } else if (chance < 0.6) {
            damage = attack(theOpponent, super.getWeapon().getBoundingBox());
        } else if (chance < 1.0) {
            damage += surpriseAttack(theOpponent);
        }
        return damage;
    }
}