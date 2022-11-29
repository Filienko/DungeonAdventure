package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.DungeonItems.Items.SpeedPotion;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class RoomTests
{
    @Test
    void testRoomPopulate()
    {
        var room = new Room();
        var items = room.getItems();
        room.setE(true);

        room.populateTheRoom(true);
        assertTrue(room.getItems().size()==2);
        assertTrue(room.getMonsters().size()==3);
        assertTrue(room.getDoors().size()==1);
    }

    @Test
    void testRoomPopulatePotions()
    {
        var room = new Room();
        var random = new Random();
        room.populatePotions(random,1);
        assertTrue(room.getItems().size()==1);
    }

    @Test
    void testRoomPopulatePit()
    {
        var room = new Room();
        room.populatePit(1.0);
        assertTrue(room.getItems().size()==0);
        room.populatePit(0.09);
        assertTrue(room.getItems().size()==1);
    }
    @Test
    void testRoomPopulateDoor()
    {
        var room = new Room();
        assertTrue(room.getDoors().size()==0);
        room.setW(true);
        room.setN(true);
        room.setS(true);
        room.populateDoors();
        assertTrue(room.getDoors().size()==3);
    }
    @Test
    void testRoomPopulateMonsters()
    {
        var room = new Room();
        room.populateMonsters();
        assertTrue(room.getMonsters().size()==3);
    }
    @Test
    void testRoomSetters()
    {
        var room = new Room();
        assertTrue(room.getNumber()==0);
        assertTrue(room.getItemStatus()==false);
        assertTrue(room.getLocation().equals(new Vec2()));
        assertTrue(room.getDoors().size()==0);
        assertTrue(room.getMonsters().size()==0);
        assertTrue(room.getItems().size()==0);
        assertFalse(room.isRoomHasMonsters());
        assertFalse(room.isTheEntrance());
        assertFalse(room.isTheExit());
        assertFalse(room.isE());
        assertFalse(room.isN());
        assertFalse(room.isS());
        assertFalse(room.isW());
        assertFalse(room.getItemStatus());


        room.setEntranceStatus(true);
        room.setExitStatus(true);
        room.setW(true);
        room.setE(false);
        room.setS(true);
        room.setN(true);
        room.populateDoors();
        room.setRoomHasMonsters(true);
        room.setMonsters((new EntityFactory()).generateMonsters(2));
        room.setLocation(new Vec2(1,2));
        room.addItem(new AttackPotion());
        room.clearRoom();
        room.addItem(new SpeedPotion());
        room.addItem(new HealingPotion());
        room.addItem(new AttackPotion());
        room.removeItem(room.getItems().get(0));
        room.setItemStatus(true);
        room.setNumber(100);

        assertTrue(room.getNumber()==100);
        assertTrue(room.getItemStatus());
        assertTrue(room.getLocation().equals(new Vec2(1,2)));
        assertTrue(room.getDoors().size()==3);
        assertTrue(room.getMonsters().size()==6);
        assertTrue(room.getItems().size()==2);
        assertTrue(room.isRoomHasMonsters());
        assertTrue(room.isTheEntrance());
        assertFalse(room.isTheExit());
        assertFalse(room.isE());
        assertTrue(room.isN());
        assertTrue(room.isS());
        assertTrue(room.isW());
        assertTrue(room.getItemStatus());
    }
}