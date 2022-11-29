package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DB.SQLConnection;
import MVC.Model.DB.SuperSQLConnection;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.*;
import MVC.Model.DungeonItems.*;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.DungeonItems.Weapon.Sword;

import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityFactory
{
    //Updating, Deleting, and Collecting entities
    private SuperSQLConnection DB;
    private ArrayList<Entity> myEntities;
    private ArrayList<Entity> myEntitiesToAdd;
    private ObjectMap<String, ArrayList<Entity>> myEntityMap;
    private long myTotalEntities;

    public EntityFactory()
    {
        myEntities = new ArrayList<>();
        myEntitiesToAdd = new ArrayList<>();
        myEntityMap = new ObjectMap<>();
        myTotalEntities = 0;
    }
    
    public Monster generateMonster(String monsterType)
    {
        DB = new SQLConnection(monsterType);
        return new Monster(DB.getCharacterType(), DB.getHitPoints(), DB.getDamage(), DB.getMaxSpeed(),
                new Vec2(DB.getX(),DB.getY()),new Vec2(DB.getVelocityX(),DB.getVelocityY()));
    }


    public void update()
    {
        // add all entities that are pending
        for (Entity e : myEntitiesToAdd)
        {
            myEntities.add(e);
            myEntityMap.get(e.getType()).add(e);
            myTotalEntities++;
        }

        myEntitiesToAdd.removeAll(myEntitiesToAdd);

        // removeDeadEntities();

        // update all entities
        for (var kv : myEntityMap)
        {
            for (Entity e : kv.value)
            {
                if (kv.key.equals("Warrior"))
                {
                    Warrior warrior = (Warrior) e;
                    warrior.update();
                }
            }
        }

    }

    public Monster generateOgre()
    {
        return generateMonster("Ogre");
    }

    public Monster generateGremlin()
    {
        return generateMonster("Gremlin");
    }

    public Monster generateElf()
    {
        return generateMonster("Elf");
    }

    public Monster generateRats()
    {
        return generateMonster("Rats");
    }


    public List<Monster> generateMonsters(int n1)
    {

        var arr = new ArrayList<Monster>();

        for (int i = 0; i < n1; i++)
        {
            arr.add(generateOgre());
            arr.add(generateGremlin());
            arr.add(generateElf());
            arr.add(generateRats());
        }
        return arr;
    }

    public static Pit generatePit()
    {
        return new Pit();
    }

    public static Pit generatePit(Vec2 theVec)
    {
        return new Pit(theVec);
    }

    public static ArrayList<Pit> generatePit(int n1)
    {
        var arr = new ArrayList<Pit>();

        for (int i = 0; i < n1; i++)
        {
            arr.add(new Pit());
        }
        return arr;
    }

    public Hero generateHero(String type1)
    {
        if (type1.contentEquals("Warrior"))
        {
            return generateWarrior();
        }
        else if (type1.contentEquals("Thief"))
        {
            return generateThief();
        }
        else if (type1.contentEquals("Priestess"))
        {
            return generatePriestess();
        }
        return null;
    }

    public Hero generateWarrior()
    {
        Warrior warrior = new Warrior();
        myEntitiesToAdd.add(warrior);
        return warrior;
    }

    public Hero generateThief()
    {
        Thief thief = new Thief();
        myEntitiesToAdd.add(thief);
        return thief;
    }

    public Hero generatePriestess()
    {
        Priestess priestess = new Priestess();
        myEntitiesToAdd.add(priestess);
        return priestess;
    }

    public static ArrayList<Pillar> generatePillars()
    {
        var arr = new ArrayList<Pillar>();

        arr.add(new Pillar("Encapsulation"));
        arr.add(new Pillar("Inheritance"));
        arr.add(new Pillar("Abstraction"));
        arr.add(new Pillar("Polymorphism"));

        return arr;
    }

    public static List<HealingPotion> generateHealingPotions(int n1)
    {
        var arr = new ArrayList<HealingPotion>();

        for (int i = 0; i < n1; i++)
        {
            arr.add(new HealingPotion());
        }

        return arr;
    }

    public static List<SpeedPotion> generateSpeedPotions(int n1)
    {
        var arr = new ArrayList<SpeedPotion>();

        for (int i = 0; i < n1; i++)
        {
            arr.add(new SpeedPotion());
        }

        return arr;
    }

    public static List<AttackPotion> generateAttackPotions(int n1)
    {
        var arr = new ArrayList<AttackPotion>();

        for (int i = 0; i < n1; i++)
        {
            arr.add(new AttackPotion());
        }

        return arr;
    }

    public ArrayList<Entity> getEntities() { return myEntities; }

    //added this method
    public static Sword generateSword() 
    {
        return new Sword();
    }

}