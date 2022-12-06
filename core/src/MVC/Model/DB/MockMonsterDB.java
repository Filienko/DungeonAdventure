package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;

public class MockMonsterDB extends SuperMonsterDB
{
    @Override
    public Monster createMonsterDB(final String monsterType, final Hero theHero, final EntityFactory theEntityFactory)
    {
        return createMonster(1,new Warrior(new EntityFactory()));
    }

    public Monster createMonster(final int theN, final Hero theHero)
        {
            int hp = 0;
            String charType = null;
            int speed = 0;
            int damage = 0;
            float myX = 0;
            float myY = 0;
            float velocityX = 0;
            float velocityY = 0;
            var ef = new EntityFactory();
            return new Monster(charType,hp,damage,speed,new Vec2(myX,myY), new Vec2(velocityX,velocityY), ef.generateHero("Thief"),ef);
            //^^requires an EntityFactory param
            }
}
