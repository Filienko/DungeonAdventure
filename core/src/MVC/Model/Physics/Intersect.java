package MVC.Model.Physics;

public class Intersect {
    private boolean myIntersectionOccurs;
    private Vec2 myVec;

    public Intersect()
    {
        this.myIntersectionOccurs = false;
        this.myVec = new Vec2();
    }

    public Intersect(final boolean myIntersectionOccurs, final Vec2 myVec)
    {
        this.myIntersectionOccurs = myIntersectionOccurs;
        this.myVec = myVec;
    }

    public boolean isMyIntersectionOccurs()
    {
        return myIntersectionOccurs;
    }

    public Vec2 getMyVec()
    {
        return myVec;
    }

    public void setIntersectionOccurs(final boolean theIntersectionOccurs)
    {
        myIntersectionOccurs = theIntersectionOccurs;
    }

    public void setVec(final Vec2 theVec)
    {
        myVec = theVec;
    }
}