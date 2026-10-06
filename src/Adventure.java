import java.util.ArrayList;

public class Adventure {

    //felter
    private GameMap gameMap;
    private Player player;

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

        } else {
                showCurrentRoom();
            }
    }

    //---------------------------------- Room relaterede metoder -------------------------------
    //metode til at vise current room
    public void showCurrentRoom() {
        IO.println("You are in " + player.getCurrentRoomName());
        IO.println(player.getCurrentRoomDescription());
        //Så man kan se items i rummet, hvis der er nogle
        ArrayList<Item> items = player.getCurrentRoomItems();
        if (!items.isEmpty()) {
            IO.println("Here you see:");

            for (Item item : items) {
                IO.println("- " + item.getLongName());
            }
        }
        //tilføjelse så look også viser enemies
        ArrayList<Enemy> enemies = player.getCurrentRoomEnemies();
            if (!enemies.isEmpty()) {
                IO.println("Beware! Here lurks:");

                for (Enemy enemy : enemies) {
                    IO.println("- " + enemy.getLongName());
                }
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

    //metoden der bliver kaldt på når man skriver equip
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
    //metoden til at tjekke om spilleren er død
    public boolean isPlayerAlive() {
        return player.isAlive();
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

    //metoder der bliver kaldt på når man skriver attack
    public void showAttack() {
        Enemy enemy = player.getFirstEnemy();
        if (enemy == null) {
            showAttackEmptyAir();
        } else {
            showAttack(enemy.getShortName());
        }
    }
    public void showAttack(String enemyName) {
        Enemy enemy = player.findEnemy(enemyName);
        if (enemy == null) {
            IO.println("There is nothing like " + enemyName + " to attack around here");
            return;
        }
        int damage = player.attack(enemy);
        if (damage == -3) {
            IO.println("You don't have a weapon equipped");
            return;
        }
        if (damage == -2) {
            IO.println(player.getEquippedWeaponLongName() + " is out of ammunition");
            return;
        }
        IO.println("You hit" + enemy.getLongName() + " with " +
                player.getEquippedWeaponLongName() + " for " + damage + " damage.");

        if (enemy.isDead()) {
            IO.println(enemy.getLongName() + " dies, dropping " + enemy.getWeaponLongName());
            return;
        }
        int enemyDamage = enemy.attack(player);
        IO.println(enemy.getLongName() + " attacks you for " + enemyDamage + " damage.");
    }

    private void showAttackEmptyAir() {
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