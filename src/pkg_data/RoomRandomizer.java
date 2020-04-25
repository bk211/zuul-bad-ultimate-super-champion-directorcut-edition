package pkg_data;
import java.util.HashMap;
import java.util.Random;
public class RoomRandomizer
{
    private HashMap <String, Room> myRooms;
    private Random randomize;

    public RoomRandomizer (final HashMap <String, Room> myRooms)
    {
        this.myRooms = myRooms;
        this.randomize = new Random();
    }

    public Room randomizeRooms()

    {
        Room[] roomTab = myRooms.values().toArray (new Room[0]);
        return roomTab[randomize.nextInt (myRooms.size())];
    }
}