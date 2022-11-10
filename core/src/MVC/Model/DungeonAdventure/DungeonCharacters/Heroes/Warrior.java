package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Warrior extends Hero
{
    private String myName;
    public Warrior()
    {
        super("Warrior","Warrior",  125, 35, 60, 4, 0.8, new Vec2(), new Vec2());
    }

    public Warrior(final String theName, final Vec2 thePos)
    {
        super(theName,"Warrior",125, 35, 60, 4, 0.8, thePos,  new Vec2());
        this.myName = theName;
    }

    public int crushingBlow(DungeonCharacter theOpponent)
    {
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.4) {
            damage = attack(theOpponent, super.getWeapon().getBoundingBox());
        } else if (chance < 0.6) {
            Random rand = new Random();
            damage = rand.nextInt(75, 175);
        }
        theOpponent.applyDamage(damage);
        return damage;
    }

}