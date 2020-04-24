
import java.util.HashMap;
//import java.util.Random;
public class TransporterRoom extends Room
{
    private  RoomRandomizer randomRoom;
    private HashMap <String, Room> myRooms;

    public TransporterRoom (final String description, final HashMap <String, Room> Rooms)
    {
        super(description);
        this.myRooms = Rooms;

    }

    @Override
    public Room getExit (final String direction)
    {
        randomRoom = new RoomRandomizer(myRooms);
        return randomRoom.randomizeRooms();

    }
}