package MVC.Model.DB;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonAdventure.DungeonCharacters.Worm;
import MVC.Model.Physics.Vec2;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class MonsterDB extends SuperMonsterDB
{
    private SQLiteDataSource myDS = establishConnection();
    private EntityFactory myEntityFactory;
    private Hero myHero;

    /**
     * @param monsterType the type of the monster desired by the factory
     * @param theEntityFactory that will keep track of the Monster and associated assets and updating logic
     * @return Monster of the specified type and characteristics specified at the Database
     */
    public Monster createMonsterDB(final String monsterType, final EntityFactory theEntityFactory)
    {
        myEntityFactory = theEntityFactory;
        myHero = theEntityFactory.getHero();

        if(monsterType.contentEquals("ogre"))
        {
            return createMonster(1,myHero);
        }
        else if(monsterType.contentEquals("gremlin"))
        {
            return createMonster(2,myHero);
        }
        else if (monsterType.contentEquals("knight"))
        {
            return createMonster(3,myHero);
        }
        else if (monsterType.contains("rat"))
        {
            return createMonster(4,myHero);
        }
        return null;
    }


    /**
     * @return Database updated with the intended values specified by the developer team
     */
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
                "myDimensionX REAL DEFAULT 1.0, " +
                "myDimensionY REAL DEFAULT 1.0," +
                "UNIQUE(myHero,myCharacterType,myHitPoints,myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY," +
                "myDimensionX,myDimensionY))";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();)
        {
            int rv = stmt.executeUpdate(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        //next insert four rows of data
        String ogreQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY,myDimensionX,myDimensionY" +
                ") VALUES (0,5,'ogre',2,2,0,0,0,0,96,96)";
        String gremlinQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY,myDimensionX,myDimensionY" +
                ") VALUES (0,3,'gremlin',1,3,0,0,0,0,64,64)";
        String knightQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY,myDimensionX,myDimensionY" +
                ") VALUES (0,3,'knight',1,5,0,0,0,0,63,63)";

        String ratsQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY,myDimensionX,myDimensionY" +
                ") VALUES (0,1,'rat',1,"+(new Random().nextInt(4,7))+",0,0,0,0,32,32)";

        String wormQuery = "INSERT OR IGNORE INTO enemiesDatabase (myHero,myHitPoints, myCharacterType," +
                " myDamage,myMaxSpeed,myX,myY,myVelocityX,myVelocityY,myDimensionX,myDimensionY" +
                ") VALUES (0,10,'Worm',1,4,0,0,0,0,96,96)";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();)
        {
            int rv = stmt.executeUpdate(ogreQuery);

            rv = stmt.executeUpdate(gremlinQuery);

            rv = stmt.executeUpdate(knightQuery);

            rv = stmt.executeUpdate(ratsQuery);

            rv = stmt.executeUpdate(wormQuery);

        } catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        return ds;
    }


    /**
     * @param thePosition the position of the Worm [center of the exit room]
     * @param theEntityFactory that will keep track of boss Worm and associated assets and updating logic
     * @return Worm with the characteristics specified at the Database
     */
    @Override
    public Worm createWormDB(final Vec2 thePosition, final EntityFactory theEntityFactory)
    {
        //now query my database table for all its contents and display my results
        String query = "SELECT * FROM enemiesDatabase WHERE rowid =" + 5 + "";

        int hp = 0;
        String charType = null;
        int speed = 0;
        int damage = 0;
        float myX = 0;
        float myY = 0;
        float velocityX = 0;
        float velocityY = 0;
        float dimensionX = 0;
        float dimensionY = 0;

        try ( Connection conn = myDS.getConnection();
              Statement stmt = conn.createStatement(); )
        {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            hp = (Integer.parseInt(rs.getString( "myHitPoints" )));
            charType = (rs.getString( "myCharacterType" ));
            speed = ( Integer.parseInt(rs.getString( "myMaxSpeed" )));
            damage = (Integer.parseInt(rs.getString( "myDamage" )));
            myX = (Float.parseFloat(rs.getString( "myX" )));
            myY = (Float.parseFloat(rs.getString( "myY" )));
            velocityX = (Float.parseFloat(rs.getString( "myVelocityX" )));
            velocityY = (Float.parseFloat(rs.getString( "myVelocityY" )));
            dimensionX = (Float.parseFloat(rs.getString( "myDimensionX" )));
            dimensionY = (Float.parseFloat(rs.getString( "myDimensionY" )));
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            System.exit( 0 );
        }

        return new Worm(charType,hp,damage,speed,new Vec2(dimensionX,dimensionY), thePosition,
                new Vec2(myX,myY), theEntityFactory);
    }

    /**
     * @param theN the position of the requested monster type in the database
     * @param theHero Hero of the game, interacting with the Monsters
     * @return Monster with the characteristics specified at the Database
     */
    @Override
    public Monster createMonster(final int theN, final Hero theHero)
    {
        //now query my database table for all its contents and display my results
        String query = "SELECT * FROM enemiesDatabase WHERE rowid =" + theN + "";

        int hp = 0;
        String charType = null;
        int speed = 0;
        int damage = 0;
        float myX = 0;
        float myY = 0;
        float velocityX = 0;
        float velocityY = 0;
        float dimensionX = 0;
        float dimensionY = 0;

        try ( Connection conn = myDS.getConnection();
              Statement stmt = conn.createStatement(); )
        {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            hp = (Integer.parseInt(rs.getString( "myHitPoints" )));
            charType = (rs.getString( "myCharacterType" ));
            speed = ( Integer.parseInt(rs.getString( "myMaxSpeed" )));
            damage = (Integer.parseInt(rs.getString( "myDamage" )));
            myX = (Float.parseFloat(rs.getString( "myX" )));
            myY = (Float.parseFloat(rs.getString( "myY" )));
            velocityX = (Float.parseFloat(rs.getString( "myVelocityX" )));
            velocityY = (Float.parseFloat(rs.getString( "myVelocityY" )));
            dimensionX = (Float.parseFloat(rs.getString( "myDimensionX" )));
            dimensionY = (Float.parseFloat(rs.getString( "myDimensionY" )));

        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            System.exit( 0 );
        }

        return new Monster(charType,hp,damage,speed,new Vec2(myX,myY), new Vec2(velocityX,velocityY),
                new Vec2(dimensionX,dimensionY), myEntityFactory);
    }
}