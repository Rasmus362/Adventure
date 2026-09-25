import java.util.ArrayList;

public class Room {
    private final String name;
    private final String description;

    private ArrayList<Item> items = new ArrayList<>();

    //Rummene
    private Room north;
    private Room east;
    private Room south;
    private Room west;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //North
    public void setNorth(Room room) {
        north = room;
    }

    public Room getNorth() {
        return north;
    }

    //East
    public void setEast(Room room) {
        east= room;
    }

    public Room getEast() {
        return east;
    }

    //South
    public void setSouth(Room room) {
        south = room;
    }

    public Room getSouth() {
        return south;
    }
    //West
    public void setWest(Room room) {
        west = room;
    }

    public Room getWest() {
        return west;
    }

    //Items

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item findItem(String navnItem) {
        for (Item item : items) {
            if (item.getItemNavn().equals(navnItem)) {
                return item;
            }
        }

        return null;
    }

}