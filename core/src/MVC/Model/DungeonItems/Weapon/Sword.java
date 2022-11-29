package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity
{
    private Vec2 myBoundingBox;
    private int myLifeSpan; //must initialize this
    private long myCurrentFrame;

    //should attackable extend Entity to get super.movement/super.attack or should attackable implement its own methods
    @Override
    public void update() {
        if (myCurrentFrame >= myLifeSpan) {
            //request that the entity factory delete it
            //entity factory does not have a method that does that

            //add a method to Entity
        }
//        super.movement();
//        super.attack();
        this.myCurrentFrame++;
    }

    public Sword()
    {
        super(new Vec2(),new Vec2());
    }

    public Sword(final Vec2 thePos, final Vec2 theBoundingBox)
    {
        super(thePos, theBoundingBox);
    }
}
