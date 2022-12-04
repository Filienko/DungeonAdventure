package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
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
        SpeedPotion mySP = new SpeedPotion(new EntityFactory());

        assertEquals(mySP.getStrength(), 5);
    }

    @Test
    void testSPOverloadedConstructor()
    {
        SpeedPotion mySP = new SpeedPotion(20, new Vec2(), new EntityFactory());

        assertEquals(mySP.getStrength(), 20);
    }

    @Test
    void testItemBehavior()
    {
        SpeedPotion mySP = new SpeedPotion(10, new Vec2(), new EntityFactory());

        Warrior myWarrior = new Warrior(new EntityFactory());

        int oldSpeed = myWarrior.getMaxSpeed();

        mySP.itemBehavior(myWarrior);

        assertEquals(myWarrior.getMaxSpeed(), oldSpeed + 10);

    }

    @Test
    void testUpdate()
    {
        //write tests for update
    }
}