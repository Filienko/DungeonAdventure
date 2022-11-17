package Tests;

import MVC.Controller.GameEngine;
import MVC.Model.Scenes.SceneGame;
import MVC.Model.Scenes.SceneMenu;
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
        menu.doAction("UP");
        assertEquals(2, menu.getMenuIndex());
        menu.doAction("UP");
        assertEquals(1, menu.getMenuIndex());
        menu.doAction("UP");
        assertEquals(0, menu.getMenuIndex());
    }

    @Test
    void cycleDown()
    {
        menu.doAction("DOWN");
        assertEquals(1, menu.getMenuIndex());
        menu.doAction("DOWN");
        assertEquals(2, menu.getMenuIndex());
        menu.doAction("DOWN");
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