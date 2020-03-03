import java.util.Observable;
import java.util.Stack;
import java.util.HashMap;

/**
 * GameModel represents the model of the game. This 
 * 
 * @author Poul Henriksen
 * @version  1.0 (February 2005)
 */
public class GameModel extends Observable
{
    
    private Room currentRoom;
    private Parser parser;
    private HashMap<String,Room> rooms = new HashMap<String, Room>();
    private Stack<Room> pastRooms;
    private GameView gameView;

    /**
     * default constructor for this class
     */
    public GameModel()
    {
        pastRooms = new Stack<Room>();
        createRooms();
        this.parser = new Parser();
    }

    /**
     * Set gm as gameView
     * @param gm the given GameView object
     */
    public void addGameView(GameView gm){
        gameView = gm;
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
        
        parking.setImageLink("img/castle.gif");
        aile_gauche.setImageLink("img/courtyard.gif");
        rooms.put("parking", parking);
        
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
     * return the currentRoom object
     * @return currentRoom , the cuurrent location Room
     */

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * go to the next room, also change the picture of the display,
     * @param nextRoom the room that the player will go to
     */
    public void goRoom(Room nextRoom)
    {
        pastRooms.add(currentRoom);
        currentRoom = nextRoom;
        if(currentRoom.getImageLinkString() != null){
            gameView.showImage(currentRoom.getImageLinkString());
        }

        setChanged();
        notifyObservers();
    }

    /**
     * go to the last visited room, also change the picture of the display
     * @param lastRoom the last visited room
     */
    
    public void goBack(Room lastRoom)
    {
        currentRoom = lastRoom;
        if(currentRoom.getImageLinkString() != null){
            gameView.showImage(currentRoom.getImageLinkString());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * return the path to the image as String
     * @return the imageName
     */
    
    public String getImageLinkString(){
        return currentRoom.getImageLinkString();
    }

    /**
     * return the welcome greeting String
     * @return greeting String
     */

    public String getWelcomeString() 
    {
        return "Welcome to the world of Albator,\n" 
        +"You are a space traveler pirate wandering in the Milky way \n"
        +"in the search of great treasure and exciting adventure  ";
    }
    
    /**
     * return the goobye String
     * @return goodbye String
     */
    public String getGoodByeString()
    {
        return "Thank you for playing.  Good bye.";
    }

    /**
     * return the help String
     * @return help String
     */
    public String getHelpString()
    {
        return "You are lost. You are alone. You wander" + "\n" +
                "around at the university."
                +"\n"+ "Your command words are: ";
    }

    /**
     * return the exit String
     * @return exit String
     */
    public String getExitString(){
        return "You have deicided to quit the game, see you soon! \n";
    }
    

    /**
     * return the description of the current Room and its exits
     * @return the current location info that contain the room description, and its exits
     */
    public String getLocationInfo() {
        return "You are " + getCurrentRoom().getDescription() + "\n" +
                getCurrentRoom().getExitString();
    }

    /**
     * return all commands availble
     * @return all commands availble for the user
     */
    public String getCommandString(){
        return parser.showCommands();
    }
    
    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command the input direction command
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            gameView.show("There is no door!\n");
        }
        else {
            goRoom(nextRoom);
        }
    }

    /**
     * Interpret the input String as a command and process it
     * @param userInput the String send by the user
     */
    public void interpretCommandString(String userInput){
        Command command = parser.getCommand(userInput);
        //debug line>>
        //System.out.println(">"+command.getSecondWord()+"<");
        processCommand(command);
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        gameView.printWelcome();
//        gameView.update(o, arg);
    }


    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {

        if(command.isUnknown()) {
            gameView.show("I don't know what you mean...\n");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")){
            gameView.printHelp();
        }
        else if (commandWord.equals("go")){
            goRoom(command);
        }
        else if (commandWord.equals("back")){
            goBack(command);
        }
        else if (commandWord.equals("look")){
            look();
        }
        else if (commandWord.equals("eat")){
            eat();
        }
        else if (commandWord.equals("quit")){
            if (confirmQuit(command) == true){
                gameView.disable();                
            }
        }

        return true;
    } 

    /** 
     * Try to go Back to one direction. If there is an visited Room, enter
     * that room, otherwise print an error message.
     * @param command the command to be analyzed
     */
    private void goBack(Command command){
        if(command.hasSecondWord()) {
            gameView.show("Back what?\n");
        }
        else if(pastRooms.empty()){
            // si la pile est vide
            gameView.show("No record of last visited room");
        }else{
            // la commande est valide
            // Try to leave current room.
            Room nextRoom = pastRooms.pop();
            goBack(nextRoom);
        }
    }


    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param command the confirmation command
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean confirmQuit(Command command) 
    {
        if(command.hasSecondWord()) {
            gameView.show("Quit what?\n");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * loop closely inside the room, print out the long description of the current Room
     */

    private void look(){
        gameView.show(getCurrentRoom().getLongDescription());
    }

    private void eat(){
        gameView.show("There are no food\n");
    }

}