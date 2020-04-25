package pkg_commands;
import pkg_mainStruct.GameModel;
import pkg_mainStruct.GameView;
import pkg_data.Player;
import pkg_tools.Command;
/**
 * Implementation of the 'look' user command.
 * 
 */
public class LookCommand extends Command
{
    /**
     * Constructor for objects of class LookCommand
     */
    public LookCommand()
    {
    }

    /**
     * Excute methode for back command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        gameView.show(player.getCurrentRoom().getLongDescription());
    }
}
