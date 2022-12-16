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
    /**
     * The Entities the factory has generated.
     */
    private ArrayList<Entity> myEntities;

    /**
     * The Entities that are to be added to the list of generated Entities.
     */
    private ArrayList<Entity> myEntitiesToAdd;

    /**
     * A map where Strings are mapped to Entities.
     */
    private transient ObjectMap<String, ArrayList<Entity>> myEntityMap;

    /**
     * The number of Entities the factory has generated.
     */
    private long myTotalEntities;

    /**
     * The assets the factory utilizes to set the animations of certain Entities.
     */
    private transient Assets myAssets;

    /**
     * The Hero in the game.
     */
    private static Hero myHero;

    /**
     * Entity Factory constructor that initializes its Entity list and map, and sets
     * the total number of Entities to 0.
     */
    public EntityFactory()
    {
        myEntities = new ArrayList<>();
        myEntitiesToAdd = new ArrayList<>();
        myEntityMap = new ObjectMap<>();
        myTotalEntities = 0;
    }

    /**
     * Entity Factory constructor that calls its default constructor to initialize its Entity list and map, and sets
     * the total number of Entities to 0. It also accepts assets to be used for animations, and a Hero to associate the
     * Entities with.
     */
    public EntityFactory(Assets assets, final String theHero)
    {
        this();
        myAssets = assets;
        myHero = generateHero(theHero);
    }

    /**
     * This method generates a Monster of the String type passed to the method.
     * @param monsterType The type of Monster to generate.
     * @return A Monster of the requested type.
     */
    public Monster generateMonster(final String monsterType)
    {
        SuperMonsterDB DB = new MonsterDB();

        return DB.createMonsterDB(monsterType,this);
    }

    /**
     * This method takes in a Dungeon object and generates Entities for each of its Rooms.
     * @param theDungeon Dungeon whose Rooms will have Entities generated inside.
     */
    public void generateGameEntities(final Dungeon theDungeon)
    {
        for (var room: theDungeon.getRooms())
        {
            generateRoomEntities(room);
        }
    }

    /**
     * This method generates various Entities for a Room.
     * @param theRoom The Room to generate Entities for.
     */
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

    /**
     * This method generates Monsters based off the
     * @param theMonsters
     * @param theLocation
     * @return
     */
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

    /**
     * This method generates a Worm.
     * @param theLocation The location to generate the Worm in.
     */
    public void generateWorm(final Vec2 theLocation)
    {
        SuperMonsterDB DB = new MonsterDB();
        Vec2 pixelPos = Physics.getPosition((int) theLocation.getMyX(), (int) theLocation.getMyY(), 9, 5);
        var worm = DB.createWormDB(pixelPos,this);
        myEntitiesToAdd.add(worm);
    }

    /**
     * This method generates Lava in a Room.
     * @param roomX The x-position of the Room.
     * @param roomY The y-position of the Room.
     */
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

    /**
     * This method generates Walls in a Room.
     * @param roomX The x-position of the Room.
     * @param roomY The y-position of the Room.
     */
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

    /**
     * This method generates Walls in a Room when there are Monsters in the Room.
     * @param theRoom The Room to generate Walls in.
     * @param theMonsterCounter The number of Monsters in the Room.
     */
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

    /**
     * This method generates Doors in a Room when there are Monsters in the Room.
     * @param theLocation The location to generate Doors in.
     * @param theMonsterCounter The number of Monsters in the Room.
     * @param tileX The x position of the location to generate the Door in
     * @param tileY The y position of the location to generate the Door in
     * @param tileX The amount that the Door should be rotated.
     */
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

    /**
     * This method generates Walls in a Room.
     * @param theLocation The location to generate the Wall in.
     * @param tileX The x position of the location to generate the Wall in
     * @param tileY The y position of the location to generate the Wall in
     * @param tileX The amount that the Wall should be rotated.
     */
    private void generateWall(final Vec2 theLocation, final int tileX, final int tileY, final int theRotation)
    {
        Vec2 pixelPos = Physics.getPosition((int) theLocation.getMyX(), (int) theLocation.getMyY(), tileX, tileY);
        Wall wall = new Wall(new Vec2(pixelPos.getMyX(), pixelPos.getMyY()), new Vec2(64, 64));
        wall.setMyAnimation(myAssets.getAnimation("wall"));
        wall.setAnimationAngle("wall");
        wall.setRotation(theRotation);
        myEntitiesToAdd.add(wall);
    }


    /**
     * Information about the Entity Factory that is to be updated.
     */
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

    /**
     * This method generates an ogre.
     * @return A Monster object of type Ogre.
     */
    public Monster generateOgre()
    {
        return generateMonster("ogre");
    }

    /**
     * This method generates a gremlin.
     * @return A Monster object of type gremlin.
     */
    public Monster generateGremlin()
    {
        return generateMonster("gremlin");
    }

    /**
     * This method generates a knight.
     * @return A Monster object of type knight.
     */
    public Monster generateKnight()
    {
        return generateMonster("knight");
    }

    /**
     * This method generates a rat.
     * @return A Monster object of type Rat.
     */
    public Monster generateRats()
    {
        return generateMonster("rat");
    }

    /**
     * This method generates a Monster based on the String type passed to it.
     * @param theMonster The type of Monster to generate.
     * @return A Monster object of the requested type.
     */
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

    /**
     * This method generates an Item based on the String type passed to it.
     * @param theItem The type of Item to generate.
     * @return An Item object of the requested type.
     */
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

    /**
     * This method generates Lava.
     * @return Lava for a Room.
     */
    public Lava generateLava() { return new Lava(this); }

    /**
     * This method generates a Hero based on the String type passed to it.
     * @param theHero The type of Hero to generate.
     * @return A Hero object of the requested type.
     */
    public Hero generateHero(final String theHero)
    {
        if (theHero.contentEquals("Warrior"))
        {
            return generateWarrior();
        }
        else if (theHero.contentEquals("Thief"))
        {
            return generateThief();
        }
        else if (theHero.contentEquals("Priestess"))
        {
            return generatePriestess();
        }
        else if (theHero.contentEquals("Mock"))
        {
            return generateMockHero();
        }
        return null;
    }

    /**
     * This method generates a Warrior object.
     * @return A Warrior.
     */
    public Hero generateWarrior()
    {
        Warrior warrior = new Warrior("Warrior", Physics.getPosition(0,0,9,5), this);
        warrior.setMyAnimation(myAssets.getAnimation("runDown"));
        myEntitiesToAdd.add(warrior);
        return warrior;
    }

    /**
     * This method creates a Mock Hero object (for testing).
     * @return A Mock Hero.
     */
    public Hero generateMockHero()
    {
        Warrior warrior = new Warrior("Warrior", Physics.getPosition(0,0,9,5), this);
        myEntities.add(warrior);
        return warrior;
    }

    /**
     * This method generates a Thief object.
     * @return A Thief.
     */
    public Hero generateThief()
    {
        Thief thief = new Thief("Thief", Physics.getPosition(0,0,9,5), this);
        thief.setMyAnimation(myAssets.getAnimation("runDown"));
        myEntitiesToAdd.add(thief);
        return thief;
    }

    /**
     * This method generates a Priestess object.
     * @return A Priestess.
     */
    public Hero generatePriestess()
    {
        Priestess priestess = new Priestess("Priestess", Physics.getPosition(0,0,9,5), this);
        priestess.setMyAnimation(myAssets.getAnimation("runDown"));
        myEntitiesToAdd.add(priestess);
        return priestess;
    }

    /**
     * This method generates an ArrayList of the 4 required Pillars.
     * @return An ArrayList of 4 Pillars.
     */
    public ArrayList<Pillar> generatePillars()
    {
        var arr = new ArrayList<Pillar>();

        arr.add(new Pillar("Encapsulation", 0, this));
        arr.add(new Pillar("Inheritance", 0, this));
        arr.add(new Pillar("Abstraction", 0, this));
        arr.add(new Pillar("Polymorphism", 0, this));

        return arr;
    }

    /**
     * This method gets instance of the Sword.
     * @return The instance of the Sword.
     */
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

    /**
     * This method gets all the Entities that the factory has generated.
     * @return An ArrayList of the Entities.
     */
    public ArrayList<Entity> getEntities() { return myEntities; }

    /**
     * This method gets all the Entities in the factory's Entity Map.
     * @return An ArrayList of the Entity Map.
     */
    public ArrayList<Entity> getEntities(final String type)
    {
        if (myEntityMap.get(type.toLowerCase()) != null)
        {
            return myEntityMap.get(type.toLowerCase());
        }

        return new ArrayList<Entity>();
    }

    /**
     * This method adds an Entity to the list of Entities to be added to the final list of Entities.
     * @param theEntity The Entity to add.
     */
    public void addEntity(Entity theEntity)
    {
        myEntitiesToAdd.add(theEntity);
    }

    /**
     * This method retrieves the Hero associated with the Entities the factory has generated.
     * @return The Hero.
     */
    public Hero getHero()
    {
        return myHero;
    }

    /**
     * This method returns the Assets that are being used for the Entity Animations.
     * @return The Entity Factory's Assets.
     */
    public Assets getAssets() { return myAssets; }

    /**
     * This method removes all dead Entities from the factory's list of Entities.
     */
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

    /**
     * This method removes the Sword from the Entity Map.
     */
    public void renewSword()
    {
        myEntityMap.remove("Sword");
    }

    /**
     * This method sets the Assets that are to be used for the Entity Animations.
     * @param theAssets The Entity Factory's new Assets.
     */
    public void setAssets(final Assets theAssets)
    {
        myAssets = theAssets;
    }

    /**
     * This method initializes the Entity Factory.
     * @param theEntities The ArrayList of Entities to add to the factory.
     */
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