import java.util.ArrayList;

public class Room {
    private final String name;
    private final String description;

    //opretter Arraylist for items i rum
    private ArrayList<Item> items = new ArrayList<>();

    //Arraylist fro enemies i rum
    private ArrayList<Enemy> enemies = new ArrayList<>();

    //rummene
    private Room north;
    private Room east;
    private Room south;
    private Room west;

    //constructor til rum
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }
    //Getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    //Setters
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
    //----------------------------------------- Metoder ------------------------------------------------
    //metoder til at tilføje og fjerne items fra rum
    public void removeItem(Item item) {
        items.remove(item);
    }
    public void addItem(Item item) {
        items.add(item);
    }
    //metode til at se items i rum
    public ArrayList<Item> getItems() {
        return items;
    }
    //metode til at finde bestemt item ud fra det korte navn
    public Item findItem(String itemName) {
        for (Item item : items) {
            if (item.getShortName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    //metode til at tilføje enemies
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    //metode til at fjerne enemies
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
    //
    public Enemy findEnemy(String enemyName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equals(enemyName)) {
                return enemy;
            }
        }
        return null;
    }
}