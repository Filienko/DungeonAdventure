package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;

public abstract class SuperMonsterDB
{
    public abstract Monster createMonsterDB(final String theMonster);

    public abstract Monster createMonster(final int theN);
}
