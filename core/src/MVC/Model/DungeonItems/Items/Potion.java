package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public abstract class Potion extends Item
{
    private final int myStrength;

    protected Potion(final int theStrength)
    {
        super("Potion");
        myStrength = theStrength;
    }

    public abstract void increase(DungeonCharacter theCharacter);

    int getStrength()
    {
        return myStrength;
    }
}
