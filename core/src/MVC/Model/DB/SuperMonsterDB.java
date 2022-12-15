package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.Physics.Vec2;

public abstract class SuperMonsterDB
{
    public abstract Monster createMonsterDB(final String monsterType, final EntityFactory theEntityFactory);
    public abstract Worm createWormDB(final Vec2 thePosition, final EntityFactory theEntityFactory);
    public abstract Monster createMonster(final int theN, final Hero theHero);

}
