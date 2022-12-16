package MVC.View.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.View.Assets;
import MVC.View.MyRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

import java.util.ArrayList;

public abstract class Scene
{
    /**
     * The GameEngine this Scene belongs to
     */
    protected GameEngine myGame;
    /**
     * The MyRenderer that calls render() on this Scene
     */
    protected MyRenderer myRenderer;
    /**
     * The EntityFactory that belongs to this Scene
     */
    protected EntityFactory myEntityFactory;
    /**
     * The map of actions that this Scene can do
     */
    private final ObjectMap<Integer, String> myActionMap;
    /**
     * Whether this Scene is paused
     */
    protected boolean myPaused;
    /**
     * The current frame of this Scene
     */
    protected long myCurrentFrame;
    /**
     * The title of this Scene
     */
    protected String myTitle;

    /**
     * Constructor that takes zero arguments
     */
    public Scene()
    {
        myCurrentFrame = 0;
        myActionMap = new ObjectMap<>();
    }

    /**
     * Constructor that takes one argument
     * @param theGameEngine The GameEngine that this Scene belongs to
     */
    public Scene(GameEngine theGameEngine)
    {
        this();
        myGame = theGameEngine;
        myRenderer = myGame.getView();
    }

    /**
     * Behavior for this Scene when it ends
     */
    protected abstract void onEnd();

    /**
     * Behavior for this Scene every frame
     */
    public abstract void update();

    /**
     * Handles Actions sent by the GameEngine
     * @param theAction Name of the action and whether it is the start or end of the action
     */
    public abstract void doAction(final Action theAction);

    /**
     * Method called by MyRenderer to draw the Scene
     */
    public abstract void render();

    /**
     * Toggles whether the Scene is paused
     */
    public void setPaused()                             { myPaused = !myPaused; }

    /**
     * Adds an Action to this Scene's handleable Actions
     * @param theInputKey The input key that initiates the Action
     * @param theActionName The name of the Action
     */
    public void registerAction(final int theInputKey, final String theActionName)
    {
        myActionMap.put(theInputKey, theActionName);
    }

    /**
     * @return This Scene's EntityFactory
     */
    public EntityFactory getEntityFactory()             { return myEntityFactory; }

    /**
     * @return This Scene's ActionMap
     */
    public ObjectMap<Integer, String> getActionMap()    { return myActionMap; }
}
