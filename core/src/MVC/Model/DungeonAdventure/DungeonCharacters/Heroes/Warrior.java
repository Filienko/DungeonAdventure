package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Warrior extends Hero
{

    /**
     * Warrior constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * amount of damage it can inflict, max speed, position, and velocity.
     * @param theEntityFactory The Entity Factory that generated the Warrior.
     */
    public Warrior(final EntityFactory theEntityFactory)
    {
        super("Warrior","Warrior",  10, 1, 5, new Vec2(), new Vec2(), theEntityFactory);
    }

    /**
     * Warrior overloaded constructor that calls its parent constructor to initialize the Warrior's name, character type, hero status, hit points,
     * amount of damage it can inflict, max speed, position, and velocity.
     * @param theName The Warrior's name.
     * @param thePos The Warrior's position.
     * @param theEntityFactory The Entity Factory that generated the Warrior.
     */
    public Warrior(final String theName, final Vec2 thePos, final EntityFactory theEntityFactory)
    {
        super(theName,"Warrior",10, 1,  5, thePos, new Vec2(), theEntityFactory);
    }

    /**
     * The Warrior's specific attack behavior. They have a 40% chance of performing their special skill
     * during this attack.
     *
     * @return The amount of damage the Warrior inflicts during their attack.
     */
    @Override
    public int damage()
    {
        var damage = 0;
        var chance = Math.random();
        if (chance < 0.4)
        {
            damage = special();
        }
        else
        {
            damage = super.damage();
            setMyKnockBackPower(4);
            setMyKnockBackLength(10);
        }
        return damage;
    }

    /**
     * This is the Warrior's special skill. damage amount between 1 and 3 is randomly generated.
     * @return The amount of damage the Warrior will inflict.
     */
    public int special()
    {
        setUsingSpecial(true);
        setMyKnockBackPower(30);
        setMyKnockBackLength(10);
        Random rand = new Random();

        return rand.nextInt(getDamage(), getDamage()+3);
    }
}