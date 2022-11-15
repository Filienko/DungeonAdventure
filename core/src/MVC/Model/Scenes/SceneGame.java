package MVC.Model.Scenes;

import MVC.Controller.GameEngine;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.Input;


public class SceneGame extends Scene
{
    private Hero myHero;

    public SceneGame(GameEngine game, boolean newGame)
    {
        if (newGame)
        {
            // Call method to generate dungeon
            initialize();
        }
        else
        {
            // deserialize
            loadLevel();
        }

        registerAction(Input.Keys.ESCAPE, "PAUSE");
        registerAction(Input.Keys.W, "UP");
        registerAction(Input.Keys.S, "DOWN");
        registerAction(Input.Keys.A, "LEFT");
        registerAction(Input.Keys.D, "RIGHT");
        registerAction(Input.Buttons.LEFT, "ATTACK");
        registerAction(Input.Keys.SPACE, "ATTACK");
    }

    private void initialize() {}

    private void loadLevel() {}

    protected void onEnd() {}

    private void spawnPlayer() {}

    private Vec2 getPosition(final int rx, final int ry, final int tx, final int ty)
    {
        // This function takes in the room (rx, ry) coordinate
        // as well as the tile (tx, ty) coordinate, and returns the Vec2 game world
        // position of the center of the entity

        int pixelX = rx * 1280 + tx * 64; // room * 20 tiles * 64 pixels per tile + room tile * 64 pixels per tile
        int pixelY = ry * 768 + ty * 64; // 12 tiles in y component

        return new Vec2(pixelX, pixelY);
    }

    private void Movement() { }

    public  void doAction(final String action) {}

    private void AI() {}

    private void status() {}

    private void collisions() {}

    private void animation() {}

    private void entityTileCollisions() {}

    private void playerEnemyCollisions() {}

    private void weaponEnemyCollisions() {}

    private void playerItemCollisions() {}

    public void update() {}
}
