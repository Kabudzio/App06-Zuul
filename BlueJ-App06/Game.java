/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Your name
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();       
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, Bank, Toilets, Staff, garage, corridor , Security, Safe;
        energyDrink drink = new energyDrink("You see an energy drink", player);
        Money cash = new Money("You see money", player);
        Items Key = new Items("There is a key!");
        player.addEquipment(Key);
        
        // create the rooms
        outside = new Room("outside the main entrance of the bank", drink);
        Bank = new Room("Inside the bank", cash);
        Toilets = new Room("in the toilets", drink);
        Staff= new Room("in a staff room", drink);
        garage = new Room("in the garage");
        corridor = new Room("in a corridor");
        Security = new Room("in the Security", Key);
        Safe = new Room("in the Safe room", cash);
        
        // initialise room exits
        outside.setExit("east", Bank);
        outside.setExit("south", garage);

        Bank.setExit("west", outside);
        Bank.setExit("south", Staff);

        Staff.setExit("west", garage);
        Staff.setExit("east", corridor);
        Staff.setExit("north", Bank);
        
        garage.setExit("north", outside);
        garage.setExit("east", Staff);

        corridor.setExit("south", Toilets);
        corridor.setExit("east", Security);
        corridor.setExit("west", Staff);
        
        Toilets.setExit("north", corridor);

        Security.setExit("east", Safe);
        Security.setExit("west", corridor);
        
        Safe.setExit("west", Security);
        
        currentRoom = outside;  // start game outside
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        
        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
              
            case Take:
                wantToQuit = quit(command);
                break;
                
            case Use:
                wantToQuit = quit(command);
                break;
                
            case Equipment:
                 player.showEquipment();
                break;
                
            case player:
                showPlayer();
                break;
        }
        return wantToQuit;
    }

    public void showPlayer()
    {
        System.out.println("Energy:"+ player.getEnergy());
        System.out.println("Score:" + player.getScore());
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
