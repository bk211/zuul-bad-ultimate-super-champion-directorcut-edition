import java.util.ArrayList;
public class Player {
    private String name;
    private Room currentRoom;
    private  double weight ;
    private ArrayList<Item> items;
    private Beamer beamer;
   // private ArrayList<Equipment> equipments;

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.weight = 0;
        this.items = new ArrayList<Item>();
        this.beamer = new Beamer();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
        if(beamer.ready()){// if beamer is equipped 
            beamer.setStatus(true);
        }
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

    public void setBeamer(Item b){
        this.beamer = new Beamer(b);
    }

    public Boolean charge(){
        if(beamer.getStatus()){
            beamer.setStatus(false);
            beamer.setCharge(beamer.getCharge() + 1);
            return true;
        }
        return false;
    }

    public Boolean fire(){
        if(beamer.getCharge() >= 2){
            return true;
        }

        return false;
    }

    public void setBeamerLocation(Room r){
        beamer.setLocation(r);
        beamer.setCharge(0);//reset charge counter
    }

    public Boolean teleport(){
        return true;
    }

    public Room getBeamerRoom(){
        return beamer.getLocation();
    }
}
