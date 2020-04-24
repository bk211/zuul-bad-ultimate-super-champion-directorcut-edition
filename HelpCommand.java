/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
 */
public class HelpCommand extends Command
{
    /**
     * Constructor for objects of class HelpCommand
     */
    public HelpCommand()
    {
    }

       /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        gameView.printHelp();
    }

    
}
