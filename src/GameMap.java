public class GameMap {
    //GameMaps opgave er at bygge mappen
    private Room startRoom;

    public GameMap() {

        Room room1 = new Room("Room 1", "Entry");
        Room room2 = new Room("Room 2", "Hallway");
        Room room3 = new Room("Room 3", "Toilet");
        Room room4 = new Room("Room 4", "Grand Hall");
        Room room5 = new Room("Room 5", "Treasure room");
        Room room6 = new Room("Room 6", "Master bedroom");
        Room room7 = new Room("Room 7", "Lounge");
        Room room8 = new Room("Room 8", "Guest room nr. 1");
        Room room9 = new Room("Room 9", "Guest room nr. 2");

        // Alle de forskellige veje man kan tage i spillet.
//room 1
        room1.setEast(room2);
        room1.setSouth(room4);
//room 2
        room2.setWest(room1);
        room2.setEast(room3);
//room 3
        room3.setWest(room2);
        room3.setSouth(room6);
//room 4
        room4.setNorth(room1);
        room4.setSouth(room7);
//room 5
        room5.setSouth(room8);
//room 6
        room6.setNorth(room3);
        room6.setSouth(room9);
//room 7
        room7.setNorth(room4);
        room7.setEast(room8);
//room 8
        room8.setNorth(room5);
        room8.setWest(room7);
        room8.setEast(room9);
//room 9
        room9.setNorth(room6);
        room9.setWest(room8);

        startRoom = room1;
    }
    //Getter til startRoom
    public Room getStartRoom() {
        return startRoom;
    }
}
