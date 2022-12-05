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

        assertEquals(myAP.getStrength(), 5);
    }

    @Test
    void testAPOverloadedConstructor()
    {
        AttackPotion myAP = new AttackPotion(25, new EntityFactory());

        assertEquals(myAP.getStrength(), 25);
    }

    @Test
    void testActivate()
    {
        AttackPotion myAP = new AttackPotion(10, new EntityFactory());

        Warrior myWarrior = new Warrior(new EntityFactory());

        int damage = myWarrior.getDamage();

        myAP.activate(myWarrior);

        assertEquals(myWarrior.getDamage(), damage + 10);

    }

    @Test
    void testGetType()
    {
        AttackPotion myAP = new AttackPotion(new EntityFactory());

        assertEquals(myAP.getType(), "Attack Potion");
    }


    @Test
    void testUpdate()
    {
        //write tests for update
    }
}