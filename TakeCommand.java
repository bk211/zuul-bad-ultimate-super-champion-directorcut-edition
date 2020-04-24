import java.util.ArrayList;
/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
 */
public class TakeCommand extends Command
{
    /**
     * Constructor for objects of class TakeCommand
     */
    public TakeCommand()
    {
    }

    /**
     * loop closely inside the room, print out the long description of the current Room
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        if(!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("take what ?\n");
            return;
        }
        String itemName = getSecondWord();
        ArrayList<Item> roomItem = player.getCurrentRoom().getItems();
        for(int i=0;i<roomItem.size();i++){
            if(roomItem.get(i).getName().equals(itemName) && !itemName.equals("magic_cookie")) {
                if(player.getWeight()>player.getMaxWeight() || player.getItems().size() > 5) {
                    gameView.show("it's enough for me ?\n");
                    return;
                }

                gameView.show("i took the " + itemName+ "\n");

                ArrayList<Item> newStateOfList  = player.getItems();
                newStateOfList.add(roomItem.get(i));
                double total_weight = player.getWeight() + roomItem.get(i).getWeight();
                player.setWeight(total_weight);
                player.setItems(newStateOfList);

                ArrayList<Item> roomListItems  = player.getCurrentRoom().getItems();
                roomListItems.remove(i);
                player.getCurrentRoom().setItems(roomListItems);

            }
            else
                gameView.show("there is no item who have this name in this room \n");
        }
    }

    
    

    
}
