/**
 * Implementation of the 'eat' user command.
 */
public class EatCommand extends Command
{
    /**
     * Constructor for objects of class EatCommand
     */
    public EatCommand()
    {
    }

    /**
     * Excute methode for eat command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        for(int i=0;i<player.getCurrentRoom().getItems().size();i++) {
            if (player.getCurrentRoom().getItems().get(i).getName().equals("magic_cookie")) {
                gameView.show("magic cookie eaaten \n");
                player.setMaxWeight(player.getMaxWeight() + 3);
                gameView.show("now your weight capacity is " + Double.toString(player.getMaxWeight()) + "\n");
            } else {
                gameView.show("noooo magic cookie \n ");
            }
        }
    
    }
    

    
}
