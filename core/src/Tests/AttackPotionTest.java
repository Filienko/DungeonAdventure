package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.AttackPotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackPotionTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");

    /**
     * Test method for Attack Potion's constructor.
     */
    @Test
    void testAPConstructor()
    {
        AttackPotion myAP = new AttackPotion(entityFactory);

        assertEquals(myAP.getStrength(), 1);
    }

    /**
     * Test method for Attack Potion's overloaded constructor.
     */
    @Test
    void testAPOverloadedConstructor()
    {
        AttackPotion myAP = new AttackPotion(4, entityFactory);

        assertEquals(myAP.getStrength(), 4);
    }

    /**
     * Test method for {@link AttackPotion#activate(Hero)}.
     */
    @Test
    void testActivate()
    {
        AttackPotion myAP = new AttackPotion(3, entityFactory);

        Warrior myWarrior = new Warrior(entityFactory);

        int damage = myWarrior.getDamage();

        myAP.activate(myWarrior);

        assertEquals(myWarrior.getDamage(), damage + 3);
    }

    /**
     * Test method for {@link AttackPotion#getType()}.
     */
    @Test
    void testGetType()
    {
        AttackPotion myAP = new AttackPotion(entityFactory);

        assertEquals(myAP.getType(), "attackPotion");
    }

}