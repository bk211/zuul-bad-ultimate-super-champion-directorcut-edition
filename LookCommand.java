/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
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
     * loop closely inside the room, print out the long description of the current Room
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        gameView.show(player.getCurrentRoom().getLongDescription());
    }
    

    
}
