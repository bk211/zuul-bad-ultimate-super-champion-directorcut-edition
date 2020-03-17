public class Item{
    String name;
    String description;
    double weight;
    
    /**
     * default constructor, if no option are given, a default description will be set and weight is 0
     */
    public Item(){
        description = "No description avaible for this item";
        weight = 0;
    }

    /**
     * construtor that set description and weigth with arguments given
     * @param des the description
     * @param w the weight
     */
    public Item(String des, double w){
        description = des;
        weight = w;
    }

    public Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * return the description of the Item
     * @return the description
     */
    public String getDescription(){
        return description;
    }

    /**
     * return the weight of the object
     * @return the weight
     */
    public double getWeight(){
        return weight;
    }

    /**
     * Set the description with the 'des' argument
     * @param des the description
     */
    public void setDescription(String des){
        description = des;
    }

    /**
     * Set the weigth with the 'w' argument
     * @param w the weight
     */
    public void setWeight(double w){
        weight = w;
    }

}