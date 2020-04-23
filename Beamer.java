
public class Beamer extends Item {
    Boolean ready;
    Boolean status; 
    int charge;
    Room location;

    public Beamer(){
        this.ready = false;
    }

    public Beamer(Item item){
        this.name = item.name;
        this.description = item.description;
        this.weight = item.weight;
        this.charge = 0;
        this.ready = true;
        this.status = false;
        this.location = null;
    }


    public void fire(Room loc){
        this.location = loc;
        this.status = false;
        this.charge = 0;
    }

    public Boolean getStatus(){
        return status;
    }
    public Boolean ready(){
        return ready;
    }
    public int getCharge(){
        return charge;
    }

    public void setStatus(Boolean v){
        status = v;
    }

    public void setCharge(int v){
        charge = v;
    }

    public void setLocation(Room r){
        this.location = r;
    }

    public Room getLocation(){
        return location;
    }
}