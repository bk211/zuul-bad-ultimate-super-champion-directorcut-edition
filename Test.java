public class Test{

    public static void main(String[] args) {
        RoomFileReader foo = new RoomFileReader("RoomData.csv");
        System.out.println(foo.ParseRooms());
        System.out.println(foo.ParseRooms().get("cuisine").getLongDescription());
    }
}