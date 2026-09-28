import java.util.ArrayList;

public class Player {
    //Håndtere hvor spilleren befinder sig, og håndtere bevægelser
    private Room currentRoom;

    //Arraylist til at håndtere spillerens inventory
    private ArrayList<Item> inventory = new ArrayList<>();

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
    //getters til nuværende rum navn og beskrivelse
    public String getCurrentRoomName() {
        return currentRoom.getName();
    }
    public String getCurrentRoomDescription() {
        return currentRoom.getDescription();
    }
    //metode til at se spillerens inventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    //metode til at tage item, som samtidig fjerner det fra rummets arraylist
    public Item takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);

        if (item != null) {
            currentRoom.removeItem(item); //fjerner fra rummet
            inventory.add(item); //tilføjer til spillerens inventory
            return item;
        }
        return null;
    }
    //metode til at spilleren kan lede i sit eget inventory
    public Item findItem(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    //Metode til at spilleren kan smide items
    public Item dropItem(String itemName) {
        Item item = findItem(itemName);
        if (item != null) {
            inventory.remove(item); //Fjerne item fra inventory
            currentRoom.addItem(item); //Tilføjer det til rummet
            return item;
        }
        return null;
    }
    //metode til at sende current rooms items videre til adventure
    public ArrayList<Item> getCurrentRoomItems() {
        return currentRoom.getItems();
    }
}
