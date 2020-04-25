package pkg_mainStruct;
import pkg_data.*;
import pkg_tools.*;
import java.util.Observable;
import java.util.Stack;
import java.util.HashMap;
//import java.util.Iterator;

/**
 * GameModel represents the model of the game. This 
 * 
 * @author Poul Henriksen
 * @version  1.0 (February 2005)
 */
public class GameModel extends Observable
{
    private Player p1;
    private Parser parser;
    private HashMap<String,Room> rooms;
    private Stack<Room> pastRooms;
    private GameView gameView;
    //private TransporterRoom tr ;



    /**
     * default constructor for this class
     */
    public GameModel()
    {
        pastRooms = new Stack<Room>();
        createRooms();
        this.parser = new Parser();
        //tr = new TransporterRoom("tr",rooms);
    }

    /**
     * return the player p1
     * @return player object
     */
    public Player getP1() {
        return p1;
    }

    /**
     * return the pastRoom siwe
     * @return pastRoom size
     */

    public int getPastRoomsSize() {
        return pastRooms.size();
    }

    /**
     * Set gm as gameView
     * @param gm the given GameView object
     */
    public void addGameView(GameView gm){
        gameView = gm;
    }

    /**
     * return the currentRoom object
     * @return currentRoom , the cuurrent location Room
     */

    public Room getCurrentRoom() {
        return p1.getCurrentRoom();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        rooms = new RoomFileReader("RoomData.csv").ParseRooms();
        this.p1 = new Player("player1",rooms.get("parking"));
        p1.setCurrentRoom(rooms.get("parking"));  // start game outside
    }

    /**
     * set change et notify all observers
     */
    public void notifyChange(){
        setChanged();
        notifyObservers();    
    }

    /**
     * return the path to the image as String
     * @return the imageName
     */
    
    public String getImageLinkString(){
        return p1.getCurrentRoom().getImageLinkString();
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
     * check if the game time is over
     */
    public void timeOut(){
        if(pastRooms.size()==20) {
            gameView.show("Time ouuuuuuuutt\n");
            interpretCommandString("quit");
        }
    }

    /**
     * add a room to the pastRoom
     * @param r the room
     */
    public void addPastRoom(Room r){
        pastRooms.add(r);
    }

    /**
     * return the pastRooms
     * @return the pastRooms
     */
    public Stack<Room> getPastRooms(){
        return pastRooms;
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
        return "You are " + p1.getCurrentRoom().getDescription() + "\n" +
                p1.getCurrentRoom().getExitString();
    }

    /**
     * return all commands availble
     * @return all commands availble for the user
     */
    public String getCommandString(){

        return parser.showCommands();
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
    }



    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {

            if(command == null){
                gameView.show("I don't know what you mean...\n");
                return false;
            }else{
                command.execute(p1, this ,gameView);
            }

            return true;
    }

}