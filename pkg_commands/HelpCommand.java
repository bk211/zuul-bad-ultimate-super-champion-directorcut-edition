package pkg_commands;
import pkg_mainStruct.GameModel;
import pkg_mainStruct.GameView;
import pkg_data.Player;
import pkg_tools.Command;
/**
 * Implementation of the 'help' user command.
 * 
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
     * Excute methode for help or sos command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        gameView.printHelp();
    }

    
}
