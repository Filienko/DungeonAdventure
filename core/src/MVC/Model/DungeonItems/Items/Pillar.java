package MVC.Model.DungeonItems.Items;

import MVC.Model.Physics.Vec2;

import java.util.Random;

public class Pillar extends Item
{

    public Pillar(final String theName)
    {
        super(theName, new Vec2((new Random()).nextInt(0, 21),
                (new Random()).nextInt(0, 13)));
    }
}

