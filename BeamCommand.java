/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
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
     * loop closely inside the room, print out the long description of the current Room
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

