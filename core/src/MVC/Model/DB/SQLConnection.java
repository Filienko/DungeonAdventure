package MVC.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    SQLiteDataSource ds;
    private double theHealChance;
    private boolean theHero;
    private int theHitPoints;
    private String theCharacterType;
    private int theMinimumRange;
    private int theMaxDamageRange;
    private int theAgility;
    private double theHitChance;
    private float myX;
    private float myY;
    private float myVelocityX;
    private float myVelocityY;
    private int theMinHeal;
    private int theMaxHeal;

    public SQLConnection(final String monsterType)
    {
        establishConnection();
        if(monsterType.contentEquals("Ogre"))
        {
            updateMonsterData(0);
        }
        else if(monsterType.contentEquals("Gremlin"))
        {
            updateMonsterData(1);
        }
        else if (monsterType.contentEquals("Elf"))
        {
            updateMonsterData(2);
        }
    }

    private static void establishConnection() {
        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:localDatabase.db");
            System.out.println( "Opened database successfully" );
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
/*
* final double theHealChance, final boolean theHero, final String theName, int theHitPoints, String theCharacterType, final int theMinimumRange,
            final int theMaxDamageRange, final double theBlockChance, final int theAgility, final double theHitChance,
            final Vec2 thePos, int theMinHeal, int theMaxHeal*/
        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS localDatabase ( " +
                "theHealChance REAL DEFAULT 0.2, " +
                "theHero INTEGER DEFAULT 0, " +
                "theName TEXT NOT NULL, " +
                "theHitPoints INTEGER DEFAULT 0, " +
                "theCharacterType TEXT NOT NULL, " +
                "theMinimumRange INTEGER DEFAULT 0, " +
                "theMaxDamageRange INTEGER DEFAULT 0, " +
                "theBlockChance REAL DEFAULT 0.2, " +
                "theAgility TEXT INTEGER DEFAULT 0, " +
                "theHitChance TEXT REAL DEFAULT 0.2, " +
                "myX REAL DEFAULT 0.0, " +
                "myY REAL DEFAULT 0.0, " +
                "theMinHeal INTEGER DEFAULT 0, " +
                "theMaxHeal INTEGER DEFAULT 1)";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created questions table successfully" );

        //next insert two rows of data
        System.out.println( "Attempting to insert argument rows into parameters table" );

        String ogreQuery = "INSERT INTO localDatabase ( theHealChance, theHero,theName,theHitPoints, theCharacterType," +
                " theMinimumRange, theMaxDamageRange,theAgility,theHitChance,myX,myY,myVelocityX,myVelocityY,theMinHeal, " +
                "theMaxHeal ) VALUES (0.1, 0,'Ogre',200,'Ogre',30,60,2,0.6,0,0,0,0,30,60)";
        String gremlinQuery = "INSERT INTO localDatabase ( theHealChance, theHero,theName,theHitPoints, theCharacterType," +
                " theMinimumRange, theMaxDamageRange,theAgility,theHitChance,myX,myY,myVelocityX,myVelocityY,theMinHeal, " +
                "theMaxHeal ) VALUES (0.4, 0,'Gremlin',70,'Gremlin',15,30,5,0.8,0,0,0,0,20,40)";
        String elfQuery = "INSERT INTO localDatabase ( theHealChance, theHero,theName,theHitPoints, theCharacterType," +
                " theMinimumRange, theMaxDamageRange,theAgility,theHitChance,myX,myY,myVelocityX,myVelocityY,theMinHeal, " +
                "theMaxHeal ) VALUES (0.3, 0,'Elf',100,'Elf',30,50,3,0.8,0,0,0,0,30,50)";


        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( ogreQuery );
            System.out.println( "1st executeUpdate() returned " + rv );
            rv = stmt.executeUpdate( gremlinQuery );
            System.out.println( "2nd executeUpdate() returned " + rv );
            rv = stmt.executeUpdate( elfQuery );
            System.out.println( "3nd executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }


    private void updateMonsterData(int n)
      {
          //now query the database table for all its contents and display the results
          String query = "SELECT * FROM localDatabase WHERE rowid = n";

          try ( Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement(); ) {

              ResultSet rs = stmt.executeQuery(query);

              //walk through each 'row' of results, grab data by column/field name
              // and print it
              while ( rs.next() ) {
                  theHealChance = Double.parseDouble(rs.getString( "theHealChance" ));
                  theHero = Boolean.parseBoolean(rs.getString( "theHero" ));
                  theHitPoints = Integer.parseInt(rs.getString( "theHitPoints" ));
                  theCharacterType = rs.getString( "theCharacterType" );
                  theMinimumRange = Integer.parseInt(rs.getString( "theMinimumRange" ));
                  theMaxDamageRange = Integer.parseInt(rs.getString( "theMaxDamageRange" ));
                  theAgility = Integer.parseInt(rs.getString( "theAgility" ));
                  theHitChance = Double.parseDouble(rs.getString( "theHitChance" ));
                  myX = Float.parseFloat(rs.getString( "myX" ));
                  myY = Float.parseFloat(( "myY" ));
                  myVelocityX = Float.parseFloat(rs.getString( "myX" ));
                  myVelocityY = Float.parseFloat(( "myY" ));
                  theMinHeal = Integer.parseInt(rs.getString( "theMinHeal" ));
                  theMaxHeal = Integer.parseInt(rs.getString( "theMaxHeal" ));
              }
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
      }

    public double getTheHealChance()
    {
        return theHealChance;
    }

    public boolean isTheHero()
    {
        return theHero;
    }

    public int getTheHitPoints()
    {
        return theHitPoints;
    }

    public String getTheCharacterType()
    {
        return theCharacterType;
    }

    public int getTheMinimumRange()
    {
        return theMinimumRange;
    }

    public int getTheMaxDamageRange()
    {
        return theMaxDamageRange;
    }

    public int getTheAgility()
    {
        return theAgility;
    }

    public double getTheHitChance()
    {
        return theHitChance;
    }

    public float getMyX()
    {
        return myX;
    }

    public float getMyY()
    {
        return myY;
    }

    public int getTheMinHeal()
    {
        return theMinHeal;
    }

    public int getTheMaxHeal()
    {
        return theMaxHeal;
    }

    public float getMyVelocityX()
    {
        return myVelocityX;
    }
    public float getMyVelocityY()
    {
        return myVelocityY;
    }
}