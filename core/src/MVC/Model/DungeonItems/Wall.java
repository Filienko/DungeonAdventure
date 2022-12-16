package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Wall extends Entity
{
    /**
     * The Wall's animation angle, expressed as a String.
     */
    private String myAnimationAngle;

    /**
     * This Wall's constructor, which calls Entity's constructor to initialize the Wall's bounding box, position,
     * type, and the Entity Factory that generated it.
     * @param thePos The Wall's position.
     * @param theBoundingBox The Wall's bounding box.
     */
    public Wall(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(theBoundingBox, thePos, "Wall", new EntityFactory());
        myAnimationAngle = "";
    }

    /**
     * This method retrieves the Wall's animation angle.
     * @return The Wall's animation angle, expressed as a String.
     */
    public String getAnimationAngle()
    {
        return myAnimationAngle;
    }

    /**
     * This method sets the Wall's animation angle.
     * @param theAnimationAngle The Wall's new animation angle, expressed as a String.
     */
    public void setAnimationAngle(final String theAnimationAngle)
    {
        myAnimationAngle = theAnimationAngle;
    }
}