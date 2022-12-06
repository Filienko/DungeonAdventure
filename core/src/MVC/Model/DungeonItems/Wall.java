package MVC.Model.DungeonItems;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Wall extends Entity
{
    public Wall(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(thePos, "Wall",theBoundingBox, new EntityFactory()); //added new EntityFactory param
    }

    @Override
    public void update()
    {

    }

//    @Override
//    public void update()
//    {
//
//    }
}
