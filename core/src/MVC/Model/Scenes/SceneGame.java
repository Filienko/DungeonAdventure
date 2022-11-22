package MVC.Model.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.Input;


public class SceneGame extends Scene
{
    private Hero myHero;

    public SceneGame(GameEngine game)
    {
        myEntityFactory = new EntityFactory();
        initialize();

        registerAction(Input.Keys.ESCAPE, "PAUSE");
        registerAction(Input.Keys.W, "UP");
        registerAction(Input.Keys.S, "DOWN");
        registerAction(Input.Keys.A, "LEFT");
        registerAction(Input.Keys.D, "RIGHT");
        registerAction(Input.Buttons.LEFT, "ATTACK");
        registerAction(Input.Keys.SPACE, "ATTACK");

        myHero = myEntityFactory.generateWarrior();
    }

    private void initialize() {}

    protected void onEnd() {}

    private Vec2 getPosition(final int rx, final int ry, final int tx, final int ty)
    {
        // This function takes in the room (rx, ry) coordinate
        // as well as the tile (tx, ty) coordinate, and returns the Vec2 game world
        // position of the center of the entity

        int pixelX = rx * 1280 + tx * 64; // room * 20 tiles * 64 pixels per tile + room tile * 64 pixels per tile
        int pixelY = ry * 768 + ty * 64; // 12 tiles in y component

        return new Vec2(pixelX, pixelY);
    }

    public  void doAction(final Action action) {}

    public void update()
    {
        if (!myPaused)
        {
            //myEntityFactory.update();
            myCurrentFrame++;
        }
    }
}
