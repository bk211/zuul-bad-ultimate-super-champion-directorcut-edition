/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
 */
public class QuitCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public QuitCommand()
    {
    }

       /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        if(hasSecondWord()) {
            gameView.show("Quit what?\n");
        }
        else {
            gameView.disable();    
        }
    }

    
}
