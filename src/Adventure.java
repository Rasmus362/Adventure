import java.util.ArrayList;

public class Adventure {

    //felter
    private final GameMap gameMap;
    private final Player player;

    public Adventure() {
        gameMap = new GameMap();
        player = new Player(gameMap.getStartRoom());
    }

    //--------------------------------- Help --------------------------------------------
    public void showHelp() {
        IO.println("""
                Commands:
                - look
                - go north
                - go east
                - go south
                - go west
                - inventory
                - take + item
                - drop + item
                - eat + food item
                - equip + weapon
                - attack
                - health
                - exit
                """);
    }

    //--------------------------------- movement relaterede metoder --------------------------
    public void showMove(String direction) {
        IO.println("Going " + direction);
        boolean moved = player.move(direction);
        if (!moved) {
            IO.println("you cannot go that way");
        }
    }

    //---------------------------------- Room relaterede metoder -------------------------------
    //metode til at vise current room
    public void showCurrentRoom() {
        ArrayList<Item> items = player.getCurrentRoomItems();
        ArrayList<Enemy> enemies = player.getCurrentRoomEnemies();
        String itemsText = "";
        String enemyText = "";

        for (Item item : items) {
            itemsText += "- " + item.getLongName() + "\n";
        }
        //Så man kan se items i rummet, hvis der er nogle.
        if (!items.isEmpty()) {
            PrintBox.printBoxHeader("you are in " + player.getCurrentRoomName(),
                    "Loot: \n" + itemsText
            );
        }
        for (Enemy enemy : enemies) {
            enemyText += "- " + enemy.getLongName() + "\n";
        }
        if (!enemies.isEmpty()) {
            PrintBox.printBoxHeader("Hostile Creatures", "Enemies: \n" + enemyText
            );
        }
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

    //metoden der sbliver kaldt på når man skriver equip
    public void showEquipItem(String itemName) {
        EquipResult result = player.equip(itemName);

        switch (result) {
            case NOT_FOUND:
                IO.println("You don't have anything like " + itemName + " in your inventory");
                break;

            case NOT_WEAPON:
                IO.println(itemName + " is not a weapon");
                break;

            case EQUIPPED:
                IO.println("You have equipped " + itemName);
                break;
        }
    }

    //Henter fra equipped fra player
    public String getEquippedWeaponLongName() {
        return player.getEquippedWeaponLongName();
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
        EatResult result = player.eat(itemName);

        switch (result) {
            case NOT_FOUND:
                IO.println("There is nothing like " + itemName + " to eat around here");
                break;

            case NOT_FOOD:
                IO.println("You cannot eat the " + itemName);
                break;

            case EATEN:
                IO.println("You have eaten the " + itemName);
                break;
        }
    }

    //metoden der bliver kaldt på når man skriver attack
    public void showAttack() {
        ArrayList<Enemy> enemies = player.getCurrentRoomEnemies();
        if (!enemies.isEmpty()) {
            //Angriber den første fjende i indexet.
            Enemy foe = enemies.getFirst();
            //Hvis spilleren så har et våben:
            if (player.hasEquippedWeapon()) {
                if (player.canUseEquippedWeapon()) {
                    int damage = player.attack();
                    foe.takeDamage(damage);

                    //if enemy is dead.
                    if (!foe.isAlive()) {
                        //enemy er død.
                        player.getCurrentRoom().addItem(foe.getWeapon());
                        player.getCurrentRoom().removeEnemy(foe);
                    } else {
                        int enemyDamage = foe.attack();
                        player.takeDamage(enemyDamage);

                    }
                }
            }
        }
    }


//----------------------------------Player vitals og stats-----------------------------------

    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    public void takeDamage(int damage) {
        player.takeDamage(damage);
    }

    //--------------------------------- PrintBox ------------------------------------
// 1. Tilføj alle items
    public String getInventoryText() {
        return player.getInventoryText();
    }


}