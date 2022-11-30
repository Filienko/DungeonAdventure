package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.Physics.Vec2;

import java.util.Random;

public abstract class Potion extends Item
{
    private final int myStrength;

    protected Potion(String theType, final int theStrength)
    {
        super(theType);
        myStrength = theStrength;
    }

    public abstract void increase(DungeonCharacter theCharacter);

    int getStrength()
    {
        return myStrength;
    }
}
