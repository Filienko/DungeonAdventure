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
     * Pillar constructor that calls the Item constructor to initialize its type and the Entity Factory that generated
     * it. It also sets the Pillar's name, size, monster counter, and its broken status.
     * @param theName The Pillar's name.
     * @param theMonsterCounter The number of Monsters in the same Room as the Pillar.
     * @param theEntityFactory The Entity Factory that generated the Pillar.
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
     * This method breaks the Pillar once the Hero has defeated all the Monsters in the same
     * Room as the Pillar and increments the number of Pillars the Hero has broken
     */
    public void breakPillar()
    {
        if (myMonsterCounter == 0)
        {
            if (getMyEntityFactory().getAssets() != null)
            {
                setMyAnimation(getMyEntityFactory().getAssets().getAnimation("brokenPillar"));
            }
            getMyEntityFactory().getHero().incrementPillars();
            myBroken = true;
        }
    }

    /**
     * This method retrieves the Pillar's name.
     * @return The Pillar's name.
     */
    public String getName()
    {
        return myName;
    }

    /**
     * This method tells if the Pillar has been broken.
     * @return The Pillar's broken status.
     */
    public boolean isBroken() { return myBroken; }

    /**
     * This method sets the number of Monsters in the same Room as the Pillar.
     * @param theMonsterCounter The Pillar's new monster counter.
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
     * This method sets the Pillar's name.
     * @param theName The Pillar's new name.
     */
    private void setName(final String theName)
    {
        if (theName != null)
        {
            myName = theName;
        }
    }
}