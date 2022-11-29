package MVC.Model.DB;

public class MockSQLConnection extends SuperSQLConnection
{
        public MockSQLConnection(final String monsterType)
        {
            updateMonsterData(1);
        }

        public void updateMonsterData(int n)
        {
            super.setHero(false);
            super.setCharacterType("Monster");
            super.setHitPoints(n);
            super.setMaxSpeed(n);
            super.setDamage(n);
            super.setX(0);
            super.setY(0);
            super.setVelocityX(1);
            super.setVelocityY(1);
        }
}
