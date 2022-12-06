package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Vec2;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MonsterDB extends SuperMonsterDB
{
    private SQLiteDataSource myDS = establishConnection();
    private EntityFactory myEntityFactory;
    private Hero myHero;

    public Monster createMonsterDB(final String monsterType, final Hero theHero, final EntityFactory theEntityFactory)
    {
        myEntityFactory = theEntityFactory;
        myHero = theHero;

        if(monsterType.contentEquals("Ogre"))
        {
            return createMonster(1,theHero);
        }
        else if(monsterType.contentEquals("Gremlin"))
        {
            return createMonster(2,theHero);
        }
        else if (monsterType.contentEquals("Elf"))
        {
            return createMonster(3,theHero);
        }
        else if (monsterType.contains("Rats"))
        {
            return createMonster(4,theHero);
        }
        return null;
    }

    public static SQLiteDataSource establishConnection()
    {
        SQLiteDataSource ds = null;
        try
        {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:enemiesDatabase.db");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS enemiesDatabase ( " +
                "myHero INTEGER DEFAULT 0, " +
                "myHitPoints INTEGER DEFAULT 0, " +
                "myCharacterType TEXT NOT NULL, " +
                "myDamage INTEGER DEFAULT 0, " +
                "myMaxSpeed INTEGER DEFAULT 0, " +
                "myX REAL DEFAULT 0.0, " +
                "myY REAL DEFAULT 0.0, " +
                "myVelocityX REAL DEFAULT 0.0, " +
                "myVelocityY REAL DEFAULT 0.0," +
                "UNIQUE(myHero,myCharacterType,myHitPoints,myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY))";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();)
        {
            int rv = stmt.executeUpdate(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        //next insert three rows of data
        String ogreQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY" +
                ") VALUES (0,5,'Ogre',2,2,0,0,0,0)";
        String gremlinQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY" +
                ") VALUES (0,3,'Gremlin',1,3,0,0,0,0)";
        String elfQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY" +
                ") VALUES (0,3,'Elf',1,5,0,0,0,0)";
        String ratsQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY" +
                ") VALUES (0,1,'Swarm of Rats',1,7,0,0,0,0)";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();)
        {
            int rv = stmt.executeUpdate(ogreQuery);

            rv = stmt.executeUpdate(gremlinQuery);

            rv = stmt.executeUpdate(elfQuery);

            rv = stmt.executeUpdate(ratsQuery);

        } catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        return ds;
    }

    @Override
    public Monster createMonster(final int theN, final Hero theHero)
    {
        //now query my database table for all its contents and display my results
        String query = "SELECT * FROM enemiesDatabase WHERE rowid =" + theN + "";

        boolean hero = false;
        int hp = 0;
        String charType = null;
        int speed = 0;
        int damage = 0;
        float myX = 0;
        float myY = 0;
        float velocityX = 0;
        float velocityY = 0;

        try ( Connection conn = myDS.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            //and print it
            hero = (Boolean.parseBoolean(rs.getString( "myHero" )));
            hp = (Integer.parseInt(rs.getString( "myHitPoints" )));
            charType = (rs.getString( "myCharacterType" ));
            speed = ( Integer.parseInt(rs.getString( "myMaxSpeed" )));
            damage = (Integer.parseInt(rs.getString( "myDamage" )));
            myX = (Float.parseFloat(rs.getString( "myX" )));
            myY = (Float.parseFloat(rs.getString( "myY" )));
            velocityX = (Float.parseFloat(rs.getString( "myVelocityX" )));
            velocityY = (Float.parseFloat(rs.getString( "myVelocityY" )));
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        return new Monster(charType,hp,damage,speed,new Vec2(myX,myY), new Vec2(velocityX,velocityY), myHero, myEntityFactory);
        }
}