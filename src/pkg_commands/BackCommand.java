package pkg_commands;
import pkg_mainStruct.GameModel;
import pkg_mainStruct.GameView;
import pkg_data.Player;
import pkg_data.Room;
import pkg_tools.Command;

/**
 * Implementation of the 'back' user command.
 * 
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
     * @param gm the gamemodel
     * @param player the player
     * @param gameView gameView
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
     * Excute methode for back command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
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
