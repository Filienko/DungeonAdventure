package Tests;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;
import MVC.Model.DungeonAdventure.DungeonCharacters.EntityFactory;
import MVC.Model.DungeonAdventure.DungeonCharacters.Monster;
import MVC.Model.Physics.Intersect;
import MVC.Model.Physics.Physics;
import MVC.Model.Physics.Vec2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhysicsTest
{
    private final EntityFactory theEntityFactory = new EntityFactory(null, "Mock");

    /**
     * Test methods for Physics calculations.
     */
    @Test
    void testPhysics()
    {
        var hero = theEntityFactory.generateMockHero();
        Monster ogre = theEntityFactory.generateOgre();

        hero.setMyPreviousPos(new Vec2(1,1));
        ogre.setMyPreviousPos(new Vec2(-1,2));

        hero.setMyPos(new Vec2(1,2));
        ogre.setMyPos(new Vec2(1,3));

        int heroXHalf = (int)hero.getMySize().getMyX()/2;
        int heroYHalf = (int)hero.getMySize().getMyY()/2;

        int ogreXHalf = (int)ogre.getMySize().getMyX()/2;
        int ogreYHalf = (int)ogre.getMySize().getMyY()/2;

        Vec2 dPos = new Vec2(Math.abs(hero.getMyPos().getMyX() - ogre.getMyPos().getMyX()),
                Math.abs(hero.getMyPos().getMyY() - ogre.getMyPos().getMyY()));

        assertTrue(Vec2.equals(new Vec2(heroXHalf + ogreXHalf - dPos.getMyX(), heroYHalf + ogreYHalf - dPos.getMyY()),
                Physics.getOverlap(hero,ogre)));
        assertTrue(Vec2.equals(new Vec2(heroXHalf + ogreXHalf - dPos.getMyX(), heroYHalf + ogreYHalf - dPos.getMyY()),
                Physics.getOverlapVector(hero,ogre,hero.getMyPos(),ogre.getMyPos())));

        assertFalse(Vec2.equals(new Vec2(),
                Physics.getPreviousOverlap(hero,ogre)));
        assertFalse(Physics.isInside(new Vec2(),
                ogre));
        assertTrue(Vec2.equals(new Vec2(),
                Physics.lineIntersect(new Vec2(),new Vec2(),new Vec2(),new Vec2()).getMyVec()));
        assertTrue(Vec2.equals(new Vec2(),
                Physics.lineIntersect(new Vec2(), new Vec2(),new Vec2(),hero.getMyPos()).getMyVec()));
    }

    /**
     * Test method for Vec2 operations.
     */
    @Test
    void testVec2Operations()
    {
        var vec = new Vec2();
        vec.setMyX(1);
        assertTrue(vec.getMyX()==1);
        vec.setMyY(2);
        assertTrue(vec.getMyY()==2);
        vec = vec.add(new Vec2(1,2));
        assertTrue(vec.getMyX()==2);
        assertTrue(vec.getMyY()==4);
        vec = vec.add(new Vec2(3,0));
        assertTrue(vec.getMyX()==5);
        assertTrue(vec.getMyY()==4);
        vec.copy(new Vec2(1,1));
        assertTrue(vec.getMyX()==1);
        assertTrue(vec.getMyY()==1);
        assertEquals(1250, vec.getDistanceSquared(new Vec2(26,26)));
        assertTrue(Math.sqrt(vec.getDistanceSquared(new Vec2(26,26)))+0.1>vec.computeDistance(new Vec2(26,26)));
        assertEquals(0, vec.crossProduct(new Vec2(10,10)));
        assertTrue(vec.quickInverseMagnitude()>0.51);
        assertTrue(vec.equals(new Vec2(1,1)));
        assertEquals(10, vec.multiply(10).getMyX());
        assertEquals(10, vec.multiply(10).getMyY());
        assertTrue(0.1+0.001> vec.divide(10).getMyX() && 0.1-0.001< vec.divide(10).getMyX());
        assertTrue(0.1+0.001> vec.divide(10).getMyY() && 0.1-0.001< vec.divide(10).getMyX());
        assertEquals(-2, vec.minus(new Vec2(3,4)).getMyX());
        assertEquals(-3, vec.minus(new Vec2(3,4)).getMyY());
        assertEquals(2, vec.getMagnitudeSquared());
        assertEquals(5, vec.dotProduct(new Vec2(3,2)));
    }

    /**
     * Test method for Intersect operations.
     */
    @Test
    void testIntersect()
    {
        var intersect = new Intersect();
        intersect.setIntersectionOccurs(true);
        assertTrue(intersect.isMyIntersectionOccurs());
        intersect.setVec(new Vec2(1,3));
        assertEquals(new Vec2(1,3).getMyX(),intersect.getMyVec().getMyX());
        assertEquals(new Vec2(1,3).getMyY(),intersect.getMyVec().getMyY());
    }
}
