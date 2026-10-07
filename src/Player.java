import java.util.ArrayList;

public class Player {
    //Håndtere hvor spilleren befinder sig, og håndtere bevægelser
    private Room currentRoom;

    //Arraylist til at håndtere spillerens inventory
    private ArrayList<Item> inventory = new ArrayList<>();

    //Healthpoints fra del 3, spilleren starter med 100 i liv
    private int health = 100;

    //equippedweapon er til at starte med automatisk null
    private Weapon equippedWeapon;

    //metode til at spilleren kan vinde spillet
    private boolean gameWon = false;

    //getter til Health
    public int getHealth() {
        return health;
    }

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
            if (item == equippedWeapon) {
                equippedWeapon = null;
            }
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
    //Metode for at spilleren kan spise et food item
    public EatResult eat(String itemName) {
        Item item = findItem(itemName); //leder efter item i spillerens inventory

        if (item == null) {
            item = currentRoom.findItem(itemName); //hvis item ikke er i inventory, tjekker vi efter det i rummet
        }
        if (item == null) { //Hvis item hverken er i inventory eller rummet returnere den ...
            return EatResult.NOT_FOUND;
        }
        if (!(item instanceof Food food)) { //Tjekker for om item er et Food item. hvis ikke (!) returnere den ...
            return EatResult.NOT_FOOD;      //Hvis det er et food item opretter den det som en variable food
        }
        health += food.getHealthPoints(); //justere HP i forhold til food item

        inventory.remove(item); //fjerener item fra inventory hvis der var der
        currentRoom.removeItem(item); //ligeledes fra currentroom hvis det var der

        return EatResult.EATEN;
    }
    //metode til at equippe våben, samt teste om det er et våben (Samme concept som eat)
    public EquipResult equip(String itemName) {
        Item item = findItem(itemName);

        if (item == null) {
            return EquipResult.NOT_FOUND;
        }
        if (!(item instanceof Weapon weapon)) {
            return EquipResult.NOT_WEAPON;
        }
        equippedWeapon = weapon;

        return EquipResult.EQUIPPED;
    }
    // metode til at angribe tomme luft
    public int attack() {
        if (equippedWeapon == null) {
            return -3;
        }
        if (!equippedWeapon.canUse()) {
            return -2;
        }
        return equippedWeapon.use();
    }
    //metode til at spilleren kan angribe en fjende
    public int attack(Enemy enemy) {
        if (equippedWeapon == null) {
            return -3;
        }
        if (!equippedWeapon.canUse()) {
            return -2;
        }
        int damage = equippedWeapon.getDamage();
        equippedWeapon.use();
        enemy.hit(damage);
        return damage;
    }

    //Metode til at fortælle hvilket våben der er equipped
    public String getEquippedWeaponLongName() {
        if (equippedWeapon == null) {
            return null;
        }
        return equippedWeapon.getLongName();
    }
    //metode til at spilleren kan tage skade
    public void hit(int damage) {
        health -= damage;
    }
    //metode til at tjekke om spilleren er død
    public boolean isAlive() {
        return health > 0;
    }
    //Metoder til at spilleren kan finde enemies i currentRoom
    public Enemy findEnemy(String enemyName) {
        return currentRoom.findEnemy(enemyName);
    }
    public Enemy getFirstEnemy() {
        if (currentRoom.getEnemies().isEmpty()) {
            return null;
        }
        return currentRoom.getEnemies().get(0);
    }
    public ArrayList<Enemy> getCurrentRoomEnemies() {
        return currentRoom.getEnemies();
    }
    //Getter til at vinde spillet
    public boolean hasWon() {
        return gameWon;
    }
}
