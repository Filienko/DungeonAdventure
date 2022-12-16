package MVC.Model.Physics;

import MVC.Model.DungeonAdventure.DungeonCharacters.Entity;

public class Physics
{
    /**
     * @param theEntity1 One of two entities for which overlap is being calculated
     * @param theEntity2 One of two entities for which overlap is being calculated
     * @return The x and y overlaps of the two entities
     */
    public static Vec2 getOverlap(Entity theEntity1, Entity theEntity2)
    {
        return getOverlapVector(theEntity1, theEntity2, theEntity1.getMyPos(), theEntity2.getMyPos());
    }

    /**
     * @param theEntity1 One of two entities for which overlap is being calculated
     * @param theEntity2 One of two entities for which overlap is being calculated
     * @return The x and y overlaps of the two entities on their previous frames
     */
    public static Vec2 getPreviousOverlap(Entity theEntity1, Entity theEntity2)
    {
        return getOverlapVector(theEntity1, theEntity2, theEntity1.getMyPreviousPos(), theEntity2.getMyPreviousPos());
    }

    /**
     * @param theEntity1 One of two entities for which overlap is being calculated
     * @param theEntity2 One of two entities for which overlap is being calculated
     * @param thePosition1 The position of the first Entity
     * @param thePosition2 The position of the second Entity
     * @return The x and y overlaps of the two entities
     */
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

    /**
     * Returns the game world pixel position of the passed room and tile
     * @param rx The room x position
     * @param ry The room y position
     * @param tx The tile x position
     * @param ty The tile y position
     * @return The game world pixel position
     */
    public static Vec2 getPosition(final int rx, final int ry, final int tx, final int ty)
    {
        // This function takes in the room (rx, ry) coordinate
        // as well as the tile (tx, ty) coordinate, and returns the Vec2 game world
        // position of the center of the entity

        int pixelX = rx * 1216 + tx * 64; // room * 19 tiles * 64 pixels per tile + room tile * 64 pixels per tile
        int pixelY = ry * 704 + ty * 64; // 11 tiles in y component

        return new Vec2(pixelX, pixelY);
    }

    /**
     * Returns the room of the game world pixel position
     * @param thePixelX The x component of the pixel position
     * @param thePixelY The y component of the pixel position
     * @return The room the pixel position is in
     */
    public static Vec2 getRoom(final float thePixelX, final float thePixelY)
    {
        float roomX = (float) Math.floor(thePixelX / 1216);
        float roomY = (float) Math.floor(thePixelY / 704);

        return new Vec2(roomX, roomY);
    }

    /**
     * Resolves an overlap between a movable Entity and static tile Entity
     * @param theOverlap The current overlap between the two Entities
     * @param theEntity The movable Entity that overlapped the tile
     * @param theTile The static tile Entity that should not be passed through
     */
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