import java.util.ArrayList;

public class Player {
    //Håndtere hvor spilleren befinder sig, og håndtere bevægelser
    private Room currentRoom;

    ArrayList<Item> inventory = new ArrayList<>();

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

    public void takeItem(Item item) {
        inventory.add(item);
    }

    public void dropItem(Item item) {
        inventory.remove(item);
    }

    public ArrayList<Item> getItems() {
        return inventory;
    }

    public Item findItem(String navnItem) {
        for(Item item : inventory) {
            if(item.getItemNavn().equals(navnItem)) {
                return item;
            }
        }

        return null;
    }

    public Item takeItem(String navnItem) {
        Item item = currentRoom.findItem(navnItem);

        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
            return item;
        }

        return null;
    }

    public Item dropItem(String navnItem) {
        Item item = findItem(navnItem);

        if(item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            return item;
        }

        return null;
    }



}
