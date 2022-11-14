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
                    myHero = false;
                    myHitPoints = 100*n;
                    myCharacterType = "Monster";
                    myMinimumRange = 10*n;
                    myMaxDamageRange = 100*n;
                    myMaxSpeed = 12*n;
                    myX = 0*n;
                    myY = 0*n;
                    myVelocityX = 1*n;
                    myVelocityY = 1*n;
        }
}
