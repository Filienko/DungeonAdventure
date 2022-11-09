package MVC.Model.DungeonAdventure.DungeonCharacters.Heroes;

import MVC.Model.DungeonAdventure.DungeonCharacters.Healable;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Priestess extends Hero implements Healable
{
    private String myName;

    public Priestess()
    {
        super("Priestess", "Priestess", true, 75, 25, 45, 0.3, 5, 0.7, new Vec2());
    }

    public Priestess(final String theName, final Vec2 thePos)
    {
        super(theName,"Priestess", true,75, 25, 45, 0.3, 5, 0.7, thePos);
        this.myName = theName;
    }

    public int healHero(Hero theHero)
    {
        return healCharacter(theHero);
    }

}