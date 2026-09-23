public class Adventure {

    Room currentRoom;
    Room room1 = new Room("Room 1", "Entry");
    Room room2 = new Room("Room 2", "Hallway");
    Room room3 = new Room("Room 3", "Toilet");
    Room room4 = new Room("Room 4", "Grand Hall");
    Room room5 = new Room("Room 5", "Treasure room");
    Room room6 = new Room("Room 6", "Master bedroom");
    Room room7 = new Room("Room 7", "Lounge");
    Room room8 = new Room("Room 8", "Guest room nr. 1");
    Room room9 = new Room("Room 9", "Guest room nr. 2");

    Adventure (){ //Sørger for at spillet altid vil starte i room1.
        currentRoom = room1;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
