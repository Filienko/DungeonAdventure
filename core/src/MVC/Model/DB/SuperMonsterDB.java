package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.Physics.Vec2;

public abstract class SuperMonsterDB
{
    /**
     * @param monsterType the type of the monster desired by the factory
     * @param theEntityFactory that will keep track of the Monster and associated assets and updating logic
     * @return Monster of the specified type and characteristics specified at the Database
     */
    public abstract Monster createMonsterDB(final String monsterType, final EntityFactory theEntityFactory);

    /**
     * @param thePosition the position of the Worm [center of the exit room]
     * @param theEntityFactory that will keep track of boss Worm and associated assets and updating logic
     * @return Worm with the characteristics specified at the Database
     */
    public abstract Worm createWormDB(final Vec2 thePosition, final EntityFactory theEntityFactory);

    /**
     * @param theN the position of the requested monster type in the database
     * @param theHero Hero of the game, interacting with the Monsters
     * @return Monster with the characteristics specified at the Database
     */
    public abstract Monster createMonster(final int theN, final Hero theHero);

}
