package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.DungeonCharacter;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Pillar extends Item
{
    private String myName;

    private long myCurrentFrame;

    private final EntityFactory myEntityFactory;

    public Pillar(final String theName, final Vec2 theLocation, final EntityFactory theEntityFactory)
    {
        super("Pillar", theLocation, theEntityFactory);
        myName = theName;
        myCurrentFrame = 0;
        myEntityFactory = theEntityFactory;
    }

    public void itemBehavior(final DungeonCharacter theCharacter) {
        ((Hero)theCharacter).addPillar(this); //adding pillars works differently now?
    }

    public String getMyName()
    {
        return myName;
    }

    public void setMyName(final String myName)
    {
        this.myName = myName;
    }

    @Override
    public void update()
    {
        //itemBehavior();
        myCurrentFrame++;
    }

}

