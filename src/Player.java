import java.util.ArrayList;

public class Player {
    //Håndtere hvor spilleren befinder sig, og håndtere bevægelser
    private Room currentRoom;

    ArrayList<Item> items = new ArrayList<>();

    public Player(Room startRoom) {
        currentRoom = startRoom;
    }

    //Bruger en boolean metode til erstate goNorth etc..
    public boolean move(String direction) {
        Room nextRoom = null;

        switch (direction) {
            case "north" -> nextRoom = currentRoom.getNorth();
            case "east" -> nextRoom = currentRoom.getEast();
            case "south" -> nextRoom = currentRoom.getSouth();
            case "west" -> nextRoom = currentRoom.getWest();
        }

        if (nextRoom != null) {
            currentRoom = nextRoom;
            return true;
        }
        return false;
    }
    public String getCurrentRoomName() {
        return currentRoom.getName();
    }
    public String getCurrentRoomDescription() {
        return currentRoom.getDescription();
    }
}
