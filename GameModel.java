import java.util.Observable;
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
    
    public GameModel()
    {
        createRooms();
        this.parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room parking = new Room("in the parking");
        rooms.put("parking", parking);

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

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void goRoom(Room nextRoom)
    {
        currentRoom = nextRoom;
        setChanged();
        notifyObservers();
    }
    
    public String getWelcomeString() 
    {
        return "Welcome to the World of Zuul!" + "\n" + 
               "World of Zuul is a new, incredibly boring adventure game.";
    }
    
    public String getGoodByeString()
    {
        return "Thank you for playing.  Good bye.";
    }
    
    public String getHelpString()
    {
        return "You are lost. You are alone. You wander" + "\n" +
                "around at the university."
                +"\n"+ "Your command words are: ";
    }
    
    public String getLocationInfo() {
        return "You are " + getCurrentRoom().getDescription() + "\n" +
                getCurrentRoom().getExitString();
    }

    public String getCommandString(){
        return parser.showCommands();
    }
    
    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = gameModel.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            gameView.show("There is no door!");
        }
        else {
            gameModel.goRoom(nextRoom);
        }
    }
}