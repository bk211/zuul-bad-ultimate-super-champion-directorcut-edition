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
            room.setImageLink(bufferArray.get(2));

            bufferArray = records.get(i+1);//lecture second ligne: exits
            for (int j = 0; j < bufferArray.size(); j+=2) {
                ArrayList<String> bufferExits = new ArrayList<String>();
                bufferExits.add(name);
                bufferExits.add(bufferArray.get(j));
                bufferExits.add(bufferArray.get(j+1));
                exitsStack.add(bufferExits);
            }

            //System.out.println("exitistack :"+exitsStack);
            
            bufferArray = records.get(i+2);//lecture items
            for (int j = 0; j < bufferArray.size(); j+=2) {
                String itemName = bufferArray.get(j);
                if(itemName == "NULL"){
                    itemName = "No description avaible for this item";
                }
                double itemWeight = Double.parseDouble(bufferArray.get(j+1));
                room.addItem(new Item(itemName, itemWeight));
                
            }

            rooms.put(name, room);
        }

        for (ArrayList<String> exitsList : exitsStack) {// ajouts des exits 
            System.out.println(exitsList);
            Room src = rooms.get(exitsList.get(0));
            Room dst = rooms.get(exitsList.get(2));
            src.setExit(exitsList.get(1), dst);
        }
        return rooms;
    }
}