public class Item{
    String description;
    double weight;
    
    public Item(){
        description = "No description avaible for this item";
        weight = 0;
    }

    public Item(String des, double w){
        description = des;
        weight = w;
    }

    public String getDescription(){
        return description;
    }

    public double getWeight(){
        return weight;
    }

    public void setDescription(String des){
        description = des;
    }

    public void setWeight(double w){
        weight = w;
    }

}