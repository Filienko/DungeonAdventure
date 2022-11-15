package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DB.SQLConnection;
import MVC.Model.DB.SuperSQLConnection;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.*;
import MVC.Model.DungeonItems.*;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;

public class EntityFactory
{
    private static SuperSQLConnection DB;

    public static Monster generateMonster(String monsterType)
    {
        DB = new SQLConnection(monsterType);
        return new Monster(DB.getHitPoints(),DB.getCharacterType(),
                DB.getMinimumRange(), DB.getMaxDamageRange(), DB.getMaxSpeed(),
                new Vec2(DB.getX(),DB.getY()),new Vec2(DB.getVelocityX(),DB.getVelocityY()));
    }

    public static Monster generateOgre()
    {
        return generateMonster("Ogre");
    }

    public static Monster generateGremlin()
    {
        return generateMonster("Gremlin");
    }

    public static Monster generateElf()
    {
        return generateMonster("Elf");
    }

    public static List<Monster> generateMonsters(int n1)
    {
        var arr = new ArrayList<Monster>();

        for (int i = 0; i < n1; i++)
        {
            arr.add(generateOgre());
            arr.add(generateGremlin());
            arr.add(generateElf());
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

    public static Hero generateHero(String type1)
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

    public static Hero generateWarrior()
    {
        return new Warrior();
    }

    public static Hero generateThief()
    {
        return new Thief();
    }

    public static Hero generatePriestess()
    {
        return new Priestess();
    }

    public static ArrayList<Room> generateRooms(int n1)
    {
        var arr = new ArrayList<Room>();
        arr.add(new Room(true));
        arr.add(new Room(false));
        for (int i = 0; i < n1-2; i++)
        {
            arr.add(new Room());
        }
        return arr;
    }

    public static ArrayList<Door> generateDoors(int n1)
    {
        var arr = new ArrayList<Door>();
        for (int i = 0; i < n1; i++)
        {
            arr.add(new Door());
        }
        return arr;
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

    public static Dungeon generateDungeon(Room[][] size1)
    {
        return new Dungeon(size1);
    }

    public static Dungeon generateMockDungeon(int[][] size1)
    {
        return new Dungeon();
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
}