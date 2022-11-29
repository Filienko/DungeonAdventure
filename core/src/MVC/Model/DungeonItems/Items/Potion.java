package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public abstract class Potion extends Item
{
    private final int myStrength;

    protected Potion()
    {
        super("Potion", new Vec2((new Random()).nextInt(0, 21),
                (new Random()).nextInt(0, 12)));
        myStrength = 15;
    }
    protected Potion(String theType, final int theStrength)
    {
        super(theType, new Vec2((new Random()).nextInt(0, 21),
                (new Random()).nextInt(0, 13)));
        myStrength = theStrength;
    }

    public abstract void increase(DungeonCharacter theCharacter);

    int getStrength()
    {
        return myStrength;
    }
}
