package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
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
    void mazeGenerationWorkTest() throws IOException
    {
        for (int i = 3; i < 10; i++)
        {
            new Dungeon(new Warrior(new EntityFactory()),i); //added new EntityFactory
            assertEquals(i*i-1, Integer.valueOf(out.toString().strip()));
            out.reset();
        }
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
        final Dungeon myDungeon = new Dungeon(new Thief(new EntityFactory()), theDimension); //added new EntityFactory
        ArrayList<Room> rooms = generateRooms(theDimension);
        var dungeonMadeOfRooms = (new Dungeon()).generateDungeonFromRooms(rooms,theDimension);
        testRoomGenerationWithValue(myDungeon, dungeonMadeOfRooms);
    }

    void testRoomGenerationUnderValue(int theDimension)
    {
        //Testing the edge case
        final Dungeon myDungeon = new Dungeon(new Thief(new EntityFactory()), theDimension); //added new EntityFactory
        ArrayList<Room> rooms = generateRooms(Math.max(theDimension,4));
        var dungeonMadeOfRooms = (new Dungeon()).generateDungeonFromRooms(rooms,Math.max(theDimension,4));
        testRoomGenerationWithValue(myDungeon, dungeonMadeOfRooms);
    }

    @Test
    private void testRoomGenerationWithValue(final Dungeon theMyDungeon, final Room[][] theDungeonMadeOfRooms)
    {
        assertTrue(theMyDungeon.getHero() instanceof Thief);
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
        var dungeon = new Dungeon();
        assertTrue(dungeon.getHero() instanceof Warrior);
        dungeon.setHero(new Thief(new EntityFactory())); //added new EntityFactory
        assertTrue(dungeon.getHero() instanceof Thief);

        dungeon = new Dungeon();
        assertTrue(dungeon.getRooms().size()==16);
        dungeon.setRooms(generateRooms(5));
        assertTrue(dungeon.getRooms().size()==25);

    }
}