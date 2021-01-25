
/**
 * Write a description of class Money here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Money extends Items
{
    // instance variables - replace the example below with your own
    private Player score;

    public void useItem()
    {  
     score.increaseScore();
    }
  
    public Money(String description,String name, Player score)
    {
     super(description, name);
     this.score = score;
    }
}
