package MVC.View.Scenes;

import MVC.Controller.Action;
import MVC.Controller.GameEngine;
import MVC.Model.Saver.Saver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class SceneMenu extends Scene
{
    /**
     * The menu element that is currently highlighted to be selected
     */
    private int myMenuIndex;
    /**
     * The text elements to be drawn corresponding to the various menu options
     */
    final private ArrayList<String> myTextElements;
    /**
     * The hero selections to be drawn for hero selection
     */
    final private ArrayList<String> myHeroSelections;
    /**
     * Whether the Hero selection screen is to be drawn
     */
    boolean myHeroSelection;

    /**
     * Constructor that takes one argument
     * @param theGameEngine The GameEngine that this Scene belongs to
     */
    public SceneMenu(GameEngine theGameEngine)
    {
        super(theGameEngine);
        myTitle = "Dungeon Adventure";
        myTextElements = new ArrayList<String>();
        myHeroSelections = new ArrayList<String>();

        registerAction(Input.Keys.W, "UP");
        registerAction(Input.Keys.S, "DOWN");
        registerAction(Input.Keys.D, "SELECT");
        registerAction(Input.Keys.ESCAPE, "BACK");

        myTextElements.add("NEW GAME");
        myTextElements.add("LOAD GAME");
        myTextElements.add("QUIT");

        myHeroSelections.add("Priestess");
        myHeroSelections.add("Warrior");
        myHeroSelections.add("Thief");
        myHeroSelection = false;

        myMenuIndex = 0;

    }

    @Override
    protected void onEnd()  { Gdx.app.exit(); }

    @Override
    public void update()    { myCurrentFrame++; }

    @Override
    public void doAction(final Action theAction)
    {
        if(theAction.getType().equals("START"))
        {
            switch (theAction.getName()) {
                case "UP":
                    if (myMenuIndex > 0) {
                        myMenuIndex--;
                    } else {
                        myMenuIndex = myTextElements.size() - 1;
                    }
                    break;
                case "DOWN":
                    myMenuIndex = (myMenuIndex + 1) % myTextElements.size();
                    break;
                case "SELECT":
                    if (myHeroSelection)
                    {
                        myHeroSelection = false;
                        myGame.setCurrentScene("Dungeon",
                                new SceneGame(myGame, myHeroSelections.get(myMenuIndex)), false);
                    }
                    else if (myTextElements.get(myMenuIndex).equals("NEW GAME"))
                    {
                        myHeroSelection = true;
                        System.out.println("Selected: 'New Game'");
                    }
                    else if (myTextElements.get(myMenuIndex).equals("LOAD GAME"))
                    {
                        myGame.setCurrentScene("Dungeon",
                                new SceneGame(myGame, (new Saver()).loadTheGame()), false);
                        System.out.println("Selected: 'Load Game'");
                    } else if (myTextElements.get(myMenuIndex).equals("QUIT"))
                    {
                        System.out.println("Selected: 'Quit'");
                        onEnd();
                    }
                    break;
                case "BACK":
                    if (myHeroSelection) { myHeroSelection = false; }
                    break;
            }

            System.out.println("Indexed at " + myTextElements.get(myMenuIndex));
        }
    }

    @Override
    public void render()
    {

        SpriteBatch batch = myRenderer.getSpriteBatch();
        BitmapFont font = myRenderer.getAssets().getFont("mario48");
        ArrayList<String> text;
        String header;
        if (myHeroSelection)
        {
            text = myHeroSelections;
            header = "Select a Hero!";
        }
        else
        {
            text = myTextElements;
            header = myTitle;
        }

        font.draw(batch, header, 5, 686);

        int yPosition = 686;
        for (int i = 0; i < text.size(); i++)
        {
            yPosition-= 80;
            if (i ==  myMenuIndex)
            {
                font = myRenderer.getAssets().getFont("mario40Black");
            }
            else
            {
                font = myRenderer.getAssets().getFont("mario40");
            }

            font.draw(batch, text.get(i), 5, yPosition);
        }

        font = myRenderer.getAssets().getFont("mario24");
        font.draw(batch, "UP:W DOWN:S SELECT:D BACK:ESC", 5, 64);
    }

    /**
     * @return The element of the menu that is highlighted for selection
     */
    public int getMenuIndex() { return myMenuIndex; }
}