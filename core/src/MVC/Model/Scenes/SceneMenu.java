package MVC.Model.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class SceneMenu extends Scene
{
    final private String myTitle;
    private ArrayList<String> myMenuSelections;
    private int myMenuIndex;

    public SceneMenu(GameEngine gameEngine)
    {
        myTitle = "Dungeon Adventure";

        registerAction(Input.Keys.W, "UP");
        registerAction(Input.Keys.S, "DOWN");
        registerAction(Input.Keys.D, "SELECT");
        registerAction(Input.Keys.ESCAPE, "QUIT");

        myMenuSelections.add("NEW GAME");
        myMenuSelections.add("LOAD GAME");
        myMenuSelections.add("QUIT");

        myMenuIndex = 0;

    }

    protected void onEnd() { Gdx.app.exit(); }

    public void update() { myCurrentFrame++; }

    public void doAction(final String action)
    {
        switch (action) {
            case "UP":
                if (myMenuIndex > 0) { myMenuIndex--; }
                else { myMenuIndex = myMenuSelections.size() - 1; }
                break;
            case "DOWN":
                myMenuIndex = (myMenuIndex + 1) % myMenuSelections.size();
                break;
            case "QUIT":
                if (myMenuIndex == 0)
                {
                    myGame.setCurrentScene("Dungeon", new SceneGame(myGame,true), false);
                }
                else if (myMenuIndex == 1)
                {
                    myGame.setCurrentScene("Dungeon", new SceneGame(myGame,false), false);
                }
                else if (myMenuIndex == 2)
                {
                    onEnd();
                }
                break;
        }
    }
}
