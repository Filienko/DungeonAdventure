package com.game;

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

    void addTexture(final String fileName) {}

    void addAnimation(final String fileName, int frameCount, int speed) {}

    void addFont(final String fileName) {}

    void addSound( final String fileName) {}

    Texture getTexture(final String textureName) {}

    Animation getAnimation(final String animationName) {}

    BitmapFont getFont(final String fontName) {}

    Sound getSound(final String soundName) {}

    ArrayList<String> getAnimationNames() {}
}
