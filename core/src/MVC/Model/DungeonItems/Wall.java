package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Wall extends Entity
{
    public Wall(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(theBoundingBox, thePos, "Wall", new EntityFactory());
    }

    @Override
    public void update()
    {

    }
}
