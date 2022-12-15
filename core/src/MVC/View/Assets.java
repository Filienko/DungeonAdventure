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
    // Member fields
    private final ObjectMap<String, Texture>    myTextureMap;
    private final ObjectMap<String, Animation>  myAnimationMap;
    private final ObjectMap<String, BitmapFont> myFontMap;
    private final ObjectMap<String, Sound>      mySoundMap;
    private final ObjectMap<String, Music>      myMusicMap;
    private final ArrayList<String>             myAnimationNames;

    // Constructor
    public Assets()
    {
        myTextureMap        = new ObjectMap<String, Texture>();
        myAnimationMap      = new ObjectMap<String, Animation>();
        myFontMap           = new ObjectMap<String, BitmapFont>();
        mySoundMap          = new ObjectMap<String, Sound>();
        myMusicMap          = new ObjectMap<String, Music>();
        myAnimationNames    = new ArrayList<String>();
    }

    // Methods
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

    private void addAnimation(final String theAnimationName, int theFrameCount, int theSpeed)
    {
        myAnimationMap.put(theAnimationName, new Animation(theAnimationName, getTexture(theAnimationName), theFrameCount, theSpeed));
    }

    private void addFont(final String theFontName, final String theFileName)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(theFileName));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;
        parameter.color = Color.CHARTREUSE;
        myFontMap.put(theFontName + "48", generator.generateFont(parameter));

        parameter.size = 62;
        myFontMap.put(theFontName + "128", generator.generateFont(parameter));

        parameter.size = 24;
        parameter.color = Color.WHITE;
        myFontMap.put(theFontName + "24", generator.generateFont(parameter));

        parameter.size = 40;
        myFontMap.put(theFontName + "40", generator.generateFont(parameter));

        parameter.color = Color.GRAY;
        myFontMap.put(theFontName + "40Black", generator.generateFont(parameter));

        generator.dispose();
    }

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

    public Texture getTexture(final String theTextureName)
    {
        assert(myTextureMap.get(theTextureName) != null);
        return myTextureMap.get(theTextureName);
    }

    public Animation getAnimation(final String theAnimationName)
    {
        assert(myAnimationMap.get(theAnimationName) != null);
        return myAnimationMap.get(theAnimationName);
    }

    public BitmapFont getFont(final String theFontName)
    {
        assert(myFontMap.get(theFontName) != null);
        return myFontMap.get(theFontName);
    }

    public Sound getSound(final String theSoundName)
    {
        assert(mySoundMap.get(theSoundName) != null);
        return mySoundMap.get(theSoundName);
    }

    public Music getMusic(final String theMusicName)
    {
        assert(myMusicMap.get(theMusicName) != null);
        return myMusicMap.get(theMusicName);
    }

    public ArrayList<String> getAnimationNames()
    {
        return myAnimationNames;
    }

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
