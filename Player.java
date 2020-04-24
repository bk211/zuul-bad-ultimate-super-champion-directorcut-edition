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

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.weight = 0;
        this.items = new ArrayList<Item>();
        this.cpt = 0;
        this.checkpoint = null;
    }

    public double getMaxWeight(){
        return max_weight;
    }

    public void setMaxWeight(double w){
        this.max_weight = w;
    }


    public void setUsed(boolean v){
        this.used = v;
    }

    public boolean getUsed(){
        return used;
    }

    public void setCheckpoint(Room r){
        this.checkpoint = r;
    }    
    
    public Room getCheckpoint(){
        return checkpoint;
    }

    public void incCpt(){
        this.cpt +=1;
    }
    public void setCpt(int v){
        this.cpt = v;
    }
    
    public int getCpt(){
        return cpt;
    }


    public String getName(){
        return this.name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean key(){
        for(int i=0;i<getItems().size();i++){
            if(getItems().get(i).getName().equals("key")){
                return true;
            }
        }
        return false;
    }

    public boolean beam1(){
        for(int i=0;i<getItems().size();i++){
            if(getItems().get(i).getName().equals("beamer")){
                return true;
            }
        }
        return false;
    }

}
