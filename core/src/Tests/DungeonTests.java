package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonItems.Dungeon;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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

    /**
     * Test methods for {@link Dungeon#generateRooms(int)}.
     */
    @Test
    void testRoomGeneration()
    {
        testRoomGenerationUnderValue(2);
        testRoomGenerationNormalValue(4);
        testDungeonSetters();
    }

    /**
     * Test helper method for {@link Dungeon#generateRooms(int)}.
     */
    void testRoomGenerationNormalValue(int theDimension)
    {
        final Dungeon myDungeon = new Dungeon(theDimension); //added new EntityFactory
        testRoomGenerationWithValue(myDungeon);
    }

    /**
     * Test helper method for {@link Dungeon#generateRooms(int)}.
     */
    void testRoomGenerationUnderValue(int theDimension)
    {
        //Testing the edge case
        final Dungeon myDungeon = new Dungeon(theDimension);
        testRoomGenerationWithValue(myDungeon);
    }

    /**
     * Test helper method for {@link Dungeon#generateRooms(int)}.
     */
    @Test
    private void testRoomGenerationWithValue(final Dungeon theMyDungeon)
    {
        assertEquals(4, theMyDungeon.getDimension());
        assertEquals(16, theMyDungeon.getRooms().size());
    }

    /**
     * Test method for {@link Dungeon#setRooms(List)}.
     */
    @Test
    private void testDungeonSetters()
    {
        var dungeon = new Dungeon(4);

        assertTrue(dungeon.getRooms().size()==16);
        dungeon.setRooms(generateRooms(5));
        assertTrue(dungeon.getRooms().size()==25);
    }
}