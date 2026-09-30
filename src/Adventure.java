import java.util.ArrayList;

public class Adventure {

    //felter
    private GameMap gameMap;
    private Player player;

    public Adventure() {
        gameMap = new GameMap();
        player = new Player(gameMap.getStartRoom());
    }
    //--------------------------------- movement relaterede metoder --------------------------
    public boolean move(String direction) {
        return player.move(direction);
    }
    //--------------------------------- Room relaterede metoder -------------------------------
    public String getCurrentRoomName() {
        return player.getCurrentRoomName();
    }
    public String getCurrentRoomDescription() {
        return player.getCurrentRoomDescription();
    }
    //metode til at give current rooms item videre
    public ArrayList<Item> getCurrentRoomItems() {
        return player.getCurrentRoomItems();
    }
    //------------------------------------- inventory & health ---------------------------------
    //metode til at se HP, som er oprettet i Player klassen
    public int getHealth() {
        return player.getHealth();
    }
    //metode fra player class til at se inventory
    public ArrayList<Item> getInventory() {
        return player.getInventory();
    }
    //----------------------------------------Actions-------------------------------------------
    //metode fra player class til at tage items
    public Item takeItem(String itemName) {
        return player.takeItem(itemName);
    }
    //metode fra player class til at smide items
    public Item dropItem(String itemName) {
        return player.dropItem(itemName);
    }
    //metode til at sende Eatresult fra player videre til UI
    public EatResult eat(String itemName) {
        return player.eat(itemName);
    }
}
