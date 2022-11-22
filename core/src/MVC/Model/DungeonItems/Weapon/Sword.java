package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity implements Attackable
{
    private Vec2 myBoundingBox;

    public Sword()
    {
        super(new Vec2(),new Vec2());
    }

    public Sword(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(thePos, theBoundingBox);
    }

    @Override
    public Vec2 getBoundingBox()
    {
        return myBoundingBox;
    }

    @Override
    public void setBoundingBox(final Vec2 theBoundingBox)
    {
        myBoundingBox = theBoundingBox;
    }
}
