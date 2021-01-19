
/**
 * Write a description of class energyDrink here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class energyDrink extends Items
{
 private Player energy;
 
 public void useItem()
 {
    energy.increaseEnergy();
 }
  
 public energyDrink(String description, Player energy)
 {
     super(description);
     this.energy = energy;
 }
 
}
