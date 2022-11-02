package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import javax.swing.text.View;
import java.util.Iterator;

public class GameEngine extends ApplicationAdapter {

	// Not sure what to do with this yet
	private SpriteBatch batch;

	// Member Fields
	private OrthographicCamera 			myCamera;
	private Assets 						myAssets;
	private String 						myCurrentScene;
	private ObjectMap<String, Scene> 	mySceneMap;
	private Render						myView;
	private boolean 					myRunning;

	// Constructor
	public GameEngine()
	{
		myAssets 	= new Assets();
		mySceneMap 	= new ObjectMap<String, Scene>();
		myRunning 	= true;
		create();

		run();
	}

	// Methods

	void update()
	{
		if (!myRunning) 			{ return; }
		if (mySceneMap.isEmpty()) 	{ return; }

		userInput();
		getCurrentScene().update();
		render();
	}

	void userInput()
	{

	}

	void setCurrentScene(final String name, final Scene scene, final boolean endCurrentScene)
	{
		// If a scene was passed, add it to the map with the scene name
		if (scene != null)
		{
			mySceneMap.put(name, scene);
		}
		else
		{
			// If no scene was passed and the scene name is not in the map then return a warning
			if (mySceneMap.find(name) == null)
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

	Scene getCurrentScene() {}

	boolean sceneExists() {}

	void run()
	{
		while (myRunning)
		{
			update();
		}
	}

	OrthographicCamera getMyCamera() {}

	Assets getMyAssets() {}

	boolean isRunning() {}

	void exit() { dispose(); }


	@Override
	public void create ()
	{

		myCamera = new OrthographicCamera();
		myCamera.setToOrtho(false, 1280, 768);

		Gdx.input.setInputProcessor(new InputAdapter()
		{

		});

		batch = new SpriteBatch();

		myAssets.loadAssets();

		setCurrentScene("Menu", new SceneMenu());
	}

	@Override
	public void render ()
	{
		ScreenUtils.clear(0, 0, 0.2f, 1);
		myCamera.update();
		batch.setProjectionMatrix(myCamera.combined);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		for (Rectangle raindrop: raindrops)
		{
			batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		batch.end();

		if (Gdx.input.isTouched())
		{
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			myCamera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

		if (bucket.x < 0) bucket.x = 0;
		if (bucket.x > 800 - 64) bucket.x = 800 - 64;

		if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext();)
		{
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0) iter.remove();
			if (raindrop.overlaps(bucket))
			{
				dropSound.play();
				iter.remove();
			}
		}
	}
	
	@Override
	public void dispose ()
	{
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}
}
