public class Player {

    private Room currentRoom;

    public Player(Room startRoom) {
        currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveNorth() {
        if (currentRoom.getNorth() != null) {
            currentRoom = currentRoom.getNorth();
        }
    }

    public void moveSouth() {
        if (currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
        }
    }

    public void moveEast() {
        if (currentRoom.getEast() != null) {
            currentRoom = currentRoom.getEast();
        }
    }

    public void moveWest() {
        if (currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
        }
    }
}