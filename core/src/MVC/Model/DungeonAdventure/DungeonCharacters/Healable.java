package MVC.Model.DungeonAdventure.DungeonCharacters;

import java.util.Random;

public interface Healable
{
    default int healCharacter(DungeonCharacter theCharacter)
    {
        int healPoints = 0;
        if (theCharacter.getHeroStatus())
        {
            Random rand = new Random();
            healPoints = rand.nextInt(25);
        } else if (!theCharacter.getHeroStatus())
        {
            Random rand = new Random();
            double chance = Math.random();
            //double charHealChance = theCharacter.getHealChance();
            //change this to Priestess's chance to heal?
            if (chance < 0.1)
            {
                healPoints = rand.nextInt(30,60);
            } else if (chance < 0.3)
            {
                healPoints = rand.nextInt(30,50);
            } else if (chance < 0.4)
            {
                healPoints = rand.nextInt(20,40);
            }
        }
        theCharacter.setHitPoints(theCharacter.getHitPoints() + healPoints);
        return healPoints;
    }
}
