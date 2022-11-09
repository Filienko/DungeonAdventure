package MVC.Model.DungeonItems.Items;

public class HealingPotion extends Item
{
    private final int myStrength;

    public HealingPotion()
    {
        this.myStrength = 15;
    }

    public HealingPotion(final int myStrength)
    {
        this.myStrength = myStrength;
    }

    public int heal(int myStrength)
    {
        return myStrength;
    }

}
