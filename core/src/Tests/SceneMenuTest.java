package Tests;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.View.Scenes.SceneMenu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SceneMenuTest
{
    private static GameEngine game;
    private static SceneMenu menu;

    @BeforeAll
    static void initializeTests()
    {
        game = new GameEngine();
        menu = new SceneMenu(game);
    }


    @Test
    void cycleUp()
    {
        menu.doAction(new Action("UP", "START"));
        assertEquals(2, menu.getMenuIndex());
        menu.doAction(new Action("UP", "START"));
        assertEquals(1, menu.getMenuIndex());
        menu.doAction(new Action("UP", "START"));
        assertEquals(0, menu.getMenuIndex());
    }

    @Test
    void cycleDown()
    {
        menu.doAction(new Action("DOWN", "START"));
        assertEquals(1, menu.getMenuIndex());
        menu.doAction(new Action("DOWN", "START"));
        assertEquals(2, menu.getMenuIndex());
        menu.doAction(new Action("DOWN", "START"));
        assertEquals(0, menu.getMenuIndex());
    }

    @Test
    void updateFrame()
    {
        assertEquals(0, menu.getCurrentFrame());
        menu.update();
        assertEquals(1, menu.getCurrentFrame());
    }
}