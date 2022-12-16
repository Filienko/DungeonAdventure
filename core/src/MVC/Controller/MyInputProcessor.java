package MVC.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter
{
    /**
     * The GameEngine that this class handles input for
     */
    GameEngine myGame;

    /**
     * Constructor that takes one argument.
     * @param theGame The GameEngine that this class handles input for
     */
    public MyInputProcessor(GameEngine theGame)
    {
        myGame = theGame;
    }

    @Override
    public boolean keyDown(int theKeycode)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(theKeycode))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(theKeycode), "START");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }

    @Override
    public boolean keyUp(int theKeycode)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(theKeycode))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(theKeycode), "END");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }

    @Override
    public boolean touchDown(int theX, int theY, int thePointer, int theButton)
    {
        if (theButton == Input.Buttons.LEFT)
        {
            System.out.println("Mouse click at: " + theX +", " + theY);
        }
        if (myGame.getCurrentScene().getActionMap().containsKey(theButton))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(theButton), "START");
            myGame.getCurrentScene().doAction(action);
        }
        return true;
    }

    @Override
    public boolean touchUp (int theX, int theY, int thePointer, int theButton)
    {
        if (myGame.getCurrentScene().getActionMap().containsKey(theButton))
        {
            Action action = new Action(myGame.getCurrentScene().getActionMap().get(theButton), "END");
            myGame.getCurrentScene().doAction(action);
        }
        return false;
    }
}
