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
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room parking = new Room("in the parking");
        Room aile_gauche = new Room("in the left wing");
        Room cargo1 = new Room("in the cargo1");
        Room cargo2 = new Room("in the cargo2");
        Room dock1 = new Room("in the dock1");
        Room dock2 = new Room("in the dock2");
        Room reactor = new Room("in the reactor room");
        Room rest = new Room("in the rest room");
        Room vestiaire = new Room("in the change room");
        Room aile_droite = new Room("in the right wing");
        Room cuisine = new Room("in the kitchen");
        Room escalier = new Room("in the staires");
        Room hall = new Room("in the Hall");
        Room commandement = new Room("in the head quarter");

        
        parking.setExit("east", aile_gauche);
        cargo2.setExit("cargo1", cargo1);
        cargo1.setExit("north", cargo2);
        cargo1.setExit("south", aile_gauche);
        cargo1.setExit("east", rest);
        aile_gauche.setExit("north", cargo1);
        aile_gauche.setExit("south", dock1);
        aile_gauche.setExit("up", hall);
        dock1.setExit("north", aile_gauche);
        reactor.setExit("sud", rest);
        rest.setExit("north", reactor);
        rest.setExit("west", cargo1);
        rest.setExit("south", hall);
        rest.setExit("east", vestiaire);
        hall.setExit("north", rest);
        hall.setExit("west", aile_gauche);
        hall.setExit("south", commandement);
        hall.setExit("east", aile_droite);
        commandement.setExit("north", hall);
        vestiaire.setExit("west", rest);
        vestiaire.setExit("south", aile_droite);
        aile_droite.setExit("north", vestiaire);
        aile_droite.setExit("west", hall);
        aile_droite.setExit("south", cuisine);
        aile_droite.setExit("east", dock2);
        dock2.setExit("north", aile_droite);
        cuisine.setExit("west", aile_droite);
        cuisine.setExit("south", escalier);
        escalier.setExit("north", cuisine);
        
        
        //setExits(Room north, Room east, Room south, Room west) 
        // initialise room exits
        currentRoom = parking;  // start game outside

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
        while (! finished) {
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
        System.out.println("Type 'help' if you need help.");
        System.out.println();

        printLocationInfo();

    }

    /**
     * print out all exit of the currentRomm location
     */
    private void printLocationInfo()
    {
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

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("eat"))
            eat();
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        return wantToQuit;
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
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
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
            printLocationInfo();
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

    private void look(){
        System.out.println(currentRoom.getLongDescription());
    }

    private void eat(){
        System.out.println("There are no food");
    }

}
