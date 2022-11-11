package MVC.Model.DungeonItems;

import MVC.Model.Physics.Vec2;
//TODO: discuss container Class

public class Room
{
    private boolean myEntranceStatus;
    private boolean myExitStatus;
    private Vec2 myLocation;

    public Room()
    {
            this.myEntranceStatus = false;
            this.myExitStatus = false;
            this.myLocation = new Vec2();
    }

    public Room(boolean exit)
    {
        if(exit==true){
            this.myEntranceStatus = true;
            this.myExitStatus = false;
            this.myLocation = new Vec2();
        } else{
            this.myEntranceStatus = false;
            this.myExitStatus = true;
            this.myLocation = new Vec2();

        }
    }

    public Room(final boolean myEntranceStatus, final boolean myExitStatus, final Vec2 myLocation)
    {
        this.myEntranceStatus = myEntranceStatus;
        this.myExitStatus = myExitStatus;
        this.myLocation = myLocation;
    }

    public Vec2 getMyLocation()
    {
        return myLocation;
    }

    public void setMyLocation(final Vec2 myLocation)
    {
        this.myLocation = myLocation;
    }

    private boolean isMyEntranceStatus()
    {
        return myEntranceStatus;
    }

    private void setMyEntranceStatus(final boolean myEntranceStatus)
    {
        this.myEntranceStatus = myEntranceStatus;
    }

    private boolean isMyExitStatus()
    {
        return myExitStatus;
    }

    private void setMyExitStatus(final boolean myExitStatus)
    {
        this.myExitStatus = myExitStatus;
    }

    @Override
    public String toString()
    {
        return "Room{" +
                "myEntranceStatus=" + myEntranceStatus +
                ", myExitStatus=" + myExitStatus +
                ", myLocation=" + myLocation +
                '}';
    }
}