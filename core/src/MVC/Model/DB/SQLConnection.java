package MVC.Model.DB;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection extends SuperSQLConnection
{
    public SQLiteDataSource myDS = establishConnection();

    public SQLConnection(final String monsterType)
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
        else if (monsterType.contentEquals("Rats"))
        {
            updateMonsterData(4);
        }
    }

    public static SQLiteDataSource establishConnection()
    {
        //establish connection (creates db file if it does not exist :-)
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
    public void updateMonsterData(int n)
    {
        //now query my database table for all its contents and display my results
        String query = "SELECT * FROM enemiesDatabase WHERE rowid =" + n + "";

        try ( Connection conn = myDS.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                super.setHero(Boolean.parseBoolean(rs.getString( "myHero" )));
                super.setHitPoints(Integer.parseInt(rs.getString( "myHitPoints" )));
                super.setCharacterType(rs.getString( "myCharacterType" ));
                super.setMaxSpeed( Integer.parseInt(rs.getString( "myMaxSpeed" )));
                super.setDamage(Integer.parseInt(rs.getString( "myDamage" )));
                super.setX(Float.parseFloat(rs.getString( "myX" )));
                super.setY(Float.parseFloat(rs.getString( "myY" )));
                super.setVelocityX(Float.parseFloat(rs.getString( "myVelocityX" )));
                super.setVelocityY(Float.parseFloat(rs.getString( "myVelocityY" )));
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public SQLiteDataSource getDS()
    {
        return myDS;
    }

    public void setDS(final SQLiteDataSource theDS)
    {
        myDS = theDS;
    }

}