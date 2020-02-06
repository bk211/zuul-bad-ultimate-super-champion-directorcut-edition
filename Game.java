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
        Room prarie, ville, chateau, marchand1, guild, entrainement, marchand2;
        Room[] donjons = new Room[10];
        Room escalier, caverne, sortieCaverne, tour, boss, fin;
        // create the rooms
        prarie = new Room("dans une vaste prarie");
        ville = new Room("dans une grande ville");
        marchand1 = new Room("chez un marchand");
        marchand2 = new Room("chez un marchand");
        guild = new Room("dans un guild");
        entrainement = new Room("dans la salle d'entrainement");
        chateau = new Room("dans le chateau du seigneur");
        escalier = new Room("devant un escalier qui mène quelque part");
        caverne = new Room("devant un caverne");
        sortieCaverne = new Room("proche de la sortie du carvene");
        tour = new Room("devant un tour géant");
        boss = new Room("face au dragon");
        fin = new Room("dans la fin du jeu");

        for (int i = 0; i < donjons.length; i++) {
            donjons[i] = new Room("dans une salle du donjon");
        }

        //setExits(Room north, Room east, Room south, Room west) 
        // initialise room exits
        prarie.setExits(ville, null, donjons[1], null);
        ville.setExits(chateau, guild, prarie, marchand1);
        marchand1.setExits(null , ville, null, null);
        guild.setExits(entrainement, null, null, ville);
        donjons[1].setExits(null, donjons[2], donjons[3], prarie);
        donjons[2].setExits(null, donjons[6], donjons[4], donjons[1]);
        donjons[3].setExits(donjons[1], donjons[4], donjons[5], null);
        donjons[4].setExits(donjons[2], null, null, donjons[3]);
        donjons[5].setExits(donjons[3], null, marchand2, null);
        donjons[6].setExits(null, null, escalier, null);
        marchand2.setExits(null, escalier, null, null);
        escalier.setExits(null, null, donjons[7], null);
        donjons[7].setExits(null, caverne, null, null);
        caverne.setExits(null, sortieCaverne, null, donjons[7]);
        sortieCaverne.setExits(null, tour, null, caverne);
        tour.setExits(null, boss, null, sortieCaverne);
        boss.setExits(fin, null, null, null);

        currentRoom = prarie;  // start game outside

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
