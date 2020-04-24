import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
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
     * loop closely inside the room, print out the long description of the current Room
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
