package MVC.View.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.Model.DungeonAdventure.DungeonCharacters.*;
import MVC.Model.DungeonItems.Dungeon;
import MVC.Model.DungeonItems.Items.Exit;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import MVC.Model.Saver.Saver;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;


public class SceneGame extends Scene
{
    /**
     * The playable character of this Scene
     */
    private Hero                    myHero;
    /**
     * Whether to draw textures
     */
    private boolean                 myDrawTextures;
    /**
     * Whether to draw bounding boxes
     */
    private boolean                 myDrawBoundingBoxes;
    /**
     * Whether to play audio
     */
    private boolean                 myMuted;
    /**
     * The order for Entities to be rendered
     */
    final private ArrayList<String> myRenderOrder;
    /**
     * The last frame that the 'skitter' audio was played
     */
    private long                    myLastSkitterFrame;
    /**
     * Whether the victory music has been played
     */
    private boolean                 myVictoryMusicPlayed;
    /**
     * Whether the game is over
     */
    private boolean                 myIsEnded;

    /**
     * Constructor that takes one argument
     * @param theGame the GameEngine that this Scene belongs to
     */
    public SceneGame(GameEngine theGame)
    {
        super(theGame);
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
        registerAction(Input.Keys.R, "TOGGLE_RESPAWN");

        myLastSkitterFrame = 0;
        myVictoryMusicPlayed = false;
        myIsEnded = false;
    }

    /**
     * Constructor that takes two arguments
     * @param theGame The GameEngine that this Scene belongs to
     * @param theHero The playable Entity for this Scene
     */
    public SceneGame(GameEngine theGame, String theHero)
    {
        this(theGame);
        myEntityFactory = new EntityFactory(myRenderer.getAssets(), theHero);
        myHero = myEntityFactory.getHero();
        initialize();
    }

    /**
     * Constructor that takes three arguments
     * @param theGame The GameEngine that this Scene belongs to
     * @param theEntityFactory An EntityFactory for this Scene to use
     */
    public SceneGame(GameEngine theGame, EntityFactory theEntityFactory)
    {
        this(theGame);
        myEntityFactory = theEntityFactory;
        myEntityFactory.setAssets(myRenderer.getAssets());
        myEntityFactory.initializeEntityFactory(myEntityFactory.getEntities());
        myHero = myEntityFactory.getHero();
    }

    /**
     * Initializes the Dungeon
     */
    private void initialize()
    {
        Dungeon testDungeon = new Dungeon(myEntityFactory,3);
        myEntityFactory.generateGameEntities(testDungeon);
    }

    @Override
    protected void onEnd()
    {
        if (!myIsEnded)
        {
            (new Saver()).saveTheGame(myEntityFactory);
        }
        myRenderer.getCamera().position.x = 608;
        myRenderer.getCamera().position.y = 352;
        Exit.clean();
        stopMusic();
        myGame.setCurrentScene("Menu", null, true);
    }

    @Override
    public  void doAction(final Action theAction)
    {
        if (theAction.getType().equals("START"))
        {
                 if (theAction.getName().equals("PAUSE"))              { if (!myIsEnded) { setPaused(); }}
            else if (theAction.getName().equals("QUIT"))               { if (!myPaused && !myIsEnded) {setPaused(); } else { onEnd(); }}
            else if (theAction.getName().equals("UP"))                 { myHero.setUp(true); }
            else if (theAction.getName().equals("DOWN"))               { myHero.setDown(true); }
            else if (theAction.getName().equals("LEFT"))               { myHero.setLeft(true); }
            else if (theAction.getName().equals("RIGHT"))              { myHero.setRight(true); }
            else if (theAction.getName().equals("ATTACK"))             { myHero.attack(); }
            else if (theAction.getName().equals("TOGGLE_TEXTURE"))     { myDrawTextures = !myDrawTextures; }
            else if (theAction.getName().equals("TOGGLE_BOXES"))       { myDrawBoundingBoxes = !myDrawBoundingBoxes; }
            else if (theAction.getName().equals("TOGGLE_SOUND"))       { myMuted = !myMuted; }
            else if (theAction.getName().equals("TOGGLE_RESPAWN"))     { myHero.toggleRespawn(); }
        }
        else if (theAction.getType().equals("END"))
        {
                 if (theAction.getName().equals("UP"))     { myHero.setUp(false); }
            else if (theAction.getName().equals("DOWN"))   { myHero.setDown(false); }
            else if (theAction.getName().equals("LEFT"))   { myHero.setLeft(false); }
            else if (theAction.getName().equals("RIGHT"))  { myHero.setRight(false); }
        }
    }

