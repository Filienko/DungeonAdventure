package MVC.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter
{
    GameEngine myGame;

    public MyInputProcessor(GameEngine game)
    {
        myGame = game;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(keycode))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(keycode), "START");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(keycode))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(keycode), "END");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        if (button == Input.Buttons.LEFT)
        {
            System.out.println("Mouse click at: " + x +", " + y);
        }
        if (myGame.getCurrentScene().getActionMap().containsKey(button))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(button), "START");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(button))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(button), "END");
            myGame.getCurrentScene().doAction(action);
        }
        return false;
    }
}
