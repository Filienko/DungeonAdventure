package MVC.Model.DungeonItems.Weapon;

import MVC.Model.Physics.Vec2;

public class Sword implements Attackable
{
    private Vec2 myBoundingBox;
    private int myLifeSpan; //must initialize this
    private long myCurrentFrame;

    //should attackable extend Entity to get super.movement/super.attack or should attackable implement its own
    @Override
    public void update() {
        if (myCurrentFrame >= myLifeSpan) {
            //request that the entity factory delete it
            //entity factory does not have a method that does that
        }
//        super.movement();
//        super.attack();
        this.myCurrentFrame++;
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
