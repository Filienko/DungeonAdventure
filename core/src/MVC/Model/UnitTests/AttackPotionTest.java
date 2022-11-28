package MVC.Model.UnitTests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackPotionTest
{
    @Test
    void testAPConstructor()
    {
        AttackPotion myAP = new AttackPotion();

        assertEquals(myAP.getStrength(), 5);
    }

    @Test
    void testAPOverloadedConstructor()
    {
        AttackPotion myAP = new AttackPotion(25, new Vec2());

        assertEquals(myAP.getStrength(), 25);
    }

    @Test
    void testIncrease()
    {
        AttackPotion myAP = new AttackPotion(10, new Vec2());

        Warrior myWarrior = new Warrior();

        int minDamage = myWarrior.getMinDamageRange();

        int maxDamage = myWarrior.getMaxDamageRange();

        myAP.increase(myWarrior);

        assertEquals(myWarrior.getMinDamageRange(), minDamage + 10);
        assertEquals(myWarrior.getMaxDamageRange(), maxDamage + 10);

    }
}