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


    /**
     * Default constructor 
     * @param gameModel the given GameModel
     */
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
        show("Type '"+ CommandWord.HELP +"' if you need help.\n");
        printLocationInfo();
        userInterface.showImage(gameModel.getCurrentRoom().getImageLinkString());
    }
    
    /**
     * print out all exit of the currentRomm location
     */

    private void printLocationInfo()
    {
        show(gameModel.getLocationInfo());
    }
    
    /**
     * display the goodbye string
     */
    public void printGoodBye() 
    {
        show(gameModel.getGoodByeString());
        show(gameModel.getExitString());
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
   
    /**
     * display the input String
     * @param string the String to be displayed
     */
    public void show(String string) 
    {
        userInterface.print(string);
    }
   
    /**
     * display the image associated with its path name
     * @param imageName the path to the image
     */
    public void showImage(String imageName){
        userInterface.showImage(imageName);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        printLocationInfo();
        //String imageLink = gameModel.getImageLinkString();
        //debug line;
        //System.out.println(">>>" + imageLink);
        //userInterface.showImage();

    }

    /**
     * disable userinput and user interaction button (fake quit)
     */
    public void disable(){
        show(gameModel.getExitString());
        userInterface.enable(false);
    }
}
