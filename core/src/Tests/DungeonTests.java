package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Room;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static MVC.Model.DungeonItems.Dungeon.generateRooms;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DungeonTests
{
    //In order to test console output, utilizing following source:
    //http://www.mastertheboss.com/various-stuff/testing-java/how-to-verify-the-console-output-in-junit-tests/
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private EntityFactory myEntityFactory = new EntityFactory();
    @BeforeEach
    private void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testRoomGeneration()
    {
        testRoomGenerationUnderValue(2);
        testRoomGenerationNormalValue(4);
        testDungeonSetters();
    }

    void testRoomGenerationNormalValue(int theDimension)
    {
        final Dungeon myDungeon = new Dungeon(myEntityFactory,theDimension); //added new EntityFactory
        ArrayList<Room> rooms = generateRooms(theDimension);
        var dungeonMadeOfRooms = (new Dungeon()).generateDungeonFromRooms(rooms,theDimension);
        testRoomGenerationWithValue(myDungeon, dungeonMadeOfRooms);
    }

    void testRoomGenerationUnderValue(int theDimension)
    {
        //Testing the edge case
        final Dungeon myDungeon = new Dungeon(myEntityFactory,theDimension); //added new EntityFactory
        ArrayList<Room> rooms = generateRooms(Math.max(theDimension,4));
        var dungeonMadeOfRooms = (new Dungeon()).generateDungeonFromRooms(rooms,Math.max(theDimension,4));
        testRoomGenerationWithValue(myDungeon, dungeonMadeOfRooms);
    }

    @Test
    private void testRoomGenerationWithValue(final Dungeon theMyDungeon, final Room[][] theDungeonMadeOfRooms)
    {
        assertEquals(4, theMyDungeon.getDimension());
        assertEquals(4, theMyDungeon.getDungeon().length);
        assertEquals(16, theMyDungeon.getRooms().size());

        outerLoop:
        for (var i = 0; i< theMyDungeon.getDimension(); i++)
        {
            for (var j = 0; j< theMyDungeon.getDimension(); j++)
            {
                assertEquals(theDungeonMadeOfRooms[i][j].getNumber(), theMyDungeon.getDungeon()[i][j].getNumber());
            }
        }
    }

    @Test
    private void testDungeonSetters()
    {
        var dungeon = new Dungeon(myEntityFactory,4);

        assertTrue(dungeon.getRooms().size()==16);
        dungeon.setRooms(generateRooms(5));
        assertTrue(dungeon.getRooms().size()==25);
    }
}