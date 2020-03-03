import java.util.Observable;
import java.util.Observer;


/**
 * TextView is a textual view of the Zuul game. It prints out texts to the 
 * screen.
 * 
 * @author Poul Henriksen
 * @version  1.0 (February 2005)
 */
public class GameView implements Observer
{
    private GameModel gameModel;
    private UserInterface userInterface;


    public GameView(GameModel gameModel)
    {
        this.gameModel = gameModel;
        userInterface = new UserInterface();
        userInterface.addGameModel(gameModel);

    }
    
    /**
     * Print out the opening message for the player.
     */

    public void printWelcome()
    {
        show("\n" + gameModel.getWelcomeString() + "\n");
        show("Type 'help' if you need help.\n");
        printLocationInfo();
    }
    
    /**
     * print out all exit of the currentRomm location
     */

    private void printLocationInfo()
    {
        show(gameModel.getLocationInfo());
    }
    
    public void printGoodBye() 
    {
        show(gameModel.getGoodByeString());
    }

        /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    
    public void printHelp()
    {
        show(gameModel.getHelpString());
        show(gameModel.getCommandString());
    }
   
    public void show(String string) 
    {
        userInterface.print(string);
    }
   
    public void update(Observable o, Object arg)
    {
        printLocationInfo();
    }

    /**
     * disable userinput and user interaction button (fake quit)
     */
    public void disable(){
        userInterface.enable(false);
    }
}
