/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
 */
public class GoCommand extends Command
{
    /**
     * Constructor for objects of class GoCommand
     */
    public GoCommand()
    {
    }

    /**
     * go to the next room, also change the picture of the display,
     * @param nextRoom the room that the player will go to
     */
    public void goRoom(Room nextRoom, GameModel gm, Player player, GameView gameView)
    {
        gm.addPastRoom(player.getCurrentRoom());
        player.setCurrentRoom(nextRoom);
        if(player.getCurrentRoom().getImageLinkString() != null){
            gameView.showImage(player.getCurrentRoom().getImageLinkString());
        }
        gm.notifyChange();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){

        if(!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("Go where?");
            return;
        }

        String direction = getSecondWord();

        // Try to leave current room.
        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            gameView.show("There is no door!\n");
        }
        else {
            if (currentRoom.getStateExit(nextRoom) == 0||currentRoom.getStateExit(nextRoom) == 1 ) {
                goRoom(nextRoom,  gameModel, player, gameView);
                if (player.beam1()) {
                    player.incCpt();
                    if (player.getCpt() == 1) {
                        player.setCheckpoint(nextRoom);
                        gameView.show("beamer charged you can use it in the next room");
                    } else if (player.getCpt() >= 2) {
                        gameView.show("beamer can be used\n");
                        player.setUsed(true);
                    }
                }

            }
            else {
                if(!player.key()) {
                    gameView.show("\nlooocked rooom right here find a key to open it\n");
                }else{
                    goRoom(nextRoom,  gameModel, player, gameView);
                }
            }
        }

    
    }

    
}




