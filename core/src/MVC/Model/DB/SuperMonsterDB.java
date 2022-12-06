package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;

public abstract class SuperMonsterDB
{
    public abstract Monster createMonsterDB(final String monsterType, final Hero theHero, final EntityFactory theEntityFactory);

    public abstract Monster createMonster(final int theN, final Hero theHero);
}
