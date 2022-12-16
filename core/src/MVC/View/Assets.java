package MVC.View;

import MVC.View.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ObjectMap;

import java.io.File;
import java.util.ArrayList;

public class Assets
{
    /**
     * A map of libgdx Textures
     */
    private final ObjectMap<String, Texture>    myTextureMap;
    /**
     * A map of Animations
     */
    private final ObjectMap<String, Animation>  myAnimationMap;
    /**
     * A map of text fonts
     */
    private final ObjectMap<String, BitmapFont> myFontMap;
    /**
     * A map of libgdx Sounds
     */
    private final ObjectMap<String, Sound>      mySoundMap;
    /**
     * A map of libgdx Music
     */
    private final ObjectMap<String, Music>      myMusicMap;
    /**
     * The names of all the Animations in myAnimationMap
     */
    private final ArrayList<String>             myAnimationNames;

    /**
     * Constructor that takes zero arguments
     */
    public Assets()
    {
        myTextureMap        = new ObjectMap<String, Texture>();
        myAnimationMap      = new ObjectMap<String, Animation>();
        myFontMap           = new ObjectMap<String, BitmapFont>();
        mySoundMap          = new ObjectMap<String, Sound>();
        myMusicMap          = new ObjectMap<String, Music>();
        myAnimationNames    = new ArrayList<String>();
    }

    /**
     * Loads the assets from the working directory
     */
    public void loadAssets()
    {
        File dir = new File(System.getProperty("user.dir"));
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null)
        {
            for (File child : directoryListing)
            {
                String fileName = child.getName();
                String[] attributes = fileName.split("_");
                if (fileName.contains(".png"))
                {
                    addTexture(attributes[0], fileName);

                    if (attributes.length > 2)
                    {
                        addAnimation(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
                        myAnimationNames.add(attributes[0]);
                    }
                }
                else if (fileName.contains(".ttf"))
                {
                    addFont(attributes[0], fileName);
                }
                else if (fileName.contains(".wav"))
                {
                    addSound(attributes[0], fileName);
                }
                else if (fileName.contains(".mp3"))
                {
                    addMusic(attributes[0], fileName);
                }
            }
        }
    }

    /**
     * Adds a libgdx Texture to myTextureMap
     * @param theTextureName The name of the Texture to be added
     * @param theFileName The file name of the texture
     */
    private void addTexture(final String theTextureName, final String theFileName)
    {
        myTextureMap.put(theTextureName, new Texture(Gdx.files.internal(theFileName)));
        if (myTextureMap.get(theTextureName) == null)
        {
            System.out.println("Could not load texture file: " + theFileName);
        }
        else
        {
            System.out.println("Loaded texture: " + theFileName);
        }
    }

    /**
     * Adds an Animation to myAnimationMap
     * @param theAnimationName The name of the Animation to be added
     * @param theFrameCount The total number of frames of this animation
     * @param theSpeed The speed at which to play the animation
     */
    private void addAnimation(final String theAnimationName, int theFrameCount, int theSpeed)
    {
        myAnimationMap.put(theAnimationName, new Animation(theAnimationName, getTexture(theAnimationName), theFrameCount, theSpeed));
    }

    /**
     * Adds a font to myFontMap
     * @param theFontName The name of the font to be added
     * @param theFileName The file name of the font
     */
    private void addFont(final String theFontName, final String theFileName)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(theFileName));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;
        parameter.color = Color.CHARTREUSE;
        myFontMap.put(theFontName + "48", generator.generateFont(parameter));

        parameter.size = 62;
        myFontMap.put(theFontName + "128Green", generator.generateFont(parameter));

        parameter.color = Color.RED;
        myFontMap.put(theFontName + "128Red", generator.generateFont(parameter));

        parameter.size = 24;
        parameter.color = Color.CHARTREUSE;
        myFontMap.put(theFontName + "24Green", generator.generateFont(parameter));

        parameter.color = Color.RED;
        myFontMap.put(theFontName + "24Red", generator.generateFont(parameter));

        parameter.color = Color.WHITE;
        myFontMap.put(theFontName + "24", generator.generateFont(parameter));

        parameter.size = 40;
        myFontMap.put(theFontName + "40", generator.generateFont(parameter));

        parameter.color = Color.GRAY;
        myFontMap.put(theFontName + "40Black", generator.generateFont(parameter));

        generator.dispose();
    }

    /**
     * Adds a libgdx Sound to mySoundMap
     * @param theSoundName The name of the Sound to be added
     * @param theFileName The file name of the audio
     */
    private void addSound(final String theSoundName, final String theFileName)
    {
        mySoundMap.put(theSoundName, Gdx.audio.newSound(Gdx.files.internal(theFileName)));
        if (mySoundMap.get(theSoundName) == null)
        {
            System.out.println("Could not load sound file: " + theFileName);
        }
        else
        {
            System.out.println("Loaded sound: " + theFileName);
        }
    }

    /**
     * Adds a libgdx Music to myMusicMap
     * @param theMusicName The name of the Music to be added
     * @param theFileName The file name of the music
     */
    private void addMusic(final String theMusicName, final String theFileName)
    {
        myMusicMap.put(theMusicName, Gdx.audio.newMusic(Gdx.files.internal(theFileName)));
        if (mySoundMap.get(theMusicName) == null)
        {
            System.out.println("Could not load sound file: " + theFileName);
        }
        else
        {
            System.out.println("Loaded sound: " + theFileName);
        }
    }

    /**
     * @param theTextureName The name of the libgdx Texture to be retrieved
     * @return A libgdx Texture
     */
    public Texture getTexture(final String theTextureName)
    {
        assert(myTextureMap.get(theTextureName) != null);
        return myTextureMap.get(theTextureName);
    }

    /**
     * @param theAnimationName The name of the Animation to be retrieved
     * @return An Animation
     */
    public Animation getAnimation(final String theAnimationName)
    {
        assert(myAnimationMap.get(theAnimationName) != null);
        return myAnimationMap.get(theAnimationName);
    }

    /**
     * @param theFontName The name of the font to be retrieved
     * @return A BitMapFont
     */
    public BitmapFont getFont(final String theFontName)
    {
        assert(myFontMap.get(theFontName) != null);
        return myFontMap.get(theFontName);
    }

    /**
     * @param theSoundName The name of the libgdx Sound to be retrieved
     * @return A libgdx Sound
     */
    public Sound getSound(final String theSoundName)
    {
        assert(mySoundMap.get(theSoundName) != null);
        return mySoundMap.get(theSoundName);
    }

    /**
     * @param theMusicName The name of the libgdx Music to be retrieved
     * @return A libgdx Music
     */
    public Music getMusic(final String theMusicName)
    {
        assert(myMusicMap.get(theMusicName) != null);
        return myMusicMap.get(theMusicName);
    }

    /**
     * @return The names of the Animations stored in myAnimationMap
     */
    public ArrayList<String> getAnimationNames()
    {
        return myAnimationNames;
    }

    /**
     * Tells libgdx to dispose of all the stored assets
     */
    public void dispose()
    {
        for (var k : myTextureMap.keys())
        {
            myTextureMap.get(k).dispose();
        }
        for (var k : myFontMap.keys())
        {
            myFontMap.get(k).dispose();
        }
        for (var k : mySoundMap.keys())
        {
            mySoundMap.get(k).dispose();
        }
        for (var k : myMusicMap.keys())
        {
            myMusicMap.get(k).dispose();
        }
    }
}
