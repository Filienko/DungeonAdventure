package MVC.Model.Physics;

public class Intersect {
    /**
     * Whether an intersection has occurred
     */
    private boolean myIntersectionOccurs;
    /**
     * The vector
     */
    private Vec2 myVec;

    /**
     * Constructor that takes zero arguments
     */
    public Intersect()
    {
        this.myIntersectionOccurs = false;
        this.myVec = new Vec2();
    }

    /**
     * @param myIntersectionOccurs Whether an intersection has occurred
     * @param myVec The vector
     */
    public Intersect(final boolean myIntersectionOccurs, final Vec2 myVec)
    {
        this.myIntersectionOccurs = myIntersectionOccurs;
        this.myVec = myVec;
    }

    /**
     * @return Whether an intersection is occurring
     */
    public boolean isMyIntersectionOccurs()
    {
        return myIntersectionOccurs;
    }

    /**
     * @return The vector
     */
    public Vec2 getMyVec()
    {
        return myVec;
    }

    /**
     * @param theIntersectionOccurs Whether an intersection is occurring
     */
    public void setIntersectionOccurs(final boolean theIntersectionOccurs)
    {
        myIntersectionOccurs = theIntersectionOccurs;
    }

    /**
     * @param theVec The vector
     */
    public void setVec(final Vec2 theVec)
    {
        myVec = theVec;
    }
}