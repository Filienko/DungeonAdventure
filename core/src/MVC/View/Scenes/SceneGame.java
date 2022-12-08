package MVC.View.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Room;
import MVC.Model.Physics.Vec2;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SceneGame extends Scene
{
    private Hero myHero;
    private boolean myDrawTextures;
    private boolean myDrawBoundingBoxes;

    public SceneGame(GameEngine game, String hero)
    {
        super(game);
        myEntityFactory = new EntityFactory(myRenderer.getAssets(), hero);
        initialize();
        myDrawTextures = true;
        myDrawBoundingBoxes = false;

        registerAction(Input.Keys.ESCAPE, "PAUSE");
        registerAction(Input.Keys.W, "UP");
        registerAction(Input.Keys.S, "DOWN");
        registerAction(Input.Keys.A, "LEFT");
        registerAction(Input.Keys.D, "RIGHT");
        registerAction(Input.Buttons.LEFT, "ATTACK");
        registerAction(Input.Keys.SPACE, "ATTACK");
        registerAction(Input.Keys.T, "TOGGLE_TEXTURE");
        registerAction(Input.Keys.B, "TOGGLE_BOXES");

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
            else if (action.getName().equals("TOGGLE_TEXTURE"))     { myDrawTextures = !myDrawTextures; }
            else if (action.getName().equals("TOGGLE_BOXES"))       { myDrawBoundingBoxes = !myDrawBoundingBoxes; }
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
            if (myHero.getHitPoints() > 0)
            {
                animation();
            }
            camera();
            myCurrentFrame++;
        }
    }

    private void camera()
    {
        Vec2 playerRoom = new Vec2();
        playerRoom.setMyX((float) Math.floor(myHero.getMyPos().getMyX() / 1216));
        playerRoom.setMyY((float) Math.floor(myHero.getMyPos().getMyY() / 704));

        myRenderer.getCamera().position.x = playerRoom.getMyX() * 1216 + 608;
        myRenderer.getCamera().position.y = playerRoom.getMyY() * 704 + 352;
    }

    private void animation()
    {
        // Build the animation name for the player based on direction and action
        String animationName = buildAnimationName();

        // Set the horizontal scale of the animation
        if (myHero.getFacing().getMyX() != 0)
        {
            myHero.getMyAnimation().getSprite().setScale(myHero.getFacing().getMyX(), 1);
        }
        else
        {
            myHero.getMyAnimation().getSprite().setScale(1, 1);
        }

        // if the build animation is different from the current then replace the current
        if (!myHero.getMyAnimation().getName().equals(animationName))
        {
            myHero.setMyAnimation(myRenderer.getAssets().getAnimation(animationName));
        }
    }

    private String buildAnimationName()
    {
        StringBuilder animationName = new StringBuilder("");
        if (myHero.getAttackStatus())
        {
            animationName.append("attack");
        }
        else if (myHero.getVelocity().getMyX() != 0 || myHero.getVelocity().getMyY() != 0)
        {
            animationName.append("run");
        }
        else
        {
            animationName.append("stand");
        }

        if (myHero.getFacing().getMyX() != 0)
        {
            animationName.append("Right");
        }
        else
        {
            if (myHero.getFacing().getMyY() == 1)
            {
                animationName.append("Up");
            }
            else
            {
                animationName.append("Down");
            }
        }

        return animationName.toString();
    }

    public void render()
    {
        Sprite sprite;

        for (Entity e : myEntityFactory.getEntities())
        {
            if (myDrawTextures)
            {
                e.getMyAnimation().update();
                sprite = e.getMyAnimation().getSprite();
                sprite.setPosition(e.getMyPos().getMyX(), e.getMyPos().getMyY());
                sprite.setRotation(e.getRotation());
                sprite.draw(myRenderer.getSpriteBatch());
            }
            if (myDrawBoundingBoxes)
            {
                var box = myRenderer.getAssets().getAnimation("boundingBox");
                if (e.getMySize().getMyX() != 64 && e.getMySize().getMyY() != 64)
                {
                    box.getSprite().setScale(e.getMySize().getMyX() / 64, e.getMySize().getMyY() / 64);
                }
                else
                {
                    box.getSprite().setScale(1, 1);
                }

                box.getSprite().setPosition(e.getMyPos().getMyX(), e.getMyPos().getMyY());
                box.getSprite().draw(myRenderer.getSpriteBatch());
            }
            if (e.getType().equals("Hero") || e.getType().equals("Monster"))
            {
                DungeonCharacter character = (DungeonCharacter) e;
                var health = myRenderer.getAssets().getAnimation("health");
                float x = character.getMyPos().getMyX();
                float y = character.getMyPos().getMyY() + 64;
                for (int i = 0; i < character.getHitPoints(); i++)
                {
                    health.getSprite().setPosition(x, y);
                    health.getSprite().draw(myRenderer.getSpriteBatch());

                    x+= health.getSize().getMyX();
                }
            }

        }


    }
}
