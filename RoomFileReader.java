import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RoomFileReader{
    List<List<String>> records;

    /**
     * constructor for RFR
     * @param fileName the name of the file that contain the RoomData
     */
    public RoomFileReader(String fileName){
        records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[]values = line.split(",");
            records.add(Arrays.asList(values));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * return a hashmap/dictionary that contain all the rooms, linked correctly to each others
     * @return the dictionary that contain all the rooms
     */
    HashMap<String,Room> ParseRooms(){
        HashMap<String,Room> rooms = new HashMap<String, Room>();
        ArrayList<ArrayList<String>> exitsStack = new ArrayList<ArrayList<String>>();
        int size = records.size();
//        System.out.println(size);
        List<String> bufferArray;        

        for (int i = 0; i < size ; i+=3) {// creation des salles ainsi que leurs items
            bufferArray = records.get(i);//lecture premier ligne: name, description, imageName
            String name = bufferArray.get(0);
            Room room = new Room(bufferArray.get(1));
            String imageName =bufferArray.get(2);
            if(imageName != "NULL"){//si la salle a une image, sinon, elle est set a null par defaut
                room.setImageLink(bufferArray.get(2));
            } 
            bufferArray = records.get(i+1);//lecture second ligne: exits
            
            for (int j = 0; j < bufferArray.size(); j+=3) {
                ArrayList<String> bufferExits = new ArrayList<String>();
                bufferExits.add(name);
                bufferExits.add(bufferArray.get(j));
                bufferExits.add(bufferArray.get(j+1));
                bufferExits.add(bufferArray.get(j+2));
                exitsStack.add(bufferExits);
            }

            bufferArray = records.get(i+2);//lecture items
            if(!bufferArray.get(0).equals("NONE")){
                
                for (int k = 0; k < bufferArray.size(); k+=3) {
                    String itemName = bufferArray.get(k);
                    String itemDescription = bufferArray.get(k+1);
                    if(itemDescription == "NULL"){
                        itemDescription = "No description avaible for this item";
                    }
                    double itemWeight = Double.parseDouble(bufferArray.get(k+2));
                    room.addItem(new Item(itemName,itemDescription, itemWeight));
                }            
            }

            rooms.put(name, room);
            
        }


        for (ArrayList<String> exitsList : exitsStack) {// ajouts des exits 
            //System.out.println(exitsList);
            Room src = rooms.get(exitsList.get(0));
            Room dst = rooms.get(exitsList.get(3));
            src.setExit(exitsList.get(1), dst);
            src.setDoor(exitsList.get(1), Boolean.valueOf(exitsList.get(2)));
        }
        return rooms;
    }
}