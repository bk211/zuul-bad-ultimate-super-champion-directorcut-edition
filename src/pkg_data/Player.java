package pkg_data;
import java.util.ArrayList;
public class Player {
    private String name;
    private Room currentRoom;
    private double weight ;
    private ArrayList<Item> items;
    private int cpt;
    private Room checkpoint;
    private boolean used=false;
    private double max_weight = 6.0;

    /**
     * defaut constructor
     * @param name player name
     * @param currentRoom the current location rrom
     */
    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.weight = 0;
        this.items = new ArrayList<Item>();
        this.cpt = 0;
        this.checkpoint = null;
    }

    /**
     * return the max weight
     * @return max weight
     */
    public double getMaxWeight(){
        return max_weight;
    }

    /**
     * set the max weight
     * @param w weight
     */
    public void setMaxWeight(double w){
        this.max_weight = w;
    }

    /**
     * set the used boolean
     * @param v value
     */
    public void setUsed(boolean v){
        this.used = v;
    }

    /**
     * return used
     * @return used
     */
    public boolean getUsed(){
        return used;
    }

    /**
     * set the room r as checkpoint  
     * @param r checkpoint
     */
    public void setCheckpoint(Room r){
        this.checkpoint = r;
    }    

    /**
     * return the checkpoint
     * @return checkpoint
     */
    public Room getCheckpoint(){
        return checkpoint;
    }

    /**
     * increase the cpt by 1
     */
    public void incCpt(){
        this.cpt +=1;
    }

    /**
     * set the cpt with v
     * @param v value
     */
    public void setCpt(int v){
        this.cpt = v;
    }
    
    /**
     * return the cpt
     * @return cpt
     */
    public int getCpt(){
        return cpt;
    }

    /**
     * return player name
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * return the current room
     * @return currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * set the current room 
     * @param currentRoom current Room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * return items
     * @return items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * set items as item att
     * @param items items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * return the cucurent weight
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * set the weight 
     * @param weight weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * return if the player has a key
     * @return has key answer
     */
    public boolean key(){
        for(int i=0;i<getItems().size();i++){
            if(getItems().get(i).getName().equals("key")){
                return true;
            }
        }
        return false;
    }

    /**
     * return if the player has a beamer
     * @return true if player have beamer else false
     */
    public boolean beam1(){
        for(int i=0;i<getItems().size();i++){
            if(getItems().get(i).getName().equals("beamer")){
                return true;
            }
        }
        return false;
    }

}
