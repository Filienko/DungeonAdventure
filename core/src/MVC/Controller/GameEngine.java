package MVC.Controller;

import MVC.Model.Scenes.Scene;
import MVC.Model.Scenes.SceneMenu;
import MVC.View.Assets;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameEngine extends ApplicationAdapter {

	// Member Fields
	private OrthographicCamera 			myCamera;
	private Assets 						myAssets;
	private String 						myCurrentScene;
	private ObjectMap<String, Scene> 	mySceneMap;
	//private Renderer					myView;
	private boolean 					myRunning;
	private SpriteBatch 				batch;
	private MyInputProcessor 			myInputProcessor;

//	Animation test1;
//	Animation test2;
//	Animation test3;

	// Methods
	public void setCurrentScene(final String name, final Scene scene, final boolean endCurrentScene)
	{
		// If a scene was passed, add it to the map with the scene name
		if (scene != null)
		{
			mySceneMap.put(name, scene);
		}
		else
		{
			// If no scene was passed and the scene name is not in the map then return a warning
			if (mySceneMap.get(name) == null)
			{
				System.out.println("Warning: Scene does not exist: " + name);
			}
		}

		if (endCurrentScene)
		{
			mySceneMap.remove(myCurrentScene);
		}

		myCurrentScene = name;
	}

	public Scene getCurrentScene() { return mySceneMap.get(myCurrentScene); }

	boolean sceneExists(final String sceneName) { return mySceneMap.get(sceneName) != null; }

	public OrthographicCamera getMyCamera() { return myCamera; }

	public Assets getMyAssets() { return myAssets; }

	public boolean isRunning() { return myRunning; }

	@Override
	public void create ()
	{

		myAssets 	= new Assets();
		mySceneMap 	= new ObjectMap<>();
		myRunning 	= true;
		myCamera 	= new OrthographicCamera();
		myCamera.setToOrtho(false, 1280, 768);

		batch = new SpriteBatch();
		myInputProcessor = new MyInputProcessor(this);
		Gdx.input.setInputProcessor(myInputProcessor);

		myAssets.loadAssets();

//		test1 = myAssets.getAnimation("runDown");
//		test2 = myAssets.getAnimation("tektite");
//		test3 = myAssets.getAnimation("standDown");

		setCurrentScene("Menu", new SceneMenu(this), false);
	}

	@Override
	public void render ()
	{
		if (!myRunning) 			{ return; }
		if (mySceneMap.isEmpty()) 	{ return; }

		getCurrentScene().update();
//		test1.update();
//		test2.update();
//		test3.update();
		ScreenUtils.clear(0, 0, 0.2f, 1);
		myCamera.update();
		batch.setProjectionMatrix(myCamera.combined);
		batch.begin();
//		batch.draw(test1.getSprite(), 0, 0);
//		batch.draw(test2.getSprite(), 100, 100);
//		batch.draw(test3.getSprite(), 75, 444);
		batch.end();
	}
	
	@Override
	public void dispose ()
	{
		// Dispose all Textures

		// Dispose batches
		batch.dispose();
	}
}
