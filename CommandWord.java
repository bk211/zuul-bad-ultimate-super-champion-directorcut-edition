/*** Representations for all the valid command words for the game* along with a string in a particular language.* 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
*/

public enum CommandWord{    
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("sos"), LOOK("look"), EAT("eat"), BACK("back"),TEST("test"),TAKE("take"),DROP("drop"),MINE("mine"), UNKNOWN("?"),EQUIP("equip");

    // The command string.
    private String commandString;
    /*** 
     * Initialize with the corresponding command word.
     * @param commandString The command string.
    */
    CommandWord(String commandString){
        this.commandString = commandString;
    }
    /*** 
     * @return The command word as a string.
    */
    public String toString(){
        return commandString;
    }
}