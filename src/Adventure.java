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

    Adventure() { //Sørger for at spillet altid vil starte i room1.
        currentRoom = room1;

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

    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {

    }

    //Overall for void metoderne nedenfor: Sender if statement til UserInterface, og gør det muligt at bevæge sig gennem rooms
    public void goNorth() {
        if (currentRoom.getNorth() != null) {
            currentRoom = currentRoom.getNorth();
        } else {
            IO.println("You cannot go that way");
        }

    }

    public void goEast() {
        if (currentRoom.getEast() != null) {
            currentRoom = currentRoom.getEast();
        } else {
            IO.println("You cannot go that way");
        }
    }

    public void goSouth() {
        if (currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
        } else {
            IO.println("You cannot go that way");
        }
    }

    public void goWest() {
        if (currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
        } else {
            IO.println("You cannot go that way");
        }
    }

}
