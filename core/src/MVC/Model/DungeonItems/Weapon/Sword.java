package MVC.Model.DungeonItems.Weapon;

import MVC.Model.Physics.Vec2;

public class Sword implements Attackable
{
    private Vec2 myBoundingBox;

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
