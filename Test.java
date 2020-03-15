public class Test{

    public static void main(String[] args) {
        RoomFileReader foo = new RoomFileReader("RoomData.csv");
        //System.out.println(foo.records);
        System.out.println(foo.ParseRooms());
        
    }
}