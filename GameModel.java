import java.util.Observable;
import java.util.Stack;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

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
    private double max_weight = 6.0;

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
     * return the currentRoom object
     * @return currentRoom , the cuurrent location Room
     */



    /**
     * go to the next room, also change the picture of the display,
     * @param nextRoom the room that the player will go to
     */
    public void goRoom(Room nextRoom)
    {
        pastRooms.add(p1.getCurrentRoom());
        p1.setCurrentRoom(nextRoom);
        if(p1.getCurrentRoom().getImageLinkString() != null){
            gameView.showImage(p1.getCurrentRoom().getImageLinkString());
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

        p1.setCurrentRoom(lastRoom);
        if(p1.getCurrentRoom().getImageLinkString() != null){
            gameView.showImage(p1.getCurrentRoom().getImageLinkString());
        }
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


    public void timeOut(){
        if(pastRooms.size()==20) {
            gameView.show("Time ouuuuuuuutt\n");
            interpretCommandString("quit");
        }
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
        Room nextRoom = p1.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            gameView.show("There is no door!\n");
        }
        else {
            goRoom(nextRoom);
        }
    }
    public void test_file(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("test what ?");
            return;
        }
        String file = command.getSecondWord();
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                interpretCommandString(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void take(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("take what ?\n");
            return;
        }
        String itemName = command.getSecondWord();
        ArrayList<Item> roomItem = p1.getCurrentRoom().getItems();
        for(int i=0;i<roomItem.size();i++){
            if(roomItem.get(i).getName().equals(itemName) && !itemName.equals("magic_cookie")) {
                if(p1.getWeight()>max_weight || p1.getItems().size() > 5) {
                    gameView.show("it's enough for me ?\n");
                    return;
                }
                gameView.show("i took the " + itemName+ "\n");
                ArrayList<Item> newStateOfList  = p1.getItems();
                newStateOfList.add(roomItem.get(i));
                double total_weight = p1.getWeight() + roomItem.get(i).getWeight();
                p1.setWeight(total_weight);
                p1.setItems(newStateOfList);

                ArrayList<Item> roomListItems  = p1.getCurrentRoom().getItems();
                roomListItems.remove(i);
                p1.getCurrentRoom().setItems(roomListItems);



            }
            else
                gameView.show("there is no item who have this name in this room \n");
        }
    }
    public void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("drop what ? \n");
            return;
        }
        String itemName = command.getSecondWord();
        ArrayList<Item> pItem = p1.getItems();
        for(int i=0;i<pItem.size();i++){
            if(pItem.get(i).getName().equals(itemName)) {

                gameView.show("i droped the " + itemName +"\n");

                ArrayList<Item> roomListItems  = p1.getCurrentRoom().getItems();
                roomListItems.add(pItem.get(i));
                p1.getCurrentRoom().setItems(roomListItems);

                double total_weight = p1.getWeight() - pItem.get(i).getWeight();
                p1.setWeight(total_weight);

                ArrayList<Item> newStateOfList  = p1.getItems();
                newStateOfList.remove(i);
                p1.setItems(newStateOfList);


            }
            else
                gameView.show("i already don't have this item \n");
        }
    }
    public void mine(){
        if(p1.getItems().size()==0)
            gameView.show("i am poor i only have \n");
        for(Item i:p1.getItems())
            gameView.show(i.getName() + "\n");
        gameView.show(Double.toString(p1.getWeight()) + "\n");
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

            if(command.isUnknown()) {
                gameView.show("I don't know what you mean...\n");
                return false;
            }
            CommandWord commandWord = command.getCommandWord();
            switch (commandWord){
                case HELP:
                    gameView.printHelp();
                    break;
                case GO:
                    goRoom(command);
                    break;
                case BACK:
                    goBack(command);
                    break;
                case LOOK:
                    look();
                    break;
                case EAT:
                    eat();
                    break;
                case TEST:
                    test_file(command);
                    break;
                case TAKE:
                    take(command);
                    break;
                case DROP:
                    drop(command);
                    break;
                case MINE:
                    mine();
                    break;
                case EQUIP:
                    equip(command);
                    break;
                case CHARGE:
                    charge();
                    break;
                case FIRE:
                    fire();
                    break;
                case TELEPORT:
                    teleport();
                    break;
                case QUIT:
                    if (confirmQuit(command) == true){
                        gameView.disable();
                    }
                    break;
                default:
                    break;

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
        gameView.show(p1.getCurrentRoom().getLongDescription());
    }

    private void eat(){
        Item s = new Item("magic_cookie","increase your weight capacity", 0.0);
        for(int i=0;i<p1.getCurrentRoom().getItems().size();i++) {
            if (p1.getCurrentRoom().getItems().get(i).getName().equals("magic_cookie")) {
                gameView.show("magic cookie eaaten \n");
                max_weight += 3.0;
                gameView.show("now your weight capacity is " + Double.toString(max_weight) + "\n");
            } else {
                gameView.show("noooo magic cookie \n ");
            }
        }
    }


    private void equip(Command command){        
        if(!command.hasSecondWord()) {
        // if there is no second word, we don't know what to equip...
            gameView.show("equip what ?\n");
            return;
        }
        String itemName = command.getSecondWord();
        ArrayList<Item> roomItem = p1.getCurrentRoom().getItems();
        Boolean found = false; 
        for(int i=0;i<roomItem.size();i++){
            if(roomItem.get(i).getName().equals(itemName) && itemName.equals("beamer")) {
                p1.setBeamer(roomItem.get(i));
                found = true;

                p1.getCurrentRoom().getItems().remove(i);
                p1.getCurrentRoom().setItems(p1.getCurrentRoom().getItems());
            }
        }

        if(found){
            gameView.show("Equip succes \n");
        }else{
            gameView.show("Equipment not found\n");    
        }
    }

    private void charge(){
        if(p1.charge()){
            gameView.show("charge succes\n");
        }else{
            gameView.show("charge failed\n");    
        }
    }
    private void fire(){
        if(p1.fire()){
            gameView.show("fire succes\n");
            p1.setBeamerLocation(p1.getCurrentRoom());;
        }else{
            gameView.show("fire failed\n");    
        }
    }
    private void teleport(){
        Room targetRoom = p1.getBeamerRoom();
        if(targetRoom != null){//if valide fired beamer
            goRoom(targetRoom);
        }else{
            gameView.show("Beamer location not found, please fire the beamer\n");
        }
    }

}