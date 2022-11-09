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
        super("Warrior","Warrior", true,  125, 35, 60, 0.2, 4, 0.8, new Vec2());
    }

    public Warrior(final String theName, final Vec2 thePos)
    {
        super(theName,"Warrior", true,125, 35, 60, 0.2, 4, 0.8, thePos);
        this.myName = theName;
    }

    public int crushingBlow(DungeonCharacter theOpponent)
    {
        double chance = Math.random();
        int damage = 0;
        if (chance < 0.4) {
            damage = attack(theOpponent);
        } else if (chance < 0.6) {
            Random rand = new Random();
            damage = rand.nextInt(75, 175);
        }
        theOpponent.applyDamage(damage);
        return damage;
    }

}