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
        SuperMonsterDB DB = new MonsterDB();
        var monster = DB.createMonsterDB(monsterType, myHero,this);
        myEntitiesToAdd.add(monster);
        return monster;
    }

    public ArrayList<Entity> generateGameEntities(final Dungeon theDungeon)
    {
        for (var room: theDungeon.getRooms())
        {
            generateRoomEntities(room);
        }
        return myEntitiesToAdd;
    }

    public ArrayList<Entity> generateRoomEntities(final Room theRoom)
    {
        theRoom.setEntityFactory(this);

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

        while(items.toString().contains(","))
        {
            String item = items.substring(0, items.indexOf(",")+1);
            items.delete(0, items.indexOf(",")+1);
            var i = generateItem(item.substring(0,item.indexOf(",")));
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(),
                    (int) i.getMyPos().getMyX(), (int) i.getMyPos().getMyY());
            i.setMyPos(pixelPos);
            myEntitiesToAdd.add(i);
        }

        int monsterCounter = 0;
        while(monsters.toString().contains(","))
        {
            String monster = monsters.substring(0, monsters.indexOf(",")+1);
            monsters.delete(0, monsters.indexOf(",")+1);
            monster = monster.substring(0,monster.indexOf(","));
            var e = generateMonster(monster);
            e.setRoom(location);
            monsterCounter++;
        }

        if (theRoom.isN())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 9, 10);
            Door door = new Door(location,monsterCounter, pixelPos, this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            myEntitiesToAdd.add(door);
        }
        if (theRoom.isS())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 9, 0);
            Door door = new Door(location,monsterCounter, pixelPos,this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            door.setRotation(180);
            myEntitiesToAdd.add(door);
        }
        if (theRoom.isW())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 0, 5);
            Door door = new Door(location,monsterCounter, pixelPos,this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            door.setRotation(90);
            myEntitiesToAdd.add(door);
        }
        if (theRoom.isE())
        {
            pixelPos = Physics.getPosition((int) location.getMyX(), (int) location.getMyY(), 18, 5);
            Door door = new Door(location,monsterCounter, pixelPos,this);
            door.setMyAnimation(myAssets.getAnimation("door"));
            door.setRotation(270);
            myEntitiesToAdd.add(door);
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

        removeDeadEntities();

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
        switch (theItem)
        {
            case "healthPotion" -> {
                return new HealingPotion(this);
            }
            case "attackPotion" -> {
                return new AttackPotion(this);
            }
            case "speedPotion" -> {
                return new SpeedPotion(this);
            }
            case "pit" -> {
                return new Pit(this);
            }
            case "pillar"-> {
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
        else if (type1.contentEquals("Mock"))
        {
            return generateMockHero();
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

    public Hero generateMockHero()
    {
        Warrior warrior = new Warrior("Warrior", Physics.getPosition(0,0,9,5), this);
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


    public List<HealingPotion> generateHealingPotions(final int theN)
    {
        var arr = new ArrayList<HealingPotion>();

        for (int i = 0; i < theN; i++)
        {
            var p = new HealingPotion(this);
            p.setMyPos(getHero().getMyPos().add(new Vec2(100,50)));
            myEntitiesToAdd.add(p);
        }

        return arr;
    }

    public List<SpeedPotion> generateSpeedPotions(final int theN)
    {
        var arr = new ArrayList<SpeedPotion>();

        for (int i = 0; i < theN; i++)
        {
            var p = new SpeedPotion(this);
            p.setMyPos(getHero().getMyPos().add(new Vec2(100,-50)));
            myEntitiesToAdd.add(p);
        }

        return arr;
    }

    public List<AttackPotion> generateAttackPotions(final int theN)
    {
        var arr = new ArrayList<AttackPotion>();

        for (int i = 0; i < theN; i++)
        {
            var p = new AttackPotion(this);
            p.setMyPos(getHero().getMyPos().add(new Vec2(-100,50)));
            myEntitiesToAdd.add(p);
        }

        return arr;
    }

    //added this method
    public Sword generateSword()
{
        var sword = Sword.getInstance(this, myHero);
        myEntitiesToAdd.add(sword);

        return sword;
}

    public ArrayList<Entity> getEntities() { return myEntities; }

    public Hero getHero()
    {
        return myHero;
    }

    public ArrayList<Entity> getMonsters()
    {
        return myEntityMap.get("Monster");
    }

    public ArrayList<Entity> getDoors()
    {
        return myEntityMap.get("Door");
    }

    public Assets getAssets() { return myAssets; }

    private void removeDeadEntities()
    {
        for (int i = 0; i < myEntities.size();)
        {
            if (!myEntities.get(i).getActiveStatus())
            {
                myEntities.remove(i);
                myTotalEntities--;
            }
            else
            {
                i++;
            }
        }
    }
}