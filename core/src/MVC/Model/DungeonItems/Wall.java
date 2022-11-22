package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public class Wall extends Entity
{
    protected Wall(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(thePos, theBoundingBox);
    }
}
