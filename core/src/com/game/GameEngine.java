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
	private Renderer					myView;
	private boolean 					myRunning;

	// Methods

	void userInput()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.W))
		{
			getCurrentScene().doAction();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A))
		{

		}
		if (Gdx.input.isKeyPressed(Input.Keys.S))
		{

		}
		if (Gdx.input.isKeyPressed(Input.Keys.D))
		{

		}
		if (Gdx.input.isKeyPressed(Input.Buttons.LEFT))
		{

		}
		if (Gdx.input.isKeyPressed(Input.Buttons.RIGHT))
		{

		}
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

	Scene getCurrentScene() { return mySceneMap.get(myCurrentScene); }

	boolean sceneExists(final String sceneName) { return mySceneMap.get(sceneName) != null; }

	OrthographicCamera getMyCamera() { return myCamera; }

	Assets getMyAssets() { return myAssets; }

	boolean isRunning() { return myRunning; }

	@Override
	public void create ()
	{

		myAssets 	= new Assets();
		mySceneMap 	= new ObjectMap<String, Scene>();
		myRunning 	= true;
		myCamera = new OrthographicCamera();
		myCamera.setToOrtho(false, 1280, 768);

		batch = new SpriteBatch();

		myAssets.loadAssets();

		setCurrentScene("Menu", new SceneMenu());
	}

	@Override
	public void render ()
	{
		if (!myRunning) 			{ return; }
		if (mySceneMap.isEmpty()) 	{ return; }

		userInput();
		getCurrentScene().update();

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
	}
	
	@Override
	public void dispose ()
	{
		// Dispose all Textures

		// Dispose batches
		batch.dispose();
	}
}
