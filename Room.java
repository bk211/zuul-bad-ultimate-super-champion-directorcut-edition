import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private String imageLink;
    private ArrayList<Item> items;

    /**
     * Add the item i to the items collection
     * @param i the item that will be add to the items collection
     */
    public void addItem(Item i){
        items.add(i);
    }

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        imageLink = null;
        items = new ArrayList<Item>();
    }



    /*** Return the room that is reached if we go from this 
     * room in direction "direction". If there is no room in 
     * that direction, return null.
     * @param direction the exit that we want to reach
     * @return the avaible exit string, otherwise null
    */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }


    /**
     * set imageName as imageLink
     * @param imageName the path to the image file 
     */
    public void setImageLink(String imageName){
        imageLink = imageName;
    }
    
    /**
     * return path to the Image file
     * @return the name of the image
     */
    public String getImageLinkString(){
        return imageLink;
    }

    /**
     * Return a description of the room's exits,* for example, "Exits: north west".
     * @return A description of the available exits.
    */
    public String getExitString(){
        String exitString = "Exits: ";
        
        for (String keys : exits.keySet()) {
            exitString += keys + " ";
        }
        return exitString +"\n";
    }
    

    
    /**
     * Define an exit from this room.
     *  @param direction The direction of the exit.
     *  @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    
    /**
     * Define the exits of this room.  Every direction either 
     * leads to another room or is null (no exit there).
     * @param north the north direction
     * @param east the east direction
     * @param south the south direction
     * @param west the west direction
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
            exits.put("north", north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
            exits.put("south", south);
        if(west != null)
            exits.put("west", west);
    }

    /**  
     * Return a long description of this room, of the form:
     * You are in the kitchen.
     * Exits: north west
     * @return A description of the room, including exits.
    */
    public String getLongDescription(){
        return "You are " + description + ".\n"+ getItemsDescription() +"\n" + getExitString()+"\n";
    }

    /**
     * return a String that contain all the item's description cotainned in the Room
     * @return description of items in the room
     */
    public String getItemsDescription(){
        String result = "";
        for (Item item : items) {
            result += item.getDescription()+ "\n";
        }
        return result;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
