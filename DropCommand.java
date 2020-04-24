import java.util.ArrayList;
/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
 */
public class DropCommand extends Command
{
    /**
     * Constructor for objects of class DropCommand
     */
    public DropCommand()
    {
    }

    /**
     * loop closely inside the room, print out the long description of the current Room
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        if(!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("drop what ? \n");
            return;
        }
        String itemName = getSecondWord();
        ArrayList<Item> pItem = player.getItems();
        for(int i=0;i<pItem.size();i++){
            if(pItem.get(i).getName().equals(itemName)) {
    
                gameView.show("i droped the " + itemName +"\n");
    
                ArrayList<Item> roomListItems  = player.getCurrentRoom().getItems();
                roomListItems.add(pItem.get(i));
                player.getCurrentRoom().setItems(roomListItems);
    
                double total_weight = player.getWeight() - pItem.get(i).getWeight();
                player.setWeight(total_weight);
    
                ArrayList<Item> newStateOfList  = player.getItems();
                newStateOfList.remove(i);
                player.setItems(newStateOfList);
    
    
            }
            else
                gameView.show("i already don't have this item \n");
        }
    }
}

