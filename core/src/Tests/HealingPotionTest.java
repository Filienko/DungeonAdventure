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

        assertEquals(myHP.getStrength(), 5);
    }

    @Test
    void testHPOverloadedConstructor()
    {
        HealingPotion myHP = new HealingPotion(30, new Vec2(), new EntityFactory());

        assertEquals(myHP.getStrength(), 30);
    }

    @Test
    void testItemBehavior()
    {
        HealingPotion myHP = new HealingPotion(10, new Vec2(), new EntityFactory());

        Priestess myPriestess = new Priestess(new EntityFactory());

        int HP = myPriestess.getHitPoints();

        myHP.itemBehavior(myPriestess);

        assertEquals(myPriestess.getHitPoints(), HP + 10);
    }

    @Test
    void testUpdate()
    {
        //write tests for update
    }
}