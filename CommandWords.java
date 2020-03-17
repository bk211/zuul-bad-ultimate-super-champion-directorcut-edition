/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.HashMap;

public class CommandWords
{

    private HashMap<String, CommandWord> validCommands;


    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for (CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN){
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @param aString the given String
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * return a String that containg all valid command word
     * @return the result String
    */
    public String getCommandList(){
        String result = "";
        for (String command : validCommands.keySet()) {
            result += command + " ";
        }
        return result;
    }
    public void showAll()
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
