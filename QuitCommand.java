/**
 * Implementation of the 'quit' user command.
 * 
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
     * Excute methode for back command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        if(hasSecondWord()) {
            gameView.show("Quit what?\n");
        }
        else {
            gameView.disable();    
        }
    }

    
}
