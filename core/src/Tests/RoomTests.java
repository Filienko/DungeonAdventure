package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.DungeonItems.Items.SpeedPotion;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Entity;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class RoomTests
{
    @Test
    void testRoomPopulate()
    {
        var room = new Room();
        room.populateTheRoom(true);
        room.addItem(new Pillar("Encapsulation", new EntityFactory()));
        room.setE(true);

        room.populateTheRoom(true);
        assertTrue(room.getItems().toString().contains("Pit"));
        assertTrue(room.getItems().toString().contains("Potion"));
        assertTrue(room.getItems().toString().contains("Encapsulation"));
        assertTrue(room.getMonsters().toString().contains("Elf"));
        assertTrue(room.getMonsters().toString().contains("Ogre"));
        assertTrue(room.getMonsters().toString().contains("Gremlin"));
        assertTrue(room.getMonsters().toString().contains("Swarm of Rats"));
    }

    @Test
    void testRoomPopulatePotions()
    {
        var room = new Room();
        var random = new Random();
        room.populatePotions(random,1);
        assertTrue(room.getItems().toString().contains("Potion"));
    }

    @Test
    void testRoomPopulatePit()
    {
        var room = new Room();
        room.populatePit(0.09);
        assertTrue(room.getItems().toString().contains("Pit"));
    }

    @Test
    void testRoomPopulateMonsters()
    {
        var room = new Room();
        room.populateMonsters(1);
        assertTrue(room.getMonsters().toString().contains("Elf"));
        assertTrue(room.getMonsters().toString().contains("Ogre"));
        assertTrue(room.getMonsters().toString().contains("Gremlin"));
        assertTrue(room.getMonsters().toString().contains("Swarm of Rats"));
    }

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
        room.setMonsters((new EntityFactory()).generateMonsters(1));
        room.setLocation(new Vec2(1,2));
        room.addItem(new AttackPotion(new EntityFactory()));
        room.clearRoom();
        room.addItem(new SpeedPotion(new EntityFactory()));
        room.addItem(new HealingPotion(new EntityFactory()));
        room.addItem(new AttackPotion(new EntityFactory()));
        room.removeItem(new AttackPotion(new EntityFactory()));
        room.setNumber(100);

        assertTrue(room.getNumber()==100);
        assertTrue(room.getMonsters().toString().contains("Elf"));
        assertTrue(room.getMonsters().toString().contains("Ogre"));
        assertTrue(room.getMonsters().toString().contains("Gremlin"));
        assertTrue(room.getMonsters().toString().contains("Swarm of Rats"));
        assertTrue(room.getItems().toString().contains("Potion"));
        assertTrue(room.isTheEntrance());
        assertFalse(room.isTheExit());
        assertFalse(room.isE());
        assertTrue(room.isN());
        assertTrue(room.isS());
        assertTrue(room.isW());
    }
}