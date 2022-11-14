package MVC.Model.DB;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection extends SuperSQLConnection
{
    public SQLiteDataSource myDS = establishConnection();

    public static void main(String[] args)
    {
        SQLConnection db = new SQLConnection("Ogre");

        System.out.println(db.getCharacterType());
        db = new SQLConnection("Gremlin");

        System.out.println(db.getCharacterType());
        db = new SQLConnection("Elf");

        System.out.println(db.getCharacterType());
    }

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
    }

    static SQLiteDataSource establishConnection()
    {
        //establish connection (creates db file if it does not exist :-)
        SQLiteDataSource ds = null;
        try
        {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:enemiesDatabase.db");
            System.out.println("Opened database successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS enemiesDatabase ( " +
                "myHero INTEGER DEFAULT 0, " +
                "myName TEXT NOT NULL, " +
                "myHitPoints INTEGER DEFAULT 0, " +
                "myCharacterType TEXT NOT NULL, " +
                "myMinimumRange INTEGER DEFAULT 0, " +
                "myMaxDamageRange INTEGER DEFAULT 0, " +
                "myMaxSpeed INTEGER DEFAULT 0, " +
                "myX REAL DEFAULT 0.0, " +
                "myY REAL DEFAULT 0.0, " +
                "myVelocityX REAL DEFAULT 0.0, " +
                "myVelocityY REAL DEFAULT 0.0)";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();)
        {
            int rv = stmt.executeUpdate(query);
            System.out.println("executeUpdate() returned " + rv);
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Created questions table successfully");

        //next insert two rows of data
        System.out.println("Attempting to insert argument rows into parameters table");

        String ogreQuery = "INSERT INTO enemiesDatabase (myHero,myName,myHitPoints, myCharacterType," +
                " myMinimumRange,myMaxDamageRange,myMaxSpeed,myX,myY,myVelocityX,myVelocityY" +
                ") VALUES (0,'Ogre',200,'Ogre',30,60,2,0,0,0,0)";
        String gremlinQuery = "INSERT INTO enemiesDatabase (myHero,myName,myHitPoints, myCharacterType," +
                " myMinimumRange, myMaxDamageRange,myMaxSpeed,myX,myY,myVelocityX,myVelocityY" +
                ") VALUES (0,'Gremlin',70,'Gremlin',15,30,5,0,0,0,0)";
        String elfQuery = "INSERT INTO enemiesDatabase ( myHero,myName,myHitPoints, myCharacterType," +
                " myMinimumRange, myMaxDamageRange,myMaxSpeed,myX,myY,myVelocityX,myVelocityY" +
                ") VALUES (0,'Elf',100,'Elf',30,50,3,0,0,0,0)";


        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();)
        {
            int rv = stmt.executeUpdate(ogreQuery);
            System.out.println("1st executeUpdate() returned " + rv);
            rv = stmt.executeUpdate(gremlinQuery);
            System.out.println("2nd executeUpdate() returned " + rv);
            rv = stmt.executeUpdate(elfQuery);
            System.out.println("3nd executeUpdate() returned " + rv);
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
                myHero = Boolean.parseBoolean(rs.getString( "myHero" ));
                myHitPoints = Integer.parseInt(rs.getString( "myHitPoints" ));
                myCharacterType = rs.getString( "myCharacterType" );
                myMinimumRange = Integer.parseInt(rs.getString( "myMinimumRange" ));
                myMaxDamageRange = Integer.parseInt(rs.getString( "myMaxDamageRange" ));
                myMaxSpeed = Integer.parseInt(rs.getString( "myMaxSpeed" ));
                myX = Float.parseFloat(rs.getString( "myX" ));
                myY = Float.parseFloat(rs.getString("myY" ));
                myVelocityX = Float.parseFloat(rs.getString( "myVelocityX" ));
                myVelocityY = Float.parseFloat(rs.getString( "myVelocityY" ));
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