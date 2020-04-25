/**
 * Implementation of the 'mine' user command.
 * 
 */
public class MineCommand extends Command
{
    /**
     * Constructor for objects of class MineCommand
     */
    public MineCommand()
    {
    }

    /**
     * Excute methode for back command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
       
        if(player.getItems().size()==0)
            gameView.show("i am poor i only have \n");
        for(Item i:player.getItems())
            gameView.show(i.getName() + "\n");
        gameView.show(Double.toString(player.getWeight()) + "\n");
        
    }
}

