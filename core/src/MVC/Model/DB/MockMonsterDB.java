package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.Physics.Vec2;

public class MockMonsterDB extends SuperMonsterDB
{

    /**
     * @param monsterType the type of the monster desired by the factory
     * @param theEntityFactory that will keep track of the Monster and associated assets and updating logic
     * @return Mock Monster of the specified type and characteristics added manually
     */
    @Override
    public Monster createMonsterDB(final String monsterType, final EntityFactory theEntityFactory)
    {
        return createMonster(1,new Warrior(new EntityFactory()));
    }

    /**
     * @param thePosition the position of the Worm [center of the exit room]
     * @param theEntityFactory that will keep track of boss Worm and associated assets and updating logic
     * @return fake Worm, currently returns null, because otherwise would require populating assets,
     *  which is not possible without a major resource load into current Game Engine and Renderer
     */
    @Override
    public Worm createWormDB(final Vec2 thePosition, final EntityFactory theEntityFactory)
    {
        return null;
    }
    /**
     * @param theN the position of the requested monster type in the database
     * @param theHero Hero of the game, interacting with the Monsters
     * @return MockMonster with the characteristics specified manually
     */

    public Monster createMonster(final int theN, final Hero theHero)
    {
        int hp = 0;
        int speed = 0;
        int damage = 0;
        float myX = 0;
        float myY = 0;
        float velocityX = 0;
        float velocityY = 0;
        var ef = new EntityFactory();
        return new Monster("Mock",hp,damage,speed,new Vec2(myX,myY), new Vec2(velocityX,velocityY), new Vec2(),ef);
    }
}
