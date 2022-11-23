package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DB.SQLConnection;
import MVC.Model.DB.SuperSQLConnection;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.*;
import MVC.Model.DungeonItems.*;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.DungeonUtils.Graph;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        // Number of rooms + boundary rooms
        int allVertices = (int) Math.pow((Math.sqrt(n1*n1)+2),2);

        //Alternative approach to generate maze with the position of a hero at 0 and end at the last square
//        arr.add(new Room(true, 1, new Vec2()));
//        arr.add(new Room(false, (n1+2)*n1-1, new Vec2(n1-1, n1-1)));

        for (int i = 1; i < ((n1+2)*n1)-1; i++)
        {
            //Skip buffer rooms
            if(i % Math.sqrt(allVertices) == Math.sqrt(allVertices)-1 || i % Math.sqrt(allVertices) == 0)
            {
                continue;
            }

            //Account for the buffer offset
            int row = i / (n1 + 2);
            int col = (i % (n1 + 2)) - 1;
            arr.add(new Room(i, new Vec2(row,col)));
        }

        var pillars = generatePillars();
        for (int i = 0; i < 4; i++)
        {
            arr.get(new Random().nextInt(1,arr.size())).addItem(pillars.get(i));
        }

        arr.get(0).setEntranceStatus(true);
        arr.get(new Random().nextInt(1,arr.size())).setExitStatus(true).addItem(new Exit());

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

    public static Dungeon generateDungeon()
    {
        return new Dungeon();
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