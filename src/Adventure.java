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

    //metoden der bliver kaldt på når man skriver take ...
    public void showTakeItem(String itemName) {

        Item item = player.takeItem(itemName);

        if (item != null) {
            IO.println(
                    "You have taken "
                            + item.getLongName()
            );
        } else {
            IO.println(
                    "There is nothing like "
                            + itemName
                            + " to take around here"
            );
        }
    }

    //metoden der bliver kaldt på når man skriver drop
    public void showDropItem(String itemName) {

        Item item = player.dropItem(itemName);

        if (item != null) {
            IO.println(
                    "You have dropped " + item.getLongName()
            );
        } else {
            IO.println(
                    "You don't have anything like "
                            + itemName
                            + " in your inventory"
            );
        }
    }

    //metoden der bliver kaldt på når man skriver eat
    public void showEatItem(String itemName) {

        EatResult result =
                player.eat(itemName);

        switch (result) {

            case NOT_FOUND:
                IO.println(
                        "There is nothing like "
                                + itemName
                                + " to eat around here"
                );
                break;

            case NOT_FOOD:
                IO.println(
                        "You cannot eat the "
                                + itemName
                );
                break;

            case EATEN:
                IO.println(
                        "You have eaten the "
                                + itemName
                );
                break;
        }
    }

    //metoden der bliver kaldt på når man skriver attack
    public void showAttack() {
        int result = player.attack();

        if (result == -3) {
            IO.println("You don't have a weapon equipped");
        } else if (result == -2) {
            IO.println("The " + player.getEquippedWeaponLongName() + " is out of ammunition");
        } else if (result == -1) {
            IO.println("You swing the " + player.getEquippedWeaponLongName() + " into the empty air.");
        } else {
            IO.println("You fire the " + player.getEquippedWeaponLongName() + " into the empty air. "
                    + result + " shots left");
        }
    }
}