package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DB.MonsterDB;
import MVC.Model.DB.SuperMonsterDB;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.*;
import MVC.Model.DungeonItems.*;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.DungeonItems.Weapon.Sword;

import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.ArrayList;
import java.util.List;

public class EntityFactory
{
    //Updating, Deleting, and Collecting entities
    private SuperMonsterDB DB;

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
    
    public Monster generateMonster(final String monsterType)
    {
        DB = new MonsterDB();
        return DB.createMonsterDB(monsterType);
    }

    public ArrayList<Entity> generateGameEntities(final Dungeon theDungeon)
    {
        for (var room: theDungeon.getRooms())
        {
            var roomNumber = room.getNumber();
            var doors = new ArrayList<Door>();
            if(room.isE()){doors.add(new Door(roomNumber,room.getNumberOfMonsters(),new Vec2()));}
            if(room.isN()){doors.add(new Door(roomNumber,room.getNumberOfMonsters(),new Vec2()));}
            if(room.isS()){doors.add(new Door(roomNumber,room.getNumberOfMonsters(),new Vec2()));}
            if(room.isW()){doors.add(new Door(roomNumber,room.getNumberOfMonsters(),new Vec2()));}
            myEntitiesToAdd.addAll(doors);
            myEntitiesToAdd.add(new Wall(new Vec2(),new Vec2()));
            generateRoomEntities(room);
        }
        return myEntitiesToAdd;
    }

    public ArrayList<Entity> generateRoomEntities(final Room theRoom)
    {
        var items = theRoom.getItems();
        var monsters = theRoom.getMonsters();

        while(items.length()>0)
        {
            String item = items.substring(0, items.indexOf(",")+1);
            items.delete(0, items.indexOf(",")+1);
            addItem(item.substring(0,item.indexOf(",")));
        }

        while(monsters.length()>0)
        {
            String monster = monsters.substring(0, monsters.indexOf(",")+1);
            monsters.delete(0, monsters.indexOf(",")+1);
            monster = monster.substring(0,monster.indexOf(","));
            myEntitiesToAdd.add(generateMonster(monster));
        }
        return myEntitiesToAdd;
    }

    public void update()
    {
        // add all entities that are pending
        for (Entity e : myEntitiesToAdd)
        {
            myEntities.add(e);
            var type = myEntityMap.get(e.getType());
            if(type==null)
            {
                var list = new ArrayList<Entity>();
                list.add(e);
                myEntityMap.put(e.getType(),list);
            }
            else
            {
                myEntityMap.get(e.getType()).add(e);
            }
            myTotalEntities++;
        }

        myEntitiesToAdd.removeAll(myEntitiesToAdd);

        // removeDeadEntities();

        // update all entities
        for (var kv : myEntityMap)
        {
            for (Entity e : kv.value)
            {
                e.update();
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

    public List<Monster> generateMonsters(final int theN)
    {
        var arr = new ArrayList<Monster>();

        for (int i = 0; i < theN; i++)
        {
            arr.add(generateOgre());
            arr.add(generateGremlin());
            arr.add(generateElf());
            arr.add(generateRats());
        }
        return arr;
    }

    public void addItem(final String theItem)
    {
        switch (theItem)
        {
            case "Healing Potion" -> {
                myEntitiesToAdd.add(new HealingPotion());
                break;
            }
            case "Attack Potion" -> {
                myEntitiesToAdd.add(new AttackPotion());
                break;
            }
            case "Speed Potion" -> {
                myEntitiesToAdd.add(new SpeedPotion());
                break;
            }
            case "Pit" -> {
                myEntitiesToAdd.add(new Pit());
                break;
            }
            case "Encapsulation","Inheritance","Polymorphism","Abstraction"-> {
                myEntitiesToAdd.add(new Pillar(theItem));
                break;
            }
            case "Exit" -> {
                myEntitiesToAdd.add(new Exit());
                break;
            }
            case "Bomb" -> {
                myEntitiesToAdd.add(new Bomb());
                break;
            }
            case "Sword" -> {
                myEntitiesToAdd.add(new Sword());
                break;
            }
            case "Wall" -> {
                myEntitiesToAdd.add(new Wall(new Vec2(),new Vec2()));
                break;
            }
        }
    }

    public static Pit generatePit()
    {
        return new Pit();
    }

//    public static ArrayList<Pit> generatePit(final int theN)
//    {
//        var arr = new ArrayList<Pit>();
//
//        for (int i = 0; i < theN; i++)
//        {
//            arr.add(new Pit());
//        }
//        return arr;
//    }

    public Hero generateHero(final String type1)
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

//
//    public static List<HealingPotion> generateHealingPotions(final int theN)
//    {
//        var arr = new ArrayList<HealingPotion>();
//
//        for (int i = 0; i < theN; i++)
//        {
//            arr.add(new HealingPotion());
//        }
//
//        return arr;
//    }
//
//    public static List<SpeedPotion> generateSpeedPotions(final int theN)
//    {
//        var arr = new ArrayList<SpeedPotion>();
//
//        for (int i = 0; i < theN; i++)
//        {
//            arr.add(new SpeedPotion());
//        }
//
//        return arr;
//    }
//
//    public static List<AttackPotion> generateAttackPotions(final int theN)
//    {
//        var arr = new ArrayList<AttackPotion>();
//
//        for (int i = 0; i < theN; i++)
//        {
//            arr.add(new AttackPotion());
//        }
//
//        return arr;
//    }

    public ArrayList<Entity> getEntities() { return myEntities; }

    //added this method
    public static Sword generateSword()
    {
        return new Sword();
    }

    public ArrayList<Entity> getEntitiesToAdd()
    {
        return myEntitiesToAdd;
    }


}