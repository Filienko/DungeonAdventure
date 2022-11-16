package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

public abstract class Potion extends Item
{
    private final int myStrength;

    protected Potion(final int theStrength, Vec2 thePosition)
    {
        super("Potion", thePosition);
        myStrength = theStrength;
    }

    public abstract void increase(DungeonCharacter theCharacter);

    int getStrength()
    {
        return myStrength;
    }
}
