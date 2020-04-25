package pkg_tools;
import pkg_data.Player;
import pkg_mainStruct.GameModel;
import pkg_mainStruct.GameView;


/**
 * This class is an abstract superclass for all command classes in the game.
 * Each user command is implemented by a specific command subclass.
 *
 * Objects of class Command can store an optional argument word (a second
 * word entered on the command line). If the command had only one word, 
 * then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2.0 (December 2002)
 */

public abstract class Command
{
    private String secondWord;

    /**
     * Create a command object. 
     */
    public Command()
    {
        secondWord = null;
    }


    /**
     * return the second word
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * check is the command have a second word
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }

    /**
     * Execute this command. 
     * @param player the player
     * @param gameModel the game model
     * @param gameView the game view
     * @return True, if game should exit; false otherwise.
     */
    public abstract void execute(Player player, GameModel gameModel,GameView gameView);

    /**
     * Define the second word of this command (the word
     * entered after the command word). Null indicates that 
     * there was no second word.
     * @param secondWord the second word
     */
    public void setSecondWord(String secondWord)
    {
        this.secondWord = secondWord;
    }
}

