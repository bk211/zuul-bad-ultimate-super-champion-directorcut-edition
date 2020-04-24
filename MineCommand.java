/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
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
     * loop closely inside the room, print out the long description of the current Room
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
       
        if(player.getItems().size()==0)
            gameView.show("i am poor i only have \n");
        for(Item i:player.getItems())
            gameView.show(i.getName() + "\n");
        gameView.show(Double.toString(player.getWeight()) + "\n");
        
    }
}

