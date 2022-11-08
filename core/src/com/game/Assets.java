package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    public Assets() {}

    // Methods
    void loadAssets()
    {
        File dir = new File("assets");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null)
        {
            for (File child : directoryListing)
            {
                String fileName = child.getName();
                if (fileName.contains(".png"))
                {
                    addTexture(fileName);

                    String[] attributes = fileName.split("_");
                    if (attributes.length > 2)
                    {
                        addAnimation(fileName, Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
                        myAnimationNames.add(attributes[0]);
                    }
                }
                else if (fileName.contains(".ttf"))
                {
                    addFont(fileName);
                }
                else if (fileName.contains(".wav"))
                {
                    addSound(fileName);
                }
                else
                {
                    System.out.println("Error: unknown asset type");
                }
            }
        }
    }

    void addTexture(final String fileName)
    {
        String textureName = fileName.substring(0, fileName.length() - 4);
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

    void addAnimation(final String fileName, int frameCount, int speed)
    {
        String animationName = fileName.substring(0, fileName.length() - 4);
        myAnimationMap.put(animationName, new Animation(animationName, getTexture(animationName), frameCount, speed));
    }

    void addFont(final String fileName)
    {
        String fontName = fileName.substring(0, fileName.length() - 4);
        myFontMap.put(fontName, new BitmapFont(Gdx.files.internal(fileName)));
        if (myFontMap.get(fontName) == null)
        {
            System.out.println("Could not load font: " + fileName);
        }
    }

    void addSound( final String fileName)
    {
        String soundName = fileName.substring(0, fileName.length() - 4);
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

    Texture getTexture(final String textureName)
    {
        assert(myTextureMap.get(textureName) != null);
        return myTextureMap.get(textureName);
    }

    Animation getAnimation(final String animationName)
    {
        assert(myAnimationMap.get(animationName) != null);
        return myAnimationMap.get(animationName);
    }

    BitmapFont getFont(final String fontName)
    {
        assert(myFontMap.get(fontName) != null);
        return myFontMap.get(fontName);
    }

    Sound getSound(final String soundName)
    {
        assert(mySoundMap.get(soundName) != null);
        return mySoundMap.get(soundName);
    }

    ArrayList<String> getAnimationNames()
    {
        return myAnimationNames;
    }
}
