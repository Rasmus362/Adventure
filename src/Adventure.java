import java.util.ArrayList;

public class Adventure {

    private GameMap gameMap;
    private Player player;


    public Adventure() {
        gameMap = new GameMap();
        player = new Player(gameMap.getStartRoom());
    }
    public boolean move(String direction) {
        return player.move(direction);
    }
    public String getCurrentRoomName() {
        return player.getCurrentRoomName();
    }
    public String getCurrentRoomDescription() {
        return player.getCurrentRoomDescription();
    }
    //metode fra player class til at tage items
    public Item takeItem(String itemName) {
        return player.takeItem(itemName);
    }
    //metode fra player class til at smide items
    public Item dropItem(String itemName) {
        return player.dropItem(itemName);
    }
    //metode fra player class til at se inventory
    public ArrayList<Item> getInventory() {
        return player.getInventory();
    }
    //metode til at give current rooms item videre
    public ArrayList<Item> getCurrentRoomItems() {
        return player.getCurrentRoomItems();
    }
    //metode til at se HP, som er oprettet i Player klassen
    public int getHealth() {
        return player.getHealth();
    }
}
