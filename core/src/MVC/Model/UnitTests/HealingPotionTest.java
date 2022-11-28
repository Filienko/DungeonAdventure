package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealingPotionTest
{
    @Test
    void testHPConstructor()
    {
        HealingPotion myHP = new HealingPotion();

        assertEquals(myHP.getStrength(), 5);
    }

    @Test
    void testHPOverloadedConstructor()
    {
        HealingPotion myHP = new HealingPotion(30, new Vec2());

        assertEquals(myHP.getStrength(), 30);
    }

    @Test
    void testIncrease()
    {
        HealingPotion myHP = new HealingPotion(10, new Vec2());

        Priestess myPriestess = new Priestess();

        int HP = myPriestess.getHitPoints();

        myHP.increase(myPriestess);

        assertEquals(myPriestess.getHitPoints(), HP + 10);

    }
}