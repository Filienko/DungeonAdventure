package MVC.Controller;

import MVC.View.Scenes.Scene;
import MVC.View.Scenes.SceneMenu;
import MVC.View.MyRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ObjectMap;


public class GameEngine extends ApplicationAdapter {

	// Member Fields
	/**
	 * The name of the current scene
	 */
	private String 						myCurrentScene;
	/**
	 * The map of all scenes
	 */
	private ObjectMap<String, Scene> 	mySceneMap;
	/**
	 * The top level view class
	 */
	private MyRenderer 					myView;
	/**
	 * Whether the game engine is running or not
	 */
	private boolean 					myRunning;
	/**
	 * Class that handles input
	 */
	private MyInputProcessor 			myInputProcessor;

	/**
	 * Sets the current scene to the passed argument
	 * @param theName The name of the scene to be set to current
	 * @param theScene The Scene class of the scene to be set, null if the Scene should already exist
	 * @param theEndCurrentScene If the current Scene should be ended when it sets the new current Scene
	 */
	public void setCurrentScene(final String theName, final Scene theScene, final boolean theEndCurrentScene)
	{
		// If a scene was passed, add it to the map with the scene theName
		if (theScene != null)
		{
			mySceneMap.put(theName, theScene);
		}
		else
		{
			// If no scene was passed and the scene name is not in the map then return a warning
			if (mySceneMap.get(theName) == null)
			{
				System.out.println("Warning: Scene does not exist: " + theName);
			}
		}

		if (theEndCurrentScene)
		{
			mySceneMap.remove(myCurrentScene);
		}

		myCurrentScene = theName;
	}

	/**
	 * @return The current Scene
	 */
	public Scene getCurrentScene() 					{ return mySceneMap.get(myCurrentScene); }

	/**
	 * @return The name of the current scene
	 */
	public String getCurrentSceneName() 			{ return myCurrentScene; }

	/**
	 * @param theSceneName The name of the Scene to be checked
	 * @return Whether any Scene exists attached to the passed name
	 */
	boolean sceneExists(final String theSceneName) 	{ return mySceneMap.get(theSceneName) != null; }

	/**
	 * @return Whether the game is running
	 */
	public boolean isRunning() 						{ return myRunning; }

	/**
	 * @return The top level View class
	 */
	public MyRenderer getView() 					{ return myView; }

	@Override
	public void create ()
	{
		mySceneMap 	= new ObjectMap<>();
		myRunning 	= true;
		myView		= new MyRenderer(this);
		myInputProcessor = new MyInputProcessor(this);
		Gdx.input.setInputProcessor(myInputProcessor);
		setCurrentScene("Menu", new SceneMenu(this), false);
	}

	@Override
	public void render ()
	{
		if (!myRunning) 			{ return; }
		if (mySceneMap.isEmpty()) 	{ return; }

		getCurrentScene().update();
		myView.render();
	}
	
	@Override
	public void dispose ()
	{
		// Dispose all Assets
		myView.getAssets().dispose();

		// Dispose batch
		myView.getSpriteBatch().dispose();
	}
}
