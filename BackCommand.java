/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
 */
public class BackCommand extends Command
{
    /**
     * Constructor for objects of class BackCommand
     */
    public BackCommand(){
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
    * Try to go Back to one direction. If there is an visited Room, enter
    * that room, otherwise print an error message.
    * @param command the command to be analyzed
    */
    public void execute(Player player, GameModel gameModel,GameView gameView){
        if(hasSecondWord()) {
            gameView.show("Back what?\n");
        }
        else if(gameModel.getPastRooms().empty()){
            // si la pile est vide
            gameView.show("No record of last visited room");
        }else{
            // la commande est valide
            // Try to leave current room.

            Room pastRoom = gameModel.getPastRooms().pop();
            Room currentRoom = player.getCurrentRoom();
            if(pastRoom.getStateExit(currentRoom)==0) {
                goBack(pastRoom, gameModel, player, gameView);
                if (player.beam1()) {
                    player.incCpt();
                    if (player.getCpt() == 1) {
                        player.setCheckpoint(pastRoom);
                        gameView.show("beamer charged you can use it in the next room");
                    } else if (player.getCpt() >= 2) {
                        gameView.show("beamer can be used\n");
                        player.setUsed(true);
                    }
                }
            }
            else {
                if(!player.key()) {
                    gameView.show("\nyou don't have a key to back to this room use look to find a key \n");
                }else{
                    gameView.show("\nyou opened the door \n");

                    goBack(pastRoom, gameModel, player, gameView);
                }
            }
        }
    }

    
}
