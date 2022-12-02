package MVC.Model.Physics;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;

public class Physics {

    public static Vec2 getOverlap(Entity theEntity1, Entity theEntity2)
    {
        return getOverlapVector(theEntity1, theEntity2, theEntity1.getMyPos(), theEntity2.getMyPos());
    }

    public static Vec2 getPreviousOverlap(Entity theEntity1, Entity theEntity2)
    {
        return getOverlapVector(theEntity1, theEntity2, theEntity1.getMyPreviousPos(), theEntity2.getMyPreviousPos());
    }

    public static Vec2 getOverlapVector(final Entity theEntity1, final Entity theEntity2, final Vec2 thePosition1, final Vec2 thePosition2)
    {
        Vec2 en1HalfSize= theEntity1.getMySize().divide(2);
        Vec2 en2HalfSize= theEntity2.getMySize().divide(2);

        Vec2 delta= new Vec2(Math.abs(thePosition1.getMyX() - thePosition2.getMyX()), Math.abs(thePosition1.getMyY() - thePosition2.getMyY()));

        float ox = en1HalfSize.getMyX() + en2HalfSize.getMyX() - delta.getMyX();
        float oy = en1HalfSize.getMyY() + en2HalfSize.getMyY() - delta.getMyY();

        return new Vec2(ox, oy);
    }

    public static boolean isInside(Vec2 theVec1, Entity theEntity)
    {
        boolean inside= false;

        // If the entity doesn't have animation, we can't be inside it
        if (theEntity.isMyEntityAnimated())
        {
            // Checks fi a point is contained within a bounding box
            Vec2 e_position= theEntity.getMyPos();
            Vec2 e_half_size= theEntity.getMyPos().divide(2);

            Vec2 delta= new Vec2(Math.abs(e_position.getMyX() - theVec1.getMyX()), Math.abs(e_position.getMyY() - theVec1.getMyY()));

            boolean overlap_x = e_half_size.getMyX() > delta.getMyX();
            boolean overlap_y = e_half_size.getMyY() > delta.getMyY();

            inside= overlap_x && overlap_y;
        }

        return inside;
    }

    public static Intersect lineIntersect(final Vec2 theVec1, final Vec2 theVec2, final Vec2 theVec3, final Vec2 theVec4)
    {
        Intersect result = new Intersect();

        Vec2 r = (theVec2.minus(theVec1));
        Vec2 s= (theVec4.minus(theVec3));
        float rxs = r.crossProduct(s);
        Vec2 cma = theVec3.minus(theVec1);
        float t = cma.crossProduct(s) / rxs;
        float u = cma.crossProduct(r) / rxs;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1)
        {
            result.setIntersectionOccurs(true);
            result.setVec(new Vec2(theVec1.getMyX() + t * r.getMyX(),theVec1.getMyY() + t * r.getMyY()));
        }

        return result;
    }

    public static boolean entityIntersect(final Vec2 theVec1, final Vec2 theVec2, final Entity theEntity)
    {
        float halfSizeX= theEntity.getMySize().divide(2).getMyX();
        float halfSizeY= theEntity.getMySize().divide(2).getMyY();
        Vec2 p1 = new Vec2(theEntity.getMyPos());
        Vec2 p2 = new Vec2(theEntity.getMyPos());
        Vec2 p3 = new Vec2(theEntity.getMyPos());
        Vec2 p4 = new Vec2(theEntity.getMyPos());

        p1.add(halfSizeX, halfSizeY);
        p2.add(halfSizeX, -halfSizeY);
        p3.add(-halfSizeX, -halfSizeY);
        p4.add(-halfSizeX, halfSizeY);

        if (lineIntersect(theVec1, theVec2, p1, p2).isMyIntersectionOccurs()) { return true; }
        if (lineIntersect(theVec1, theVec2, p2, p3).isMyIntersectionOccurs()) { return true; }
        if (lineIntersect(theVec1, theVec2, p3, p4).isMyIntersectionOccurs()) { return true; }
        if (lineIntersect(theVec1, theVec2, p1, p4).isMyIntersectionOccurs()) { return true; }

        return false;
    }

    public static Vec2 getPosition(final int rx, final int ry, final int tx, final int ty)
    {
        // This function takes in the room (rx, ry) coordinate
        // as well as the tile (tx, ty) coordinate, and returns the Vec2 game world
        // position of the center of the entity

        int pixelX = rx * 1216 + tx * 64; // room * 19 tiles * 64 pixels per tile + room tile * 64 pixels per tile
        int pixelY = ry * 704 + ty * 64; // 11 tiles in y component

        return new Vec2(pixelX, pixelY);
    }
}