package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
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
        HealingPotion myHP = new HealingPotion(new EntityFactory());

        assertEquals(myHP.getType(), "Healing Potion");
        assertEquals(myHP.getStrength(), 15);
    }

    @Test
    void testHPOverloadedConstructor()
    {
        HealingPotion myHP = new HealingPotion(30, new EntityFactory());

        assertEquals(myHP.getType(), "Healing Potion");
        assertEquals(myHP.getStrength(), 30);
    }

    @Test
    void testActivate()
    {
        HealingPotion myHP = new HealingPotion(10, new EntityFactory());

        Priestess myPriestess = new Priestess(new EntityFactory());

        int HP = myPriestess.getHitPoints();

        myHP.activate(myPriestess);

        assertEquals(myPriestess.getHitPoints(), Math.min(10,(myPriestess.getHitPoints() +  10)));
    }

    @Test
    void testUpdate()
    {
        //write tests for update
    }
}