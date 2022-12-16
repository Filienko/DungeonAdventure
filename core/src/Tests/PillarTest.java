package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Hero;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Thief;
import MVC.Model.DungeonItems.Items.AttackPotion;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarTest
{
    private final EntityFactory theEntityFactory = new EntityFactory(null, "Mock");

    /**
     * Test method for Pillar's constructor.
     */
    @Test
    void testPillarConstructor()
    {
        Pillar myPillar = new Pillar(theEntityFactory);

        assertTrue(myPillar.getName().equals("pillar"));
        assertTrue(myPillar.getMySize().equals(new Vec2(64,64)));
        assertEquals(myPillar.getMonsterCounter(), 0);
        assertFalse(myPillar.isBroken());
    }

    /**
     * Test method for Pillar's overloaded constructor.
     */
    @Test
    void testOLPillarConstructor()
    {
        Pillar myPillar = new Pillar("Inheritance", 2, theEntityFactory);

        assertTrue(myPillar.getName().equals("Inheritance"));
        assertTrue(myPillar.getMySize().equals(new Vec2(64,64)));
        assertEquals(myPillar.getMonsterCounter(), 2);
        assertFalse(myPillar.isBroken());
    }

    /**
     * Test method for {@link Pillar#getName()}.
     */
    @Test
    void testGetName()
    {
        Pillar myPillar = new Pillar("Abstraction", 0, theEntityFactory);

        assertTrue(myPillar.getName().equals("Abstraction"));
    }

    /**
     * Test method for {@link Pillar#breakPillar()}.
     */
    @Test
    void testBreakPillar()
    {
        Pillar myPillar = new Pillar("Encapsulation", 1, theEntityFactory);

        myPillar.breakPillar();

        assertFalse(myPillar.isBroken());
        assertEquals(myPillar.getMyEntityFactory().getHero().getPillars(), 0);

        myPillar.decrementMonsterCounter();
        myPillar.breakPillar();

        assertEquals(myPillar.getMyEntityFactory().getHero().getPillars(), 1);
        assertTrue(myPillar.isBroken());
    }

    /**
     * Test method for {@link Pillar#isBroken()}.
     */
    @Test
    void testIsBroken()
    {
        Pillar myPillar = new Pillar("Inheritance", 0,theEntityFactory);

        assertFalse(myPillar.isBroken());
    }

    /**
     * Test method for {@link Pillar#getMonsterCounter()}.
     */
    @Test
    void testGetMonsterCounter()
    {
        Pillar myPillar = new Pillar("Polymorphism", 0,theEntityFactory);

        assertEquals(myPillar.getMonsterCounter(), 0);
    }

    /**
     * Test method for {@link Pillar#setMonsterCounter(int)}.
     */
    @Test
    void testSetMonsterCounter()
    {
        Pillar myPillar = new Pillar("Polymorphism", 0,theEntityFactory);

        assertEquals(myPillar.getMonsterCounter(), 0);

        myPillar.setMonsterCounter(3);

        assertEquals(myPillar.getMonsterCounter(), 3);
    }

    /**
     * Test method for {@link Pillar#decrementMonsterCounter()}.
     */
    @Test
    void testDecrementMonsterCounter()
    {
        Pillar myPillar = new Pillar("Polymorphism", 2,theEntityFactory);

        assertEquals(myPillar.getMonsterCounter(), 2);

        myPillar.decrementMonsterCounter();

        assertEquals(myPillar.getMonsterCounter(), 1);
    }
}