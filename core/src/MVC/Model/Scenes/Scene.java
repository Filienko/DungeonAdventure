package MVC.Model.Scenes;

import MVC.Controller.GameEngine;
import com.badlogic.gdx.utils.ObjectMap;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;

public abstract class Scene
{
    protected GameEngine myGame;
    protected EntityFactory myEntityFactory;
    private final ObjectMap<Integer, String> myActionMap;
    protected boolean myPaused;
    private boolean myHasEnded;
    protected long myCurrentFrame;

    public Scene()
    {
        myCurrentFrame = 0;
        myActionMap = new ObjectMap<>();
    }
    public Scene(GameEngine gameEngine)
    {
        this();
        myGame = gameEngine;
    }

    protected abstract void onEnd();

    public abstract void update();

    public abstract void doAction(final String action);

    public void setPaused() { myPaused = !myPaused; }

    public void registerAction(final int inputKey, final String actionName)
    {
        myActionMap.put(inputKey, actionName);
    }

    public ObjectMap<Integer, String> getActionMap() { return myActionMap; }

    public float getWidth()         { return myGame.getMyCamera().viewportWidth; }
    public float getHeight()        { return myGame.getMyCamera().viewportHeight; }
    public long getCurrentFrame()   { return myCurrentFrame; }
}
