package MVC.View;

import MVC.View.Animation;
import com.badlogic.gdx.Gdx;
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
    private ObjectMap<String, Texture> myTextureMap;
    private ObjectMap<String, Animation> myAnimationMap;
    private ObjectMap<String, BitmapFont> myFontMap;
    private ObjectMap<String, Sound> mySoundMap;
    private ArrayList<String> myAnimationNames;

    // Constructor
    public Assets()
    {
        myTextureMap        = new ObjectMap<String, Texture>();
        myAnimationMap      = new ObjectMap<String, Animation>();
        myFontMap           = new ObjectMap<String, BitmapFont>();
        mySoundMap          = new ObjectMap<String, Sound>();
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
                        addAnimation(attributes[0], fileName, Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
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
                else
                {
                    //System.out.println("Error: unknown asset type");
                }
            }
        }
    }

    private void addTexture(final String textureName, final String fileName)
    {
        myTextureMap.put(textureName, new Texture(Gdx.files.internal(fileName)));
        if (myTextureMap.get(textureName) == null)
        {
            System.out.println("Could not load texture file: " + fileName);
        }
        else
        {
            System.out.println("Loaded texture: " + fileName);
        }
    }

    private void addAnimation(final String animationName, final String fileName, int frameCount, int speed)
    {
        myAnimationMap.put(animationName, new Animation(animationName, getTexture(animationName), frameCount, speed));
    }

    private void addFont(final String fontName, final String fileName)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fileName));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;
        parameter.color = Color.CHARTREUSE;
        myFontMap.put(fontName + "48", generator.generateFont(parameter));

        parameter.size = 62;
        myFontMap.put(fontName + "128", generator.generateFont(parameter));

        parameter.size = 24;
        parameter.color = Color.WHITE;
        myFontMap.put(fontName + "24", generator.generateFont(parameter));

        parameter.size = 40;
        myFontMap.put(fontName + "40", generator.generateFont(parameter));

        parameter.color = Color.GRAY;
        myFontMap.put(fontName + "40Black", generator.generateFont(parameter));

        generator.dispose();
    }

    private void addSound(final String soundName, final String fileName)
    {
        mySoundMap.put(soundName, Gdx.audio.newSound(Gdx.files.internal(fileName)));
        if (mySoundMap.get(soundName) == null)
        {
            System.out.println("Could not load sound file: " + fileName);
        }
        else
        {
            System.out.println("Loaded sound: " + fileName);
        }
    }

    public Texture getTexture(final String textureName)
    {
        assert(myTextureMap.get(textureName) != null);
        return myTextureMap.get(textureName);
    }

    public Animation getAnimation(final String animationName)
    {
        assert(myAnimationMap.get(animationName) != null);
        return myAnimationMap.get(animationName);
    }

    public BitmapFont getFont(final String fontName)
    {
        assert(myFontMap.get(fontName) != null);
        return myFontMap.get(fontName);
    }

    public Sound getSound(final String soundName)
    {
        assert(mySoundMap.get(soundName) != null);
        return mySoundMap.get(soundName);
    }

    public ArrayList<String> getAnimationNames()
    {
        return myAnimationNames;
    }
}
