package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonItems.Items.SpeedPotion;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedPotionTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    @Test
    void testSPConstructor()
    {
        SpeedPotion mySP = new SpeedPotion(entityFactory);

        assertEquals(mySP.getStrength(), 1);
        assertEquals(mySP.getType(), "speedPotion");
    }

    @Test
    void testSPOverloadedConstructor()
    {
        SpeedPotion mySP = new SpeedPotion(8, entityFactory);

        assertEquals(mySP.getStrength(), 8);
        assertEquals(mySP.getType(), "speedPotion");
    }

    @Test
    void testItemBehavior()
    {
        SpeedPotion mySP = new SpeedPotion(2, entityFactory);

        Warrior myWarrior = new Warrior(new EntityFactory());

        int oldSpeed = myWarrior.getMaxSpeed();

        mySP.activate(myWarrior);

        assertEquals(myWarrior.getMaxSpeed(), oldSpeed + 2);

    }
}