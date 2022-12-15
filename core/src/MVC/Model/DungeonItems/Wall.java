package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Wall extends Entity
{
    private String myAnimationAngle;

    public Wall(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(theBoundingBox, thePos, "Wall", new EntityFactory());
        myAnimationAngle = "";
    }

    public String getAnimationAngle()
    {
        return myAnimationAngle;
    }

    public void setAnimationAngle(final String theAnimationAngle)
    {
        myAnimationAngle = theAnimationAngle;
    }
}