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
    protected GameEngine myGame;
    protected MyRenderer myRenderer;
    protected EntityFactory myEntityFactory;
    private final ObjectMap<Integer, String> myActionMap;
    protected boolean myPaused;
    private boolean myHasEnded;
    protected long myCurrentFrame;
    protected String myTitle;

    public Scene()
    {
        myCurrentFrame = 0;
        myActionMap = new ObjectMap<>();
    }
    public Scene(GameEngine gameEngine)
    {
        this();
        myGame = gameEngine;
        myRenderer = myGame.getView();
    }

    protected abstract void onEnd();

    public abstract void update();

    public abstract void doAction(final Action action);

    public abstract void render();

    public void setPaused() { myPaused = !myPaused; }

    public void registerAction(final int inputKey, final String actionName)
    {
        myActionMap.put(inputKey, actionName);
    }

    public EntityFactory getEntityFactory() { return myEntityFactory; }

    public ObjectMap<Integer, String> getActionMap() { return myActionMap; }

    public String getMyTitle() { return myTitle; }

    public long getCurrentFrame()   { return myCurrentFrame; }
}
