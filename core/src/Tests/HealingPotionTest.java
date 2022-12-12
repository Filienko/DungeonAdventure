package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Priestess;
import MVC.Model.DungeonItems.Items.HealingPotion;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealingPotionTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    @Test
    void testHPConstructor()
    {
        HealingPotion myHP = new HealingPotion(entityFactory);

        assertEquals(myHP.getType(), "healthPotion");
        assertEquals(myHP.getStrength(), 2);
    }

    @Test
    void testHPOverloadedConstructor()
    {
        HealingPotion myHP = new HealingPotion(5,entityFactory);

        assertEquals(myHP.getType(), "healthPotion");
        assertEquals(myHP.getStrength(), 5);
    }

    @Test
    void testActivateLessThan10()
    {
        HealingPotion myHP = new HealingPotion(10, entityFactory);

        Priestess myPriestess = new Priestess(entityFactory);

        int HP = myPriestess.getHitPoints();

        myHP.activate(myPriestess);

        assertEquals(myPriestess.getHitPoints(), Math.min(10,(myPriestess.getHitPoints() +  10)));
    }

    @Test
    void testActivateMoreThan10()
    {
        HealingPotion myHP = new HealingPotion(12, entityFactory);

        Priestess myPriestess = new Priestess(entityFactory);

        int HP = myPriestess.getHitPoints();

        myHP.activate(myPriestess);

        assertEquals(myPriestess.getHitPoints(), Math.min(10,(myPriestess.getHitPoints() +  12)));
    }
}