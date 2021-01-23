import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int energy;
    private int score;
    private ArrayList<Items> Equipment = new ArrayList<>();
    
    public void addEquipment(Items Eq)
    {
        Equipment.add(Eq);
    }
    
    public void showEquipment()
    {
     for(int i = 0;i < Equipment.size();i++)
     {
      System.out.println(Equipment.get(i).getName());   
     }
    }
    
    public int getEnergy()
    {
     return energy;    
    }
    
    public int getScore()
    {
     return score;
    }
    
    public void increaseEnergy()
    {
        energy += 10;
    }
    
    public void increaseScore()
    {
        score += 10;
    }
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        energy = 100;
        score = 0;
    }

}