    @Override
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
            myCurrentFrame++;
        }
        music();
    }

    /**
     * Updates the camera position
     */
    private void camera()
    {
        Vec2 playerRoom = new Vec2();
        playerRoom.setMyX((float) Math.floor(myHero.getMyPos().getMyX() / 1216));
        playerRoom.setMyY((float) Math.floor(myHero.getMyPos().getMyY() / 704));

        myRenderer.getCamera().position.x = playerRoom.getMyX() * 1216 + 608;
        myRenderer.getCamera().position.y = playerRoom.getMyY() * 704 + 352;
    }

    /**
     * Updates the Hero's animation
     */
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

        // if the new animation is different from the current then replace the current
        if (!myHero.getMyAnimation().getName().equals(animationName))
        {
            myHero.setMyAnimation(myRenderer.getAssets().getAnimation(animationName));
        }

        myHero.getMyAnimation().setSpeed( 15 - (long) (myHero.getMaxSpeed() - 5));
    }

    /**
     * Builds the name of the animation for the Hero to be updated with based on Hero behavior
     * @return The name of the animation
     */
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


    @Override
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
            renderEndScreen(true);
        }
        else if (!myHero.getActiveStatus())
        {
            renderEndScreen(false);
        }
    }

    /**
     * Renders the texture of the passed Entity
     * @param theEntity The Entity for which a texture is rendered
     */
    private void renderTextures(final Entity theEntity)
    {
        Sprite sprite;
        theEntity.getMyAnimation().update();
        sprite = theEntity.getMyAnimation().getSprite();
        theEntity.getMyAnimation().setPos(theEntity.getMyPos().getMyX(), theEntity.getMyPos().getMyY());
        sprite.setRotation(theEntity.getRotation());

        if (theEntity.getMyAnimation().getName().equals("exit"))
        {
            if (!Exit.isExitCondition())
            {
                sprite.setAlpha(0.25f);
            }
            else
            {
                sprite.setAlpha(1f);
            }
        }
        else if (theEntity.getMyAnimation().getName().equals("rat"))
        {
            Monster rat = (Monster) theEntity;
            double angle = Math.atan2(rat.getVelocity().getMyY(), rat.getVelocity().getMyX()) * 57.296 + 90;
            sprite.setRotation((float) angle);
        }
        else if (theEntity.getType().equals("Monster"))
        {
            if (((DungeonCharacter) theEntity).isKnockBack())
            {
                float v = ((DungeonCharacter) theEntity).getVelocity().getMagnitudeSquared();
                if (v == 0)
                {
                    sprite.setColor(0.58f, 0f, 0.83f, 0.5f);
                }
                else if (v >= 895 && v <= 905)
                {
                    sprite.setColor(1f, 0f, 0f, 0.5f);
                }
            }
            else if (((DungeonCharacter) theEntity).isInvincibility())
            {
                sprite.setColor(1, 1, 1, 0.5f);
            }
            else
            {
                sprite.setColor(1, 1, 1, 1);
            }
        }
        else if (theEntity.getType().equals("Hero"))
        {
            if (((DungeonCharacter) theEntity).isBurning())
            {
                sprite.setColor(1, .325f, .286f, 0.5f);
            }
            else if (((DungeonCharacter) theEntity).isInvincibility())
            {
                sprite.setColor(1, 1, 1, 0.5f);
            }
            else
            {
                sprite.setColor(1, 1, 1, 1);
            }
        }
        else if (theEntity.getType().equals("Worm"))
        {
            Worm w = (Worm) theEntity;
            double angle = Math.atan2(w.getVelocity().getMyY(), w.getVelocity().getMyX()) * 57.296 + 135;
            sprite.setRotation((float) angle);
            sprite.setColor(determineBossBodyColor(w));
        }
        else if (theEntity.getType().equals("Body") || theEntity.getType().equals("Tail"))
        {
            Vec2 vector = theEntity.getMyPos().minus(theEntity.getMyPreviousPos());
            double angle = Math.atan2(vector.getMyY(), vector.getMyX()) * 57.296 + 135;
            sprite.setRotation((float) angle);

            Worm w;
            if (theEntity.getType().equals("Body"))
            {
                w = ((Worm.Body) theEntity).getHead();
            }
            else
            {
                w = ((Worm.Tail) theEntity).getHead();
            }

            sprite.setColor(determineBossBodyColor(w));
        }
        sprite.draw(myRenderer.getSpriteBatch());
    }

    /**
     * Determines the color to render the boss Worm's body
     * @param theWorm The Worm to be colored
     * @return The Color to render the Worm
     */
    private Color determineBossBodyColor(final Worm theWorm)
    {
        if (theWorm.isKnockBack())
        {
            long remainder = (theWorm.getKnockBackEndFrame() - theWorm.getCurrentFrame()) % 6;
            if (remainder < 3)
            {
                return Color.RED;
            }
            else if (remainder >= 3)
            {
                return Color.GREEN;
            }
        }

        return Color.WHITE;

    }

    /**
     * Renders the bounding box of the passed Entity
     * @param theEntity The Entity for which a bounding box is rendered
     */
    private void renderBoundingBoxes(final Entity theEntity)
    {
        var box = myRenderer.getAssets().getAnimation("boundingBox");
        if (theEntity.getMySize().getMyX() != 64 && theEntity.getMySize().getMyY() != 64)
        {
            box.getSprite().setScale(theEntity.getMySize().getMyX() / 64, theEntity.getMySize().getMyY() / 64);
        }
        else
        {
            box.getSprite().setScale(1, 1);
        }

        box.getSprite().setPosition(theEntity.getMyPos().getMyX(), theEntity.getMyPos().getMyY());
        box.getSprite().draw(myRenderer.getSpriteBatch());

        var pos = myRenderer.getAssets().getAnimation("pos");
        pos.getSprite().setPosition(theEntity.getMyPos().getMyX(), theEntity.getMyPos().getMyY());
        pos.getSprite().draw(myRenderer.getSpriteBatch());
    }

    /**
     * Renders the health bars of the passed Entity
     * @param theEntity The Entity for which health bars are rendered
     */
    private void renderHealthBars(final Entity theEntity)
    {
        DungeonCharacter character = (DungeonCharacter) theEntity;
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

    /**
     * Renders the healing aura of the Priestess
     * @param theHero The Priestess for which a healing aura is rendered
     */
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

    /**
     * Render the screen for when the game is paused
     */
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
                TOGGLE SOUND:M
                TOGGLE RESPAWN:R
                RESPAWN ==""", pixelPos.getMyX(), pixelPos.getMyY());

        pixelPos = Physics.getPosition(roomX, roomY, 7, 6);
        String respawn = "FALSE";
        font = myRenderer.getAssets().getFont("mario24Red");
        if (myHero.isRespawn())
        {
            respawn = "TRUE";
            font = myRenderer.getAssets().getFont("mario24Green");
        }
        font.draw(myRenderer.getSpriteBatch(), respawn, pixelPos.getMyX(), pixelPos.getMyY());
    }

    /**
     * Plays the appropriate sounds for the Entity
     * @param theEntity The Entity for which the sounds are played
     */
    private void sounds(final Entity theEntity)
    {
        if (theEntity.getType().equals("Monster"))
        {
            Monster mon = (Monster) theEntity;
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
        else if (theEntity.getType().equals("Worm"))
        {
            Worm w = (Worm) theEntity;

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
        else if (theEntity.getType().equals("Hero"))
        {
            Hero h = (Hero) theEntity;
            if (h.getLastDamageFrame() == h.getCurrentFrame())
            {
                if (h.getDied())
                {
                    myRenderer.getAssets().getSound("linkDie").play(.5f);
                }
                else if (h.getHitPoints() > 0)
                {
                    myRenderer.getAssets().getSound("linkHurt").play(.5f);
                }
            }
        }
        else if (theEntity.getType().equals("Sword"))
        {
            if (theEntity.getCurrentFrame() == 1)
            {
                myRenderer.getAssets().getSound("slash").play(.5f);
            }
        }
        else if (theEntity.getType().contains("Potion"))
        {
            if (!theEntity.getActiveStatus())
            {
                myRenderer.getAssets().getSound("getItem").play(.5f);
            }
        }
    }

    /**
     * Renders the screen if the game is over
     * @param theIsVictory Whether the game ended with success or fail
     */
    private void renderEndScreen(final boolean theIsVictory)
    {
        myIsEnded = true;

        var shade = myRenderer.getAssets().getAnimation("pause");
        int roomX = (int) Math.floor(myHero.getMyPos().getMyX() / 1216);
        int roomY = (int) Math.floor(myHero.getMyPos().getMyY() / 704);
        Vec2 pixelPos = Physics.getPosition(roomX, roomY, 0, 0);
        shade.getSprite().setPosition(pixelPos.getMyX(), pixelPos.getMyY());
        shade.getSprite().setAlpha(.5f);
        shade.getSprite().draw(myRenderer.getSpriteBatch());

        pixelPos = Physics.getPosition(roomX, roomY, 6, 7);

        BitmapFont font = myRenderer.getAssets().getFont("mario128Red");
        String message = "YOU DIED";
        if (theIsVictory)
        {
            font = myRenderer.getAssets().getFont("mario128Green");
            message = "VICTORY!";
        }
        font.draw(myRenderer.getSpriteBatch(), message, pixelPos.getMyX(), pixelPos.getMyY());

        pixelPos = Physics.getPosition(roomX, roomY, 5, 5);
        font = myRenderer.getAssets().getFont("mario24");
        font.draw(myRenderer.getSpriteBatch(), "PRESS ESC TO RETURN TO MENU", pixelPos.getMyX(), pixelPos.getMyY());
    }

    /**
     * Plays the appropriate music for the game
     */
    private void music()
    {
        if (!myEntityFactory.getEntities("worm").isEmpty())
        {
            Worm w = (Worm) myEntityFactory.getEntities("worm").get(0);
            if (w.getHitPoints() > 0 &&
                    Physics.getRoom(myHero.getMyPos().getMyX(), myHero.getMyPos().getMyY())
                            .equals(Physics.getRoom(w.getMyPos().getMyX(), w.getMyPos().getMyY())))
            {
                if (!myRenderer.getAssets().getMusic("boss").isLooping())
                {
                    myRenderer.getAssets().getMusic("boss").setLooping(true);
                    myRenderer.getAssets().getMusic("boss").play();
                }
            }
            else if (w.getHitPoints() <= 0 && !myVictoryMusicPlayed)
            {
                myRenderer.getAssets().getMusic("boss").stop();
                myRenderer.getAssets().getMusic("victory").setLooping(false);
                myRenderer.getAssets().getMusic("victory").play();
                myVictoryMusicPlayed = true;
            }
            setMusicVolume("boss");
            setMusicVolume("victory");
        }

    }

    /**
     * Sets the volume appropriately for the passed music
     * @param theMusicName The name of the music for which the volume is set
     */
    private void setMusicVolume(final String theMusicName)
    {
        if (myMuted)
        {
            myRenderer.getAssets().getMusic(theMusicName).setVolume(0f);
        }
        else if (myPaused)
        {
            myRenderer.getAssets().getMusic(theMusicName).setVolume(.05f);
        }
        else
        {
            myRenderer.getAssets().getMusic(theMusicName).setVolume(.5f);
        }
    }

    /**
     * Stops all music that may be playing
     */
    private void stopMusic()
    {
        myRenderer.getAssets().getMusic("boss").stop();
        myRenderer.getAssets().getMusic("victory").stop();
    }

}
