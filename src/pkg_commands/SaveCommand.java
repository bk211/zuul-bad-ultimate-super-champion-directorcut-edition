package pkg_commands;
import pkg_mainStruct.GameModel;
import pkg_mainStruct.GameView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pkg_data.Player;
import pkg_tools.Command;

/**
 * Implementation of the 'save' user command.
 */
public class SaveCommand extends Command
{
    /**
     * Constructor for objects of class SaveCommand
     */
    public SaveCommand()
    {
    }

    /**
     * Excute methode for save command
     * @param player the player
     * @param gameModel the gameModel
     * @param gameView the gameView
     */
    public void execute(Player player, GameModel gameModel,GameView gameView){

        if(!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gameView.show("save what ?");
            return;
        }
        String filename = getSecondWord();
        ArrayList<String> userHistory = gameModel.getUserHistory();
        userHistory.remove(userHistory.size() -1); // remove last instruction since it's the save instruction

        try {
            FileWriter writter = new FileWriter(filename);
            for (String str : userHistory) {
                writter.write(str+"\n");
            }
            writter.close();
            
        } catch (IOException e) {
            gameView.show("Error with the given file\n");
        }


    }
    

    
}
