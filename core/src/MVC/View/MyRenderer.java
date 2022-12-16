package MVC.View;

import MVC.Controller.GameEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class MyRenderer
{
    /**
     * The GameEngine that this belongs to
     */
    final private GameEngine            myGame;
    /**
     * The libgdx Orthographic Camera used to draw the game
     */
    final private OrthographicCamera    myCamera;
    /**
     * The libgdx SpriteBatch used to draw all textures
     */
    final private SpriteBatch           myBatch;
    /**
     * The Asset class that stores all the assets
     */
    final private Assets 			    myAssets;

    /**
     * Constructor that takes one argument
     * @param game The GameEngine that this belongs to
     */
    public MyRenderer(GameEngine game)
    {
        myGame      = game;
        myAssets 	= new Assets();
        myCamera 	= new OrthographicCamera();
        myCamera.setToOrtho(false, 1216, 704);
        myBatch = new SpriteBatch();
        myAssets.loadAssets();

    }

    /**
     * @return The libgdx OrthographicCamera used to render the game
     */
    public OrthographicCamera getCamera() { return myCamera; }

    /**
     * @return The Asset instance that stores all the assets
     */
    public Assets getAssets() { return myAssets; }

    /**
     * @return The libgdx SpriteBatch used to draw all textures
     */
    public SpriteBatch getSpriteBatch() { return myBatch; }

    /**
     * Renders the game
     */
    public void render()
    {
        ScreenUtils.clear(.729f, .659f, .435f, 1);
        myCamera.update();
        myBatch.setProjectionMatrix(myCamera.combined);
        myBatch.begin();

        myGame.getCurrentScene().render();

        myBatch.end();
    }
}
