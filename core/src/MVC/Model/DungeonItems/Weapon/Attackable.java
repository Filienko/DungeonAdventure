package MVC.Model.DungeonItems.Weapon;

import MVC.Model.Physics.Vec2;

public interface Attackable
{
    Vec2 getBoundingBox();

    void setBoundingBox(Vec2 theBoundingBox);
}
