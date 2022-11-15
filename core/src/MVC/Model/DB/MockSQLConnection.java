package MVC.Model.DB;

public class MockSQLConnection extends SuperSQLConnection
{
        public MockSQLConnection(final String monsterType)
        {
            if(monsterType.contentEquals("Ogre"))
            {
                updateMonsterData(1);
            }
            else if(monsterType.contentEquals("Gremlin"))
            {
                updateMonsterData(2);
            }
            else if (monsterType.contentEquals("Elf"))
            {
                updateMonsterData(3);
            }
        }

        public void updateMonsterData(int n)
        {
            super.setHero(false);
            super.setHitPoints(100);
            super.setCharacterType("Monster");
            super.setMaxSpeed(10);
            super.setMinimumRange(0);
            super.setMaxDamageRange(11);
            super.setX(0);
            super.setY(0);
            super.setVelocityX(1);
            super.setVelocityY(1);
        }
}
