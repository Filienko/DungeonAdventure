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
	private String 						myCurrentScene;
	private ObjectMap<String, Scene> 	mySceneMap;
	private MyRenderer 					myView;
	private boolean 					myRunning;
	private MyInputProcessor 			myInputProcessor;

	// Methods
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

	public Scene getCurrentScene() 					{ return mySceneMap.get(myCurrentScene); }

	public String getCurrentSceneName() 			{ return myCurrentScene; }

	boolean sceneExists(final String theSceneName) 	{ return mySceneMap.get(theSceneName) != null; }

	public boolean isRunning() 						{ return myRunning; }

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
