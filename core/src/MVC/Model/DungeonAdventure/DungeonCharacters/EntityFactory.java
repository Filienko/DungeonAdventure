package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DungeonAdventure.DungeonCharacters.*;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.*;
import MVC.Model.DungeonItems.*;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.Physics.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

public class EntityFactory
{
    public Monster generateOgre()
    {
        return new Monster(0.1, false,"Ogre",200,"Ogre",30, 60, 0.05, 2, 0.6, new Vec2(), 30, 60);
    }

    public Monster generateGremlin()
    {
        return new Monster(0.4, false,"Gremlin",70,"Gremlin",15, 30, 0.05, 5, 0.8, new Vec2(),20,40);
    }

    public Monster generateElf()
    {
        return new Monster(0.3, false,"Elf",100,"Elf",30, 50, 0.05, 3, 0.8, new Vec2(), 30, 50);
    }

    public List<Monster> generateMonsters(int n1)
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

    public Pit generatePit()
    {
        return new Pit();
    }

    public Pit generatePit(Vec2 theVec)
    {
        return new Pit(theVec);
    }

    public ArrayList<Pit> generatePit(int n1)
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
        return new Warrior();
    }

    public Hero generateThief()
    {
        return new Thief();
    }

    public Hero generatePriestess()
    {
        return new Priestess();
    }

    public ArrayList<Room> generateRooms(int n1)
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

    public ArrayList<Door> generateDoors(int n1)
    {
        var arr = new ArrayList<Door>();
        for (int i = 0; i < n1; i++)
        {
            arr.add(new Door());
        }
        return arr;
    }

    public ArrayList<Pillar> generatePillars()
    {
        var arr = new ArrayList<Pillar>();

        arr.add(new Pillar("Encapsulation"));
        arr.add(new Pillar("Inheritance"));
        arr.add(new Pillar("Abstraction"));
        arr.add(new Pillar("Polymorphism"));

        return arr;
    }

    public Dungeon generateDungeon(int[][] size1)
    {
        return new Dungeon(size1);
    }

    public Dungeon generateMockDungeon(int[][] size1)
    {
        return new Dungeon();
    }

    public List<HealingPotion> generateHealingPotions(int n1)
    {
        var arr = new ArrayList<HealingPotion>();

        for (int i = 0; i < n1; i++)
        {
            arr.add(new HealingPotion());
        }

        return arr;
    }

}