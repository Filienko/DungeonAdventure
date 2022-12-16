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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class EntityFactory implements Serializable
{
    private ArrayList<Entity> myEntities;
    private ArrayList<Entity> myEntitiesToAdd;
    private transient ObjectMap<String, ArrayList<Entity>> myEntityMap;
    private long myTotalEntities;
    private transient Assets myAssets;
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

        return DB.createMonsterDB(monsterType,this);
    }

    public void generateGameEntities(final Dungeon theDungeon)
    {
        for (var room: theDungeon.getRooms())
        {
            generateRoomEntities(room);
        }
    }

    public void generateRoomEntities(final Room theRoom) {
        var items = theRoom.getItems();
        var monsters = theRoom.getMonsters();
        Vec2 location = theRoom.getLocation();

        int monsterCounter = generateRoomMonsters(monsters, location);

        generateWalls(theRoom,monsterCounter);

        generateRoomItems(items, location, monsterCounter);

        if (theRoom.isLava())
        {
            generateLava((int) theRoom.getLocation().getMyX(), (int) theRoom.getLocation().getMyY());
        }
    }

    private int generateRoomMonsters(final StringBuilder theMonsters, final Vec2 theLocation)
    {
        int monsterCounter = 0;
        while (theMonsters.toString().contains(","))
        {
            String monster = theMonsters.substring(0,theMonsters.indexOf(",") + 1);
            theMonsters.delete(0, theMonsters.indexOf(",") + 1);
            monster = monster.substring(0, monster.indexOf(","));
            var e = generateMonsters(monster);

            if (e.getMonsterType().contentEquals("rat"))
            {
                e.setRoom(theLocation, monsterCounter);
            }
            else
            {
                e.setRoom(theLocation);
            }
            myEntitiesToAdd.add(e);
            monsterCounter++;
        }

        return monsterCounter;
    }

    private void generateRoomItems(StringBuilder theItems, final Vec2 theLocation, final int theMonsterCounter)
    {
        Vec2 pixelPos;
        while(theItems.toString().contains(","))
        {
            String item = theItems.substring(0, theItems.indexOf(",")+1);
            theItems.delete(0, theItems.indexOf(",")+1);
            var i = generateItems(item.substring(0,item.indexOf(",")));
            if(i.getType().contentEquals("pillar") || i.getType().contentEquals("exit"))
            {
                pixelPos = Physics.getPosition((int) theLocation.getMyX(), (int) theLocation.getMyY(), 9,5);
            }
            else
            {
                pixelPos = Physics.getPosition((int) theLocation.getMyX(), (int) theLocation.getMyY(),
                        (int) i.getMyPos().getMyX(), (int) i.getMyPos().getMyY());
            }

            if(i.getType().contentEquals("pillar"))
            {
                ((Pillar)i).setMonsterCounter(theMonsterCounter);
            }
            i.setRoom(theLocation);
            i.setMyPos(pixelPos);
            i.setMyPreviousPos(pixelPos);
            myEntitiesToAdd.add(i);
        }
    }

    public void generateWorm(final Vec2 theLocation)
    {
        SuperMonsterDB DB = new MonsterDB();
        Vec2 pixelPos = Physics.getPosition((int) theLocation.getMyX(), (int) theLocation.getMyY(), 9, 5);
        var worm = DB.createWormDB(pixelPos,this);
        myEntitiesToAdd.add(worm);
    }

    private void generateLava(final int roomX, final int roomY)
    {
        FileHandle file = Gdx.files.internal("lava.txt");
        String[] layout = file.readString().split("\r\n");
        Vec2 pixelPos;
        Wall lava;

        for (String s : layout)
        {
            String[] tiles = s.split(" ");
            int tileX = Integer.parseInt(tiles[0]);
            int tileY = Integer.parseInt(tiles[1]);
            pixelPos = Physics.getPosition(roomX, roomY, tileX, tileY);
            lava = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(62, 62));
            lava.setMyAnimation(myAssets.getAnimation("lava"));
            lava.setType("Lava");
            lava.setAnimationAngle("lava");
            myEntitiesToAdd.add(lava);
        }
    }

    private void generateWalls(final int roomX, final int roomY)
    {
        FileHandle file = Gdx.files.internal("walls.txt");
        String[] layout = file.readString().split("\r\n");
        Vec2 pixelPos;
        Wall wall;

        for (String s : layout)
        {
            String[] attributes = s.split(" ");
            int tileX = Integer.parseInt(attributes[0]);
            int tileY = Integer.parseInt(attributes[1]);
            pixelPos = Physics.getPosition(roomX, roomY, tileX, tileY);
            String animation = attributes[2];
            int rotation = Integer.parseInt(attributes[3]);

            wall = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(64, 64));
            wall.setAnimationAngle(animation);
            wall.setMyAnimation(myAssets.getAnimation(animation));
            wall.setRotation(rotation);
            myEntitiesToAdd.add(wall);
        }
    }

    void generateWalls(final Room theRoom,final int theMonsterCounter)
    {
        var location = theRoom.getLocation();
        generateWalls((int) location.getMyX(), (int) location.getMyY());
        if (theRoom.isN())
        {
            generateDoor(location, theMonsterCounter, 9, 10, 0);
        }
        else
        {
            generateWall(location, 9, 10, 0);
        }
        if (theRoom.isS())
        {
            generateDoor(location, theMonsterCounter, 9, 0, 180);
        }
        else
        {
            generateWall(location, 9, 0, 180);
        }
        if (theRoom.isW())
        {
            generateDoor(location, theMonsterCounter, 0, 5, 90);
        }
        else
        {
            generateWall(location, 0, 5, 90);
        }
        if (theRoom.isE())
        {
            generateDoor(location, theMonsterCounter, 18, 5, 270);
        }
        else
        {
            generateWall(location, 18, 5, 270);
        }
    }

    private void generateDoor(final Vec2 theLocation, final int theMonsterCounter, final int tileX, final int tileY,
                              final int theRotation)
    {
        Vec2 pixelPos = Physics.getPosition((int) theLocation.getMyX(), (int) theLocation.getMyY(), tileX, tileY);
        Door door = new Door(theLocation, theMonsterCounter, pixelPos, this);
        door.setMyAnimation(myAssets.getAnimation("door"));
        door.setRoom(theLocation);
        door.setRotation(theRotation);
        myEntitiesToAdd.add(door);
    }

    private void generateWall(final Vec2 theLocation, final int tileX, final int tileY, final int theRotation)
    {
        Vec2 pixelPos = Physics.getPosition((int) theLocation.getMyX(), (int) theLocation.getMyY(), tileX, tileY);
        Wall wall = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(64, 64));
        wall.setMyAnimation(myAssets.getAnimation("wall"));
        wall.setAnimationAngle("wall");
        wall.setRotation(theRotation);
        myEntitiesToAdd.add(wall);
    }


    public void update()
    {
        // add all entities that are pending
        for (Entity e : myEntitiesToAdd)
        {
            myEntities.add(e);
            var type = myEntityMap.get(e.getType().toLowerCase());
            if(type==null)
            {
                var list = new ArrayList<Entity>();
                list.add(e);
                myEntityMap.put(e.getType().toLowerCase(),list);
            }
            else
            {
                myEntityMap.get(e.getType().toLowerCase()).add(e);
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

    public Monster generateMonsters(final String theMonster)
    {
        switch (theMonster)
        {
            case "ogre" -> {
                return generateOgre();
            }
            case "rat" -> {
                return generateRats();
            }
            case "gremlin" -> {
                return generateGremlin();
            }
            case "knight" -> {
                return generateKnight();
            }
        }
        return generateOgre();
    }

    public Item generateItems(final String theItem)
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
            case "lava" -> {
                return new Lava(this);
            }
            case "pillar"-> {
                return new Pillar(theItem, 0, this);
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

    public Lava generateLava() { return new Lava(this); }

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
        myEntities.add(warrior);
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

        arr.add(new Pillar("Encapsulation", 0, this));
        arr.add(new Pillar("Abstraction", 0, this));
        arr.add(new Pillar("Inheritance", 0, this));
        arr.add(new Pillar("Polymorphism", 0, this));

        return arr;
    }

    //added this method
    public Sword generateSword()
    {
        var sword = Sword.getInstance(this, myHero);
        if(myEntityMap.get("Sword")==null)
        {
            myEntitiesToAdd.add(sword);
            var weapons = new ArrayList<Entity>();
            weapons.add(sword);
            myEntityMap.put("Sword", weapons);
        }

        return sword;
    }

    public ArrayList<Entity> getEntities() { return myEntities; }

    public ArrayList<Entity> getEntities(final String type)
    {
        if (myEntityMap.get(type.toLowerCase()) != null)
        {
            return myEntityMap.get(type.toLowerCase());
        }

        return new ArrayList<Entity>();
    }

    public void addEntity(Entity theEntity)
    {
        myEntitiesToAdd.add(theEntity);
    }

    public Hero getHero()
    {
        return myHero;
    }

    public Assets getAssets() { return myAssets; }

    private void removeDeadEntities()
    {
        for (int i = 0; i < myEntities.size();)
        {
            if (!myEntities.get(i).getActiveStatus())
            {
                var e = myEntities.remove(i);
                myEntityMap.get(e.getType().toLowerCase()).remove(e);
                myTotalEntities--;
            }
            else
            {
                i++;
            }
        }
    }

    public void renewSword()
    {
        myEntityMap.remove("Sword");
    }

    public void setAssets(final Assets theAssets)
    {
        myAssets = theAssets;
    }

    public void initializeEntityFactory(final ArrayList<Entity> theEntities)
    {
        myEntitiesToAdd = new ArrayList<>();
        myEntitiesToAdd.addAll(theEntities);

        for (Entity e: myEntitiesToAdd)
        {
            if (e instanceof Wall)
            {
                e.setMyAnimation(myAssets.getAnimation(((Wall)e).getAnimationAngle()));
            }
            else if(e instanceof Lava)
            {
                System.out.println("LAVA");
                e.setMyAnimation(myAssets.getAnimation("lava"));
            }
            else if(e instanceof Item)
            {
                e.setMyAnimation(myAssets.getAnimation(e.getType()));
                if(e instanceof Pillar && ((Pillar)e).isBroken()){e.setMyAnimation(myAssets.getAnimation("brokenPillar"));}
            }
            else if(e.getType().toLowerCase(Locale.ROOT).contains("monster"))
            {
                e.setMyAnimation(myAssets.getAnimation(((Monster)e).getMonsterType()));
            }
            else if(e.getType().toLowerCase(Locale.ROOT).contains("door"))
            {
                e.setMyAnimation(myAssets.getAnimation("door"));
            }
            else if(e instanceof Hero)
            {
                myHero = (Hero) e;
                e.setMyAnimation(myAssets.getAnimation("runDown"));
            }
            else if(e.getType().toLowerCase(Locale.ROOT).contains("worm"))
            {
                e.setMyAnimation(myAssets.getAnimation("head"));
                ((Worm) e).getTail().setMyAnimation(myAssets.getAnimation("tail"));
                var segments =  ((Worm)e).getSegments();
                for (int i = 0; i < segments.size(); i++)
                {
                    segments.get(0).setMyAnimation(myAssets.getAnimation("body1"));
                    segments.get(1).setMyAnimation(myAssets.getAnimation("body1"));
                    segments.get(2).setMyAnimation(myAssets.getAnimation("body3"));
                }
            }
        }

        myEntityMap = new ObjectMap<>();
        myEntities = new ArrayList<>();
        myTotalEntities = 0;
    }
}