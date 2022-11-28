package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity
{
    //ask if we need a getter/setter for this?
    private final int myLifeSpan; //must initialize this --what should the value be??

    private Vec2 myBoundingBox;

    private long myCurrentFrame;

    //must fix this constructor!!
    public Sword()
    {
        super(new Vec2(), new Vec2());
        this.myLifeSpan = 10; //what should this value be?
        this.myCurrentFrame = 0; //what should this value be?
        this.myBoundingBox = new Vec2();
    }

    public Sword(Vec2 theBoundingBox)
    {
        super(new Vec2(), new Vec2());
        this.myLifeSpan = 10; //what should this value be?
        this.myCurrentFrame = 0; //what should this value be?
        this.myBoundingBox = theBoundingBox;
    }

    @Override
    public void update() {
        if (myCurrentFrame >= myLifeSpan)
        {
            this.destroy();
        } else if (myCurrentFrame < myLifeSpan)
        {
            super.movement();
            //super.attack(); //only DungeonCharacters should have attack() ?
            this.myCurrentFrame++;
        }

    }

    public Vec2 getBoundingBox()
    {
        return myBoundingBox;
    }

    public void setBoundingBox(final Vec2 theBoundingBox)
    {
        myBoundingBox = theBoundingBox;
    }

    public int getMyLifeSpan() { return this.myLifeSpan; }
}
