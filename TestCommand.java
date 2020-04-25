import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Implementation of the 'test' user command.
 */
public class TestCommand extends Command
{
    /**
     * Constructor for objects of class TestCommand
     */
    public TestCommand()
    {
    }

    /**
     * Excute methode for test command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){

        if(!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("test what ?");
            return;
        }
        String file = getSecondWord();
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                gameModel.interpretCommandString(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    
}
