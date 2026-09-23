public class Adventure {

    Room currentRoom;
    Room room1 = new Room("Room 1", "Entry");

    Adventure (){ //Sørger for at spillet altid vil starte i room1.
        currentRoom = room1;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
