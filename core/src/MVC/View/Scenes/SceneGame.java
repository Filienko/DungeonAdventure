package MVC.View.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SceneGame extends Scene
{
    private Hero myHero;

    public SceneGame(GameEngine game, String hero)
    {
        super(game);
        myEntityFactory = new EntityFactory(myRenderer.getAssets(), hero);
        initialize();

        registerAction(Input.Keys.ESCAPE, "PAUSE");
        registerAction(Input.Keys.W, "UP");
        registerAction(Input.Keys.S, "DOWN");
        registerAction(Input.Keys.A, "LEFT");
        registerAction(Input.Keys.D, "RIGHT");
        registerAction(Input.Buttons.LEFT, "ATTACK");
        registerAction(Input.Keys.SPACE, "ATTACK");

        myHero = myEntityFactory.getHero();
    }

    private void initialize()
    {
        Room testRoom = new Room(0, new Vec2(0, 0));
        testRoom.setN(true);
        testRoom.setE(true);
        testRoom.setS(true);
        testRoom.setW(true);
        myEntityFactory.generateRoomEntities(testRoom);
    }

    protected void onEnd() {}

    public  void doAction(final Action action)
    {
        if (action.getType().equals("START"))
        {
                 if (action.getName().equals("PAUSE"))              { setPaused(); }
            else if (action.getName().equals("QUIT"))               { onEnd(); }
            else if (action.getName().equals("UP"))                 { myHero.setUp(true); }
            else if (action.getName().equals("DOWN"))               { myHero.setDown(true); }
            else if (action.getName().equals("LEFT"))               { myHero.setLeft(true); }
            else if (action.getName().equals("RIGHT"))              { myHero.setRight(true); }
            else if (action.getName().equals("ATTACK"))             { myHero.attack(); }
        }
        else if (action.getType().equals("END"))
        {
                 if (action.getName().equals("UP"))     { myHero.setUp(false); }
            else if (action.getName().equals("DOWN"))   { myHero.setDown(false); }
            else if (action.getName().equals("LEFT"))   { myHero.setLeft(false); }
            else if (action.getName().equals("RIGHT"))  { myHero.setRight(false); }
        }
    }

    public void update()
    {
        if (!myPaused)
        {
            myEntityFactory.update();
            myCurrentFrame++;
        }
    }

    public void render()
    {
        Sprite sprite;

        for (Entity e : myEntityFactory.getEntities())
        {
            e.getMyAnimation().update();
            sprite = e.getMyAnimation().getSprite();
            sprite.setPosition(e.getMyPos().getMyX(), e.getMyPos().getMyY());
            sprite.setRotation(e.getRotation());
            sprite.draw(myRenderer.getSpriteBatch());
        }


    }
}
