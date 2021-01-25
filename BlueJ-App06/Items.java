    
    /**
     * Write a description of class Items here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */
    public class Items
    {
        // instance variables - replace the example below with your own
        protected String name;
        protected String description;
        /**
         * Constructor for objects of class Items
         */
        public Items(String description, String name)
    {
            // initialise instance variables
            this.description = description;
            this.name = name;
    }
    
    public void useItem()
    {
    
    }
    
    public String getName()
    {
        return name;
    }
    
    /**
     * Constructor for objects of class Items
     */
    public String getItemsDescription()
    {
        // initialise instance variables
        return description;
    }
}
