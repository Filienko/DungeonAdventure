package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;

public class MockMonsterDB extends SuperMonsterDB
{
    @Override
    public Monster createMonsterDB(final String theMonster, final Hero theHero)
    {
        return createMonster(1,new Warrior());
    }

    public Monster createMonster(final int theN, final Hero theHero)
        {
            boolean hero = false;
            int hp = 0;
            String charType = null;
            int speed = 0;
            int damage = 0;
            float myX = 0;
            float myY = 0;
            float velocityX = 0;
            float velocityY = 0;
            return new Monster(charType,hp,damage,speed,new Vec2(myX,myY), new Vec2(velocityX,velocityY), new Warrior());
        }
}
