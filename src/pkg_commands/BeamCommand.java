package pkg_commands;
import pkg_mainStruct.GameModel;
import pkg_mainStruct.GameView;
import pkg_data.Player;
import pkg_data.Room;
import pkg_tools.Command;
/**
 * Implementation of the 'go' user command.
 * 
 */
public class BeamCommand extends Command
{
    /**
     * Constructor for objects of class BeamCommand
     */
    public BeamCommand()
    {
    }

    /**
     * go to the last visited room, also change the picture of the display
     * @param lastRoom the last visited room
     * @param gm the gameModel
     * @param player the player
     * @param gameView the gameView
     */
    public void goBack(Room lastRoom, GameModel gm, Player player, GameView gameView)
    {

        player.setCurrentRoom(lastRoom);
        if(player.getCurrentRoom().getImageLinkString() != null){
            gameView.showImage(player.getCurrentRoom().getImageLinkString());
        }
        gm.notifyChange();
    }

    /**
     * Excute methode for beam command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){

        if(player.getUsed()){
            goBack(player.getCheckpoint(), gameModel, player, gameView);
            gameView.show("beamer used\n");
            player.setUsed(false);
            player.setCpt(0);
        }
        else
            gameView.show("beamer uncharged\n");
    }
}

