package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
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
        AttackPotion myAP = new AttackPotion(new EntityFactory());

        assertEquals(myAP.getStrength(), 2);
    }

    @Test
    void testAPOverloadedConstructor()
    {
        AttackPotion myAP = new AttackPotion(4, new EntityFactory());

        assertEquals(myAP.getStrength(), 4);
    }

    @Test
    void testActivate()
    {
        AttackPotion myAP = new AttackPotion(3, new EntityFactory());

        Warrior myWarrior = new Warrior(new EntityFactory());

        int damage = myWarrior.getDamage();

        myAP.activate(myWarrior);

        assertEquals(myWarrior.getDamage(), damage + 3);

    }

    @Test
    void testGetType()
    {
        AttackPotion myAP = new AttackPotion(new EntityFactory());

        assertEquals(myAP.getType(), "Attack Potion");
    }

}