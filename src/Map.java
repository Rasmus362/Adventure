public class Map {

    private Room startRoom;

    public Map() {

        Room room1 = new Room("Room 1", "Dette er det første rum.");
        Room room2 = new Room("Room 2", "Dette er det andet rum.");
        Room room3 = new Room("Room 3", "Dette er det tredje rum.");

        room1.setNorth(room2);
        room2.setSouth(room1);

        room2.setEast(room3);
        room3.setWest(room2);

        startRoom = room1;
    }

    public Room getStartRoom() {
        return startRoom;
    }
}