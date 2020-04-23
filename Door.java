
public class Door {
    String exitName;
    Boolean status;

    public Door(String n){
        this.exitName = n;
        this.status = false;
    }    
    public Door(String n, boolean s){
        this.exitName = n;
        this.status = s;
    }

    public String getExitName(){
        return exitName;
    }

    public Boolean getStatus(){
        return status;
    }
}