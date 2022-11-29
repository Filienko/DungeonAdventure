package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;

public class MockMonsterDB extends SuperMonsterDB
{
    @Override
    public Monster createMonsterDB(final String theMonster)
    {
        return createMonster(1);
    }

    public Monster createMonster(final int theN)
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
            return new Monster(charType,hp,damage,speed,new Vec2(myX,myY), new Vec2(velocityX,velocityY));
        }
}
