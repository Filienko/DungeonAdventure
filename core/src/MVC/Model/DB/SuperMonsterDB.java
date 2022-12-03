package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;

public abstract class SuperMonsterDB
{
    public abstract Monster createMonsterDB(final String theMonster, final Hero theHero);

    public abstract Monster createMonster(final int theN, final Hero theHero);
}
