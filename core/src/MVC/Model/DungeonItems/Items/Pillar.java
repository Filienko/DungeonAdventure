package MVC.Model.DungeonItems.Items;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.Physics.Vec2;

public class Pillar extends Item
{
    /**
     * The name of this Pillar
     */
    private String myName;
    /**
     * Whether this Pillar has been broken by the hero
     */
    private boolean myBroken;
    /**
     * The number of monsters linked to this Pillar that must be destroyed to break it
     */
    private int myMonsterCounter;

    /**
     * Constructor that takes one argument
     * @param theEntityFactory The EntityFactory to which this Pillar belongs
     */
    public Pillar(final EntityFactory theEntityFactory)
    {
        super("pillar", theEntityFactory);
        setName("pillar");
        setMySize(new Vec2(64, 64));
        setMonsterCounter(0);
        myBroken = false;
    }

    /**
     * Constructor that takes three arguments
     * @param theName The name of this Pillar
     * @param theMonsterCounter The number of monsters linked to this Pillar that must be destroyed to break it
     * @param theEntityFactory The EntityFactory to which this Pillar belongs
     */
    public Pillar(final String theName, final int theMonsterCounter, final EntityFactory theEntityFactory)
    {
        super(theName, theEntityFactory);
        setName(theName);
        setMySize(new Vec2(64, 64));
        setMonsterCounter(theMonsterCounter);
        myBroken = false;
    }

    /**
     * Breaks the Pillar and increments the number of Pillars the Hero has broken
     */
    public void breakPillar()
    {
        if (myMonsterCounter == 0)
        {
            if (getMyEntityFactory().getAssets() != null) //added for testing, should i delete?
            {
                setMyAnimation(getMyEntityFactory().getAssets().getAnimation("brokenPillar"));
            }
            getMyEntityFactory().getHero().incrementPillars();
            myBroken = true;
        }
    }

    /**
     * @return The name of this Pillar
     */
    public String getName()
    {
        return myName;
    }

    /**
     * @return Whether this Pillar is broken
     */
    public boolean isBroken() { return myBroken; }

    /**
     * @return The number of monsters linked to this Pillar that must be destroyed to break it
     */
    public int getMonsterCounter()
    {
        return myMonsterCounter;
    }

    /**
     * @param theMonsterCounter The number of monsters linked to this Pillar that must be destroyed to break it
     */
    public void setMonsterCounter(final int theMonsterCounter)
    {
        if (theMonsterCounter > 0)
        {
            myMonsterCounter = theMonsterCounter;
        }
        else
        {
            decrementMonsterCounter();
        }
    }

    /**
     * Decrements the number of monsters linked to this Pillar that must be destroyed to break it
     */
    public void decrementMonsterCounter()
    {
        if (myMonsterCounter > 0)
        {
            myMonsterCounter--;
        }
    }

    /**
     * @param theName The name of this Pillar
     */
    private void setName(final String theName)
    {
        if (theName != null)
        {
            myName = theName;
        }
    }
}