
public class Beamer extends Item {
    
    int status = 0; 
    int charge;
    Room location;

    public Beamer(Item item){
        this.name = item.name;
        this.description = item.description;
        this.weight = item.weight;
        this.charge = 0;
    }

    private void fire(Room loc){
        this.location = loc;
        this.status = 0;
        this.charge = 0;
    }
}