package MVC.Model.DungeonItems.Weapon;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.Physics.Vec2;

public class Sword extends Entity
{
    private static Sword mySword;

    private final EntityFactory myEntityFactory;

    private final int myLifeSpan; //do we need a getter/setter for this?

    private Vec2 myBoundingBox;

    private long myCurrentFrame;

    private int damage; //how much damage it does?


//    public Sword(final EntityFactory theEntityFactory)
//    {
//        super(new Vec2(), new Vec2(), theEntityFactory);
//        this.myEntityFactory = theEntityFactory;
//        this.myLifeSpan = 15;
//        this.myCurrentFrame = 0;
//        this.myBoundingBox = new Vec2();
//    }

    private Sword(final Vec2 theBoundingBox, final EntityFactory theEntityFactory)
    {
        super(new Vec2(), new Vec2(), theEntityFactory);
        this.myEntityFactory = theEntityFactory;
        this.myLifeSpan = 15;
        this.myCurrentFrame = 0;
        this.myBoundingBox = theBoundingBox;
    }

    public static Sword getInstance(final Vec2 theBoundingBox, final EntityFactory theEntityFactory) {
        if (mySword == null) {
            mySword = new Sword(theBoundingBox, theEntityFactory);
        }
        return mySword;
    }

    public Vec2 getBoundingBox()
    {
        return myBoundingBox;
    }

    public void setBoundingBox(final Vec2 theBoundingBox)
    {
        myBoundingBox = theBoundingBox;
    }

    public int getMyLifeSpan() { return myLifeSpan; }

    @Override
    public void update() {
        if (myCurrentFrame >= myLifeSpan)
        {
            destroy();
        } else if (myCurrentFrame < myLifeSpan)
        {
            super.movement();
            myCurrentFrame++;
        }

    }
}
