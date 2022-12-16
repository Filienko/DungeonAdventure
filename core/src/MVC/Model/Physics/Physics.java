package MVC.Model.Physics;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;

public class Physics
{
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
        if (theEntity1.getMySize().equals(new Vec2(0, 0))
                || theEntity2.getMySize().equals(new Vec2(0, 0)))
        {
            return new Vec2(0, 0);
        }

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

    public static Vec2 getRoom(final float thePixelX, final float thePixelY)
    {
        float roomX = (float) Math.floor(thePixelX / 1216);
        float roomY = (float) Math.floor(thePixelY / 704);

        return new Vec2(roomX, roomY);
    }

    public static Vec2 calculateBezierPoint(final float t,
                        final Vec2 point0, final Vec2 point1, final Vec2 point2, final Vec2 point3)
    {
        float u = 1 - t;
        float tt = t * t;
        float uu = u * u;
        float uuu = uu * u;
        float ttt = tt * t;

        Vec2 p = point0.multiply(uuu);          // first term
        p = p.add(point1.multiply(3 * uu * t)); // second term
        p = p.add(point2.multiply(3 * u * tt)); // third term
        p = p.add(point3.multiply(ttt));        // fourth term

        return p;
    }

    public static void tileResolution(final Vec2 theOverlap, final Entity theEntity, final Entity theTile)
    {
        Vec2 previousOverlap = Physics.getPreviousOverlap(theEntity, theTile);

        // If the overlap is horizontal
        if (previousOverlap.getMyY() > 0)
        {
            // If the entity came from the left, push them out to the left
            if (theEntity.getMyPos().getMyX() < theTile.getMyPos().getMyX())
            {
                theEntity.getMyPos().setMyX(theEntity.getMyPos().getMyX() - (theOverlap.getMyX()));
            }
            // If the entity came from the right push them out to the right
            else
            {
                theEntity.getMyPos().setMyX(theEntity.getMyPos().getMyX() + (theOverlap.getMyX()));
            }
        }

        // If the overlap is vertical
        if (previousOverlap.getMyX() > 0)
        {
            // If the entity came from above push them up
            if (theEntity.getMyPos().getMyY() < theTile.getMyPos().getMyY())
            {
                theEntity.getMyPos().setMyY(theEntity.getMyPos().getMyY() - (theOverlap.getMyY()));
            }
            else
            {
                theEntity.getMyPos().setMyY(theEntity.getMyPos().getMyY() + (theOverlap.getMyY()));
            }
        }
    }

}