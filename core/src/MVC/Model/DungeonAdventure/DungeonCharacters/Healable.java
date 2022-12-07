package MVC.Model.DungeonAdventure.DungeonCharacters;

import java.util.Random;

public interface Healable
{
    /**
     * This method allows a DungeonCharacter to heal another character. The number of hit points that the
     * DungeonCharacter is allowed to restore is randomly determined between 1 and the specified maximum (theHealMax).
     * @param theCharacter The DungeonCharacter whose hit points are being restored.
     * @param theHealMax The maximum number of hit points that can be restored.
     * @return The number of hit points that were restored.
     */
    default int heal(DungeonCharacter theCharacter, int theHealMax)
    {
        Random rand = new Random();
        int healPoints = rand.nextInt(theHealMax) + 1;

        int maxHeal = 10;

        if (theCharacter.getHitPoints() + healPoints > maxHeal)
        {
            int overLimit = (theCharacter.getHitPoints() + healPoints) - maxHeal;
            healPoints -= overLimit;
        }

        theCharacter.setHitPoints(theCharacter.getHitPoints() + healPoints);

        return healPoints;
    }
}
