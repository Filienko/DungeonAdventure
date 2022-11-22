package MVC.Controller;

import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter
{
    GameEngine myGame;

    public MyInputProcessor(GameEngine game)
    {
        myGame = game;
    }

    public boolean keyDown(int keycode)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(keycode))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(keycode), "START");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }

    public boolean keyUp(int keycode)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(keycode))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(keycode), "END");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }
}
