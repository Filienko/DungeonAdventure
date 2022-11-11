package MVC.Model.DungeonItems;

public class Dungeon
{
    private int[][] myCoordinates;

    public Dungeon()
    {
        this.myCoordinates = new int[2][2];
    }

    public Dungeon(final int[][] myCoordinates)
    {
        this.myCoordinates = myCoordinates;
    }

    public int[][] getMyCoordinates()
    {
        return myCoordinates;
    }

    public void setMyCoordinates(final int[][] myCoordinates)
    {
        this.myCoordinates = myCoordinates;
    }
}
//DUNGEON KEEPS TRACK OF THE LOCATION OF THE HERO
//DUNGEON KNOWS ONLY GENERAL ENTRANCE/EXIT LOGIC
//ROOM KNOWS SPECIFIC