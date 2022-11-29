package MVC.View;

import MVC.Controller.GameEngine;
import MVC.View.Scenes.SceneMenu;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class MyRenderer
{
    final private GameEngine            myGame;
    final private OrthographicCamera    myCamera;
    final private SpriteBatch           myBatch;
    final private Assets 			    myAssets;

    public MyRenderer(GameEngine game)
    {
        myGame      = game;
        myAssets 	= new Assets();
        myCamera 	= new OrthographicCamera();
        myCamera.setToOrtho(false, 1280, 768);
        myBatch = new SpriteBatch();
        myAssets.loadAssets();

    }

    public OrthographicCamera getCamera() { return myCamera; }

    public Assets getAssets() { return myAssets; }

    public SpriteBatch getSpriteBatch() { return myBatch; }

    public void render()
    {
        SceneMenu menu = (SceneMenu) myGame.getCurrentScene();
        ScreenUtils.clear(0, 0, 0.2f, 1);
        myCamera.update();
        myBatch.setProjectionMatrix(myCamera.combined);
        myBatch.begin();

        myGame.getCurrentScene().render();

        myBatch.end();
    }
}
