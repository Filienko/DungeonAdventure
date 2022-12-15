package MVC.View.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.Model.DungeonAdventure.DungeonCharacters.*;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.DungeonItems.Room;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import MVC.Model.Saver.Saver;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class SceneGame extends Scene
{
    private Hero myHero;
    private boolean myDrawTextures;
    private boolean myDrawBoundingBoxes;
    private boolean myMuted;
    private ArrayList<String> myRenderOrder;
    private long myLastSkitterFrame;

    public SceneGame(GameEngine game)
    {
        super(game);
        myDrawTextures = true;
        myDrawBoundingBoxes = false;
        myMuted = false;

        myRenderOrder = new ArrayList<>();
        myRenderOrder.add("Lava");
        myRenderOrder.add("Wall");
        myRenderOrder.add("Door");
        myRenderOrder.add("Exit");
        myRenderOrder.add("attackPotion");
        myRenderOrder.add("healthPotion");
        myRenderOrder.add("speedPotion");
        myRenderOrder.add("Monster");
        myRenderOrder.add("Tail");
        myRenderOrder.add("Body");
        myRenderOrder.add("Worm");
        myRenderOrder.add("Hero");
        myRenderOrder.add("Sword");
        myRenderOrder.add("Pillar");

        registerAction(Input.Keys.P, "PAUSE");
        registerAction(Input.Keys.ESCAPE, "QUIT");
        registerAction(Input.Keys.W, "UP");
        registerAction(Input.Keys.S, "DOWN");
        registerAction(Input.Keys.A, "LEFT");
        registerAction(Input.Keys.D, "RIGHT");
        registerAction(Input.Buttons.LEFT, "ATTACK");
        registerAction(Input.Keys.SPACE, "ATTACK");
        registerAction(Input.Keys.T, "TOGGLE_TEXTURE");
        registerAction(Input.Keys.B, "TOGGLE_BOXES");
        registerAction(Input.Keys.M, "TOGGLE_SOUND");

        myLastSkitterFrame = 0;
    }

    public SceneGame(GameEngine game, String hero)
    {
        this(game);
        myEntityFactory = new EntityFactory(myRenderer.getAssets(), hero);
        myHero = myEntityFactory.getHero();
        initialize();
    }

    public SceneGame(GameEngine game, EntityFactory theEntityFactory)
    {
        this(game);
        myEntityFactory = theEntityFactory;
        myEntityFactory.setAssets(myRenderer.getAssets());
        myEntityFactory.initializeEntityFactory(myEntityFactory.getEntities());
        myHero = myEntityFactory.getHero();
    }

    private void initialize()
    {
        Dungeon testDungeon = new Dungeon(myEntityFactory,3);
        myEntityFactory.generateGameEntities(testDungeon);
    }

    protected void onEnd()
    {
        // TODO: serialize
        (new Saver()).saveTheGame(myEntityFactory);
        myRenderer.getCamera().position.x = 608;
        myRenderer.getCamera().position.y = 352;
        myGame.setCurrentScene("Menu", null, true);
    }

    public  void doAction(final Action action)
    {
        if (action.getType().equals("START"))
        {
                 if (action.getName().equals("PAUSE"))              { if (!Exit.isExited()) { setPaused(); }}
            else if (action.getName().equals("QUIT"))               { if (!myPaused && !Exit.isExited()) {setPaused(); } else { onEnd(); }}
            else if (action.getName().equals("UP"))                 { myHero.setUp(true); }
            else if (action.getName().equals("DOWN"))               { myHero.setDown(true); }
            else if (action.getName().equals("LEFT"))               { myHero.setLeft(true); }
            else if (action.getName().equals("RIGHT"))              { myHero.setRight(true); }
            else if (action.getName().equals("ATTACK"))             { myHero.attack(); }
            else if (action.getName().equals("TOGGLE_TEXTURE"))     { myDrawTextures = !myDrawTextures; }
            else if (action.getName().equals("TOGGLE_BOXES"))       { myDrawBoundingBoxes = !myDrawBoundingBoxes; }
            else if (action.getName().equals("TOGGLE_SOUND"))       { myMuted = !myMuted; }
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

        if (!myPaused && !Exit.isExited())
        {
            myEntityFactory.update();
            if (myHero.getHitPoints() > 0)
            {
                animation();
            }
            camera();
            music();
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
        for (String k : myRenderOrder)
        {
            for (Entity e : myEntityFactory.getEntities(k))
            {
                if (myDrawTextures && e.getMyAnimation() != null)
                {
                    renderTextures(e);
                }
                if (myDrawBoundingBoxes)
                {
                    renderBoundingBoxes(e);
                }
                if (e.getType().equals("Hero"))
                {
                    renderHealthBars(e);
                    renderAura((Hero) e);
                }
                else if ( e.getType().equals("Monster") || e.getType().equals("Worm"))
                {
                    renderHealthBars(e);
                }
                if (!myMuted)
                {
                    sounds(e);
                }
            }
        }

        if (myPaused)
        {
            renderPaused();
        }
        else if (Exit.isExited())
        {
            renderEndScreen();
        }
    }

    private void renderTextures(final Entity e)
    {
        Sprite sprite;
        e.getMyAnimation().update();
        sprite = e.getMyAnimation().getSprite();
        e.getMyAnimation().setPos(e.getMyPos().getMyX(), e.getMyPos().getMyY());
        sprite.setRotation(e.getRotation());

        if (e.getMyAnimation().getName().equals("exit"))
        {
            if (!((Exit) e).checkFinishGame())
            {
                sprite.setAlpha(0.25f);
            }
            else
            {
                sprite.setAlpha(1f);
            }
        }
        else if (e.getMyAnimation().getName().equals("rat"))
        {
            Monster rat = (Monster) e;
            double angle = Math.atan2(rat.getVelocity().getMyY(), rat.getVelocity().getMyX()) * 57.296 + 90;
            sprite.setRotation((float) angle);
        }
        else if (e.getType().equals("Monster"))
        {
            if (((DungeonCharacter) e).isKnockback())
            {
                float v = ((DungeonCharacter) e).getVelocity().getMagnitudeSquared();
                if (v == 0)
                {
                    sprite.setColor(0.58f, 0f, 0.83f, 0.5f);
                }
                else if (v >= 895 && v <= 905)
                {
                    sprite.setColor(1f, 0f, 0f, 0.5f);
                }
            }
            else if (((DungeonCharacter) e).isInvincibility())
            {
                sprite.setColor(1, 1, 1, 0.5f);
            }
            else
            {
                sprite.setColor(1, 1, 1, 1);
            }
        }
        else if (e.getType().equals("Hero"))
        {
            if (((DungeonCharacter) e).isBurning())
            {
                sprite.setColor(1, .325f, .286f, 0.5f);
            }
            else if (((DungeonCharacter) e).isInvincibility())
            {
                sprite.setColor(1, 1, 1, 0.5f);
            }
            else
            {
                sprite.setColor(1, 1, 1, 1);
            }
        }
        else if (e.getType().equals("Worm"))
        {
            Worm w = (Worm) e;
            double angle = Math.atan2(w.getVelocity().getMyY(), w.getVelocity().getMyX()) * 57.296 + 135;
            sprite.setRotation((float) angle);
            if (((Worm) e).isKnockback())
            {
                long remainder = (((Worm) e).getKnockbackEndFrame() - e.getCurrentFrame()) % 6;
                if (remainder < 3)
                {
                    sprite.setColor(Color.RED);
                }
                else if (remainder >= 3)
                {
                    sprite.setColor(Color.GREEN);
                }
            }
            else
            {
                sprite.setColor(Color.WHITE);
            }
        }
        else if (e.getType().equals("Body") || e.getType().equals("Tail"))
        {
            Vec2 vector = e.getMyPos().minus(e.getMyPreviousPos());
            double angle = Math.atan2(vector.getMyY(), vector.getMyX()) * 57.296 + 135;
            sprite.setRotation((float) angle);

            Worm w;
            if (e.getType().equals("Body"))
            {
                w = ((Worm.Body) e).getHead();
            }
            else
            {
                w = ((Worm.Tail) e).getHead();
            }
            if (w.isKnockback())
            {
                long remainder = (w.getKnockbackEndFrame() - w.getCurrentFrame()) % 6;
                if (remainder < 3)
                {
                    sprite.setColor(Color.RED);
                }
                else if (remainder >= 3)
                {
                    sprite.setColor(Color.GREEN);
                }
            }
            else
            {
                sprite.setColor(Color.WHITE);
            }
        }
        sprite.draw(myRenderer.getSpriteBatch());
    }

    private void renderBoundingBoxes(final Entity e)
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

        var pos = myRenderer.getAssets().getAnimation("pos");
        pos.getSprite().setPosition(e.getMyPos().getMyX(), e.getMyPos().getMyY());
        pos.getSprite().draw(myRenderer.getSpriteBatch());
    }

    private void renderHealthBars(final Entity e)
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

    private void renderAura(final Hero theHero)
    {
        if (theHero.getUsingSpecial())
        {
            if (theHero.getCharacterType().equals("Priestess"))
            {
                var aura = myRenderer.getAssets().getAnimation("aura");
                aura.setPos(theHero.getMyPos().getMyX(), theHero.getMyPos().getMyY());
                aura.getSprite().setAlpha(0.5f);
                aura.getSprite().draw(myRenderer.getSpriteBatch());
            }
        }
    }

    private void renderPaused()
    {
        int roomX = (int) Math.floor(myHero.getMyPos().getMyX() / 1216);
        int roomY = (int) Math.floor(myHero.getMyPos().getMyY() / 704);
        var shade = myRenderer.getAssets().getAnimation("pause");
        Vec2 pixelPos = Physics.getPosition(roomX, roomY, 0, 0);
        shade.getSprite().setPosition(pixelPos.getMyX(), pixelPos.getMyY());
        shade.getSprite().setAlpha(.5f);
        shade.getSprite().draw(myRenderer.getSpriteBatch());

        pixelPos = Physics.getPosition(roomX, roomY, 3, 9);
        BitmapFont font = myRenderer.getAssets().getFont("mario24");
        font.draw(myRenderer.getSpriteBatch(), """
                UP:W DOWN:S LEFT:A RIGHT:D
                ATTACK:SPACE
                PAUSE:P
                SAVE AND QUIT:ESC
                TOGGLE TEXTURES:T
                TOGGLE BOUNDING BOXES:B
                TOGGLE SOUND:M""", pixelPos.getMyX(), pixelPos.getMyY());
    }

    private void sounds(final Entity e)
    {
        if (e.getType().equals("Monster"))
        {
            Monster mon = (Monster) e;
            if (mon.getLastDamageFrame() == mon.getCurrentFrame())
            {
                if (mon.getHitPoints() > 0)
                {
                    myRenderer.getAssets().getSound("enemyHit").play(.5f);
                }
                else
                {
                    myRenderer.getAssets().getSound("enemyDie").play(.5f);
                }
            }
        }
        else if (e.getType().equals("Worm"))
        {
            Worm w = (Worm) e;
            if (w.isSpawned())
            {
                if (w.getLastDamageFrame() == w.getCurrentFrame())
                {
                    myRenderer.getAssets().getSound("bossHit").play(.5f);
                }
                if (w.getCurrentFrame() >= myLastSkitterFrame + 16 &&
                        Physics.getRoom(myHero.getMyPos().getMyX(), myHero.getMyPos().getMyY())
                                .equals(Physics.getRoom(w.getMyPos().getMyX(), w.getMyPos().getMyY())))
                {
                    myRenderer.getAssets().getSound("bossSkitter").play(.5f);
                    myLastSkitterFrame = w.getCurrentFrame() + 16;
                }
            }
        }
        else if (e.getType().equals("Hero"))
        {
            Hero h = (Hero) e;
            if (h.getLastDamageFrame() == h.getCurrentFrame())
            {
                if (h.getHitPoints() > 0)
                {
                    myRenderer.getAssets().getSound("linkHurt").play(.5f);
                }
                else
                {
                    myRenderer.getAssets().getSound("linkDie").play(.5f);
                }
            }
        }
        else if (e.getType().equals("Sword"))
        {
            if (e.getCurrentFrame() == 1)
            {
                myRenderer.getAssets().getSound("slash").play(.5f);
            }
        }
        else if (e.getType().contains("Potion"))
        {
            if (!e.getActiveStatus())
            {
                myRenderer.getAssets().getSound("getItem").play(.5f);
            }
        }
    }

    private void renderEndScreen()
    {
        var shade = myRenderer.getAssets().getAnimation("pause");
        int roomX = (int) Math.floor(myHero.getMyPos().getMyX() / 1216);
        int roomY = (int) Math.floor(myHero.getMyPos().getMyY() / 704);
        Vec2 pixelPos = Physics.getPosition(roomX, roomY, 0, 0);
        shade.getSprite().setPosition(pixelPos.getMyX(), pixelPos.getMyY());
        shade.getSprite().setAlpha(.5f);
        shade.getSprite().draw(myRenderer.getSpriteBatch());

        pixelPos = Physics.getPosition(roomX, roomY, 6, 7);
        BitmapFont font = myRenderer.getAssets().getFont("mario128");
        font.draw(myRenderer.getSpriteBatch(), "VICTORY!", pixelPos.getMyX(), pixelPos.getMyY());

        pixelPos = Physics.getPosition(roomX, roomY, 5, 5);
        font = myRenderer.getAssets().getFont("mario24");
        font.draw(myRenderer.getSpriteBatch(), "PRESS ESC TO RETURN TO MENU", pixelPos.getMyX(), pixelPos.getMyY());
    }

    private void music()
    {
        if (!myEntityFactory.getEntities("worm").isEmpty())
        {
            Worm w = (Worm) myEntityFactory.getEntities("worm").get(0);
            if (w.isSpawned() && w.getHitPoints() > 0 &&
                    Physics.getRoom(myHero.getMyPos().getMyX(), myHero.getMyPos().getMyY())
                            .equals(Physics.getRoom(w.getMyPos().getMyX(), w.getMyPos().getMyY())))
            {
                if (!myRenderer.getAssets().getMusic("boss").isPlaying())
                {
                    myRenderer.getAssets().getMusic("boss").setLooping(true);
                    myRenderer.getAssets().getMusic("boss").setVolume(.5f);
                    myRenderer.getAssets().getMusic("boss").play();
                }
            }
            else if (w.isSpawned() && w.getHitPoints() <= 0)
            {
                myRenderer.getAssets().getMusic("boss").stop();
                if (!myRenderer.getAssets().getMusic("victory").isPlaying())
                {
                    myRenderer.getAssets().getMusic("victory").setLooping(false);
                    myRenderer.getAssets().getMusic("victory").setVolume(.5f);
                    myRenderer.getAssets().getMusic("victory").play();
                }
            }
        }

    }

}
