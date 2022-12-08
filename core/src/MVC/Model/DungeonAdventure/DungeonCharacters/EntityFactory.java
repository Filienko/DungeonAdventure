package MVC.Model.DungeonAdventure.DungeonCharacters;

import MVC.Model.DB.MonsterDB;
import MVC.Model.DB.SuperMonsterDB;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.*;
import MVC.Model.DungeonItems.*;
import MVC.Model.DungeonItems.Items.*;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import MVC.View.Assets;
import com.badlogic.gdx.utils.ObjectMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class EntityFactory
{
    private ArrayList<Entity> myEntities;
    private ArrayList<Entity> myEntitiesToAdd;
    private ObjectMap<String, ArrayList<Entity>> myEntityMap;
    private long myTotalEntities;
    private Assets myAssets;
    private static Hero myHero;

    public EntityFactory()
    {
        myEntities = new ArrayList<>();
        myEntitiesToAdd = new ArrayList<>();
        myEntityMap = new ObjectMap<>();
        myTotalEntities = 0;
    }
    public EntityFactory(Assets assets, final String theHero)
    {
        this();
        myAssets = assets;
        myHero = generateHero(theHero);
    }
    
    public Monster generateMonster(final String monsterType)
    {
        //Updating, Deleting, and Collecting entities
        SuperMonsterDB DB = new MonsterDB();
        var monster = DB.createMonsterDB(monsterType, myHero,this);
        myEntities.add(monster);
        return DB.createMonsterDB(monsterType, myHero,this);
    }

    public ArrayList<Entity> generateGameEntities(final Dungeon theDungeon)
    {
        for (var room: theDungeon.getRooms())
        {
            var roomNumber = room.getNumber();
            generateRoomEntities(room);
        }
        return myEntitiesToAdd;
    }

    public ArrayList<Entity> generateRoomEntities(final Room theRoom)
    {
        var items = theRoom.getItems();
        var monsters = theRoom.getMonsters();
        Vec2 location = theRoom.getLocation();

        Vec2 pixelPos;
        Wall wall;
        // Generate walls along the north and south
        for (int i = 0; i <= 18; i++)
        {
            if (!(i == 9 && theRoom.isN()))
            {
                pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), i, 10);
                wall = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(64, 64));
                if (i == 0 || i == 18)
                {
                    wall.setMyAnimation(myAssets.getAnimation("corner"));
                }
                else
                {
                    wall.setMyAnimation(myAssets.getAnimation("wall"));
                }
                if (i == 18)
                {
                    wall.setRotation(270);
                }
                myEntitiesToAdd.add(wall);
            }

            if (!(i == 9 && theRoom.isS()))
            {
                pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), i, 0);
                wall = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(64, 64));
                if (i == 0 || i == 18)
                {
                    wall.setMyAnimation(myAssets.getAnimation("corner"));
                }
                else
                {
                    wall.setMyAnimation(myAssets.getAnimation("wall"));
                }
                if (i == 0)
                {
                    wall.setRotation(90);
                }
                else
                {
                    wall.setRotation(180);
                }
                myEntitiesToAdd.add(wall);
            }
        }
        // Generate walls along the east and west
        for (int i = 1; i < 10; i++)
        {
            if (!(i == 5 && theRoom.isW()))
            {
                pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 0, i);
                wall = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(64, 64));
                wall.setMyAnimation(myAssets.getAnimation("wall"));
                wall.setRotation(90);
                myEntitiesToAdd.add(wall);
            }

            if (!(i == 5 && theRoom.isE()))
            {
                pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 18, i);
                wall = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(64, 64));
                wall.setMyAnimation(myAssets.getAnimation("wall"));
                wall.setRotation(270);
                myEntitiesToAdd.add(wall);
            }
        }


        if (theRoom.isN())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 9, 10);
            Door door = new Door(0, pixelPos, this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            myEntitiesToAdd.add(door);
        }
        if (theRoom.isS())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 9, 0);
            Door door = new Door(0, pixelPos,this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            door.setRotation(180);
            myEntitiesToAdd.add(door);
        }
        if (theRoom.isW())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 0, 5);
            Door door = new Door(0, pixelPos,this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            door.setRotation(90);
            myEntitiesToAdd.add(door);
        }
        if (theRoom.isE())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 18, 5);
            Door door = new Door(0, pixelPos,this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            door.setRotation(270);
            myEntitiesToAdd.add(door);
        }

        while(items.length()>1)
        {
            String item = items.substring(0, items.indexOf(",")+1);
            items.delete(0, items.indexOf(",")+1);
            myEntitiesToAdd.add(generateItem(item.substring(0,item.indexOf(","))));
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
        for (Entity e : myEntities)
        {
            e.update();
        }
    }

    public Monster generateOgre()
    {
        return generateMonster("ogre");
    }

    public Monster generateGremlin()
    {
        return generateMonster("gremlin");
    }

    public Monster generateKnight()
    {
        return generateMonster("knight");
    }

    public Monster generateRats()
    {
        return generateMonster("rat");
    }

    public List<Monster> generateMonsters(final int theN)
    {
        var ran = new Random().nextDouble();
        var arr = new ArrayList<Monster>();

        if(ran <= 0.25)
        {
            for (int i = 0; i < 4; i++)
            {
                arr.add(generateRats());
            }
        }
        else if(ran <= 0.50)
        {
            for (int i = 0; i < 2; i++)
            {
                arr.add(generateGremlin());
            }
        }
        else if(ran <= 0.75)
        {
            arr.add(generateKnight());
        }
        else
        {
            arr.add(generateOgre());
        }

        return arr;
    }

    public Item generateItem(final String theItem)
    {
        switch (theItem.toLowerCase(Locale.ROOT))
        {
            case "healing potion" -> {
                return new HealingPotion(this);
            }
            case "attack potion" -> {
                return new AttackPotion(this);
            }
            case "speed potion" -> {
                return new SpeedPotion(this);
            }
            case "pit" -> {
                return new Pit(this);
            }
            case "encapsulation","inheritance","polymorphism","abstraction"-> {
                return new Pillar(theItem, this);
            }
            case "exit" -> {
                return Exit.getInstance(this);
            }
            case "bomb" -> {
                return new Bomb(this);
            }
        }
        return new HealingPotion(this);
    }

    public Pit generatePit()
    {
        return new Pit(this); //added new EntityFactory param
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
        Warrior warrior = new Warrior("Warrior", Physics.getPosition(0,0,9,5), this);
        warrior.setMyAnimation(myAssets.getAnimation("runDown"));
        myEntitiesToAdd.add(warrior);
        return warrior;
    }

    public Hero generateThief()
    {
        Thief thief = new Thief("Thief", Physics.getPosition(0,0,9,5), this);
        thief.setMyAnimation(myAssets.getAnimation("runDown"));
        myEntitiesToAdd.add(thief);
        return thief;
    }

    public Hero generatePriestess()
    {
        Priestess priestess = new Priestess("Priestess", Physics.getPosition(0,0,9,5), this);
        priestess.setMyAnimation(myAssets.getAnimation("runDown"));
        myEntitiesToAdd.add(priestess);
        return priestess;
    }

    public ArrayList<Pillar> generatePillars()
    {
        var arr = new ArrayList<Pillar>();

        arr.add(new Pillar("Encapsulation", this)); //added new EntityFactory params here
        arr.add(new Pillar("Inheritance", this));
        arr.add(new Pillar("Abstraction", this));
        arr.add(new Pillar("Polymorphism", this));

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

    //added this method
    public Sword generateSword()
{
        var sword = Sword.getInstance(this, myHero);
        myEntities.add(sword);
        generateKnight();
        return sword;
}

    public ArrayList<Entity> getEntities() { return myEntities; }

    public Hero getHero()
    {
        return myHero;
    }

    public ArrayList<Monster> getMonsters()
    {
        var monsters = new ArrayList<Monster>();
        for (var e: myEntities)
        {
            if(e instanceof Monster)
            {
                monsters.add((Monster) e);
            }
        }

        return monsters;
    }
    public Assets getAssets() { return myAssets; }
}