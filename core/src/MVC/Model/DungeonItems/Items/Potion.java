package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;

public abstract class Potion extends Item
{
    protected final int myStrength;

    protected Potion(final int theStrength)
    {
        myStrength = theStrength;
    }

    public abstract void increase(DungeonCharacter theCharacter);
}
