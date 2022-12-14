package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.DungeonItems.Items.SpeedPotion;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class RoomTests
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

    /**
     * Test method for {@link Room#populateTheRoom(boolean)}.
     */
    @Test
    void testRoomPopulate()
    {
        var room = new Room();
        room.populateTheRoom(true);
        room.addItem(new Pillar(entityFactory));
        room.setE(true);

        room.setLocation(new Vec2(1,2));
        room.populateTheRoom(true);

        assertTrue(room.isLava());
        assertTrue(room.getItems().toString().contains("Potion"));
        assertTrue(room.getItems().toString().contains("pillar"));
        assertTrue(room.getMonsters().toString().length()>3);
    }

    /**
     * Test method for {@link Room#populatePotions(Random, int)}.
     */
    @Test
    void testRoomPopulatePotions()
    {
        var room = new Room();
        var random = new Random();
        room.populatePotions(random,1);
        assertTrue(room.getItems().toString().contains("Potion"));
    }

    /**
     * Test method for {@link Room#populateLava(double)}.
     */
    @Test
    void testRoomPopulateLava()
    {
        var room = new Room();
        room.populateLava(0.09);
        assertTrue(room.isLava());
    }

    /**
     * Test method for {@link Room#populateMonsters(int)}.
     */
    @Test
    void testRoomPopulateMonsters()
    {
        var room = new Room();
        room.populateMonsters(1);
        assertTrue(room.getMonsters().toString().length()>3);
    }

    /**
     * Test method for various Room setters.
     */
    @Test
    void testRoomSetters()
    {
        var room = new Room();
        assertTrue(room.getNumber()==0);
        assertTrue(room.getLocation().equals(new Vec2()));
        assertTrue(room.getMonsters().length()==0);
        assertTrue(room.getItems().length()==0);
        assertFalse(room.isTheEntrance());
        assertFalse(room.isTheExit());
        assertFalse(room.isE());
        assertFalse(room.isN());
        assertFalse(room.isS());
        assertFalse(room.isW());


        room.setEntranceStatus(true);
        room.setExitStatus(true);
        room.setW(true);
        room.setE(false);
        room.setS(true);
        room.setN(true);
        room.setLocation(new Vec2(1,2));
        room.addItem(new AttackPotion(entityFactory));
        room.clearRoom();
        room.addItem(new SpeedPotion(entityFactory));
        room.addItem(new HealingPotion(entityFactory));
        room.addItem(new AttackPotion(entityFactory));
        room.setNumber(100);

        room.setMonsters(Arrays.asList(entityFactory.generateMonster("ogre"),
                new EntityFactory().generateMonster("gremlin")));

        assertTrue(room.getNumber()==100);
        assertTrue(room.getMonsters().toString().length()>1);
        assertTrue(room.getItems().toString().contains("Potion"));
        assertTrue(room.isTheEntrance());
        assertFalse(room.isTheExit());
        assertFalse(room.isE());
        assertTrue(room.isN());
        assertTrue(room.isS());
        assertTrue(room.isW());
    }
}