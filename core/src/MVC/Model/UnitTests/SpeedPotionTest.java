package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.SpeedPotion;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedPotionTest
{
    @Test
    void testSPConstructor()
    {
        SpeedPotion mySP = new SpeedPotion();

        assertEquals(mySP.getStrength(), 5);
    }

    @Test
    void testSPOverloadedConstructor()
    {
        SpeedPotion mySP = new SpeedPotion(20, new Vec2());

        assertEquals(mySP.getStrength(), 20);
    }

    @Test
    void testIncrease()
    {
        SpeedPotion mySP = new SpeedPotion(10, new Vec2());

        Warrior myWarrior = new Warrior();

        int oldSpeed = myWarrior.getMaxSpeed();

        mySP.increase(myWarrior);

        assertEquals(myWarrior.getMaxSpeed(), oldSpeed + 10);

    }
}