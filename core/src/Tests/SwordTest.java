package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Heroes.Warrior;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.DungeonItems.Door;
import MVC.Model.DungeonItems.Items.Pillar;
import MVC.Model.DungeonItems.Room;
import MVC.Model.DungeonItems.Weapon.Sword;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest
{
    private final EntityFactory entityFactory = new EntityFactory(null, "Mock");
    @Test
    void testGetInstance()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        assertEquals(15, mySword.getMyLifeSpan());
        assertEquals(0, mySword.getCurrentFrame());
    }

    @Test
    void testUpdate()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        //added getter for sword's hero for testing

        assertFalse(mySword.getMyHero().getAttackStatus());

        mySword.setCurrentFrame(mySword.getMyLifeSpan() + 1);

        mySword.update();

        assertFalse(mySword.getActiveStatus());
        assertFalse(entityFactory.getEntities().contains("Sword"));

        mySword.setCurrentFrame(mySword.getMyLifeSpan() - 1);
        long frame = mySword.getCurrentFrame();

        mySword.update();

        testCollide(); //can i?

        assertEquals(mySword.getCurrentFrame(), frame + 1);
    }

    @Test
    void getMyLifeSpan()
    {
        Sword mySword = Sword.getInstance(entityFactory,new Warrior(entityFactory));

        assertEquals(15, mySword.getMyLifeSpan());
    }

    @Test
    void testDestroy()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));

        mySword.destroy();

        assertFalse(mySword.getActiveStatus());
        assertFalse(mySword.getMyHero().getAttackStatus());
    }

    @Test
    void testCollide()
    {
        Sword mySword = Sword.getInstance(entityFactory, new Warrior(entityFactory));
        entityFactory.generateRoomEntities(new Room()); //assets are null

        for (int i = 0; i < mySword.getMyEntityFactory().getEntities().size(); i++)
        {
            Entity e = mySword.getMyEntityFactory().getEntities().get(i);
//            if (e.getType().equals("monster"))
//            {
//                assertTrue(((Door)e).getMonsterCounter()<0);
//                System.out.println("true");
//            } else if (e.getType().equals("pillar"))
//            {
//                assertTrue(((Pillar)e).isBroken());
//                System.out.println("true");
//            }
        }

        Monster m = (Monster) mySword.getMyEntityFactory().getEntities("monster").get(0);
        int d = m.getDamage();

        mySword.collide();

        assertTrue(d + mySword.getMyHero().getDamage() == m.getHitPoints());

        m.setHitPoints(0);

        mySword.collide();

        assertTrue(m.getMySize().equals(new Vec2()));
        assertFalse(m.getActiveStatus());

        m.setHitPoints(5);
        Vec2 v = m.getMyPos().minus(mySword.getMyPos());
        assertTrue(m.getVelocity().equals((v.multiply(v.quickInverseMagnitude())).multiply((float) 4)));
        assertTrue(m.isInvincibility());

        //^^ ensure that cases where !m.active and m.isInvincible are not applyin damage, etc
    }

}