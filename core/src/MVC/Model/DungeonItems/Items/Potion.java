package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;


public abstract class Potion extends Item
{
    private final int myStrength;

    protected Potion()
    {
        super("Potion", new Vec2());
        myStrength = 15;
    }
    protected Potion(final int theStrength, Vec2 thePosition)
    {
        super("Potion", thePosition);
        myStrength = theStrength;
    }

    public abstract void increase(DungeonCharacter theCharacter);


    //made public for testing!! change back to default
    public int getStrength()
    {
        return myStrength;
    }
}

