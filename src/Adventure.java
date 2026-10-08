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

    //Metode til at spille en besked når spillet starter
    public void showIntro() {

        IO.println("""
                
                =========================================
                        THE FORGOTTEN CASTLE
                =========================================
                
                You awaken before the gates of an old castle.
                The halls beyond are dark and silent...
                but you have the strange feeling that you are not alone.
                
                Explore the castle, gather useful items,
                find food to survive and weapons to defend yourself.
                
                When looking around, important item names are colored:
                """);

        IO.println(
                ConsoleColors.YELLOW
                        + "Yellow"
                        + ConsoleColors.RESET
                        + " = ordinary items"
        );

        IO.println(
                ConsoleColors.BLUE
                        + "Blue"
                        + ConsoleColors.RESET
                        + " = food"
        );

        IO.println(
                ConsoleColors.RED
                        + "Red"
                        + ConsoleColors.RESET
                        + " = weapons"
        );

        IO.println("""
                
                Useful commands:
                
                look: Look around the current room.
                
                go north / east / south / west: Move through the castle.
                
                take <item>: Pick up an item you can see.
                
                drop <item>: Drop an item from your inventory.
                
                inventory: See what you are carrying.
                """);

        IO.println(
                "Use "
                        + ConsoleColors.BLUE
                        + "eat <food>"
                        + ConsoleColors.RESET
                        + " to eat food and change your health."
        );

        IO.println(
                "Use "
                        + ConsoleColors.RED
                        + "equip <weapon>"
                        + ConsoleColors.RESET
                        + " to ready a weapon."
        );

        IO.println(
                "Use "
                        + ConsoleColors.RED
                        + "attack"
                        + ConsoleColors.RESET
                        + " or "
                        + ConsoleColors.RED
                        + "attack <enemy>"
                        + ConsoleColors.RESET
                        + " to fight."
        );

        IO.println("""
                
                health: Check your current health.
                
                help: Show the command list again.
                
                exit: Leave the game.
                
                The castle awaits...
                =========================================
                
                """);
    }

    //Hjælpe metode der bliver kaldt på i showCurrentRoom(), den farver våben rød, food items blå
    //og almindelige items gule
    private String colorItemName(Item item) {
        String color;

        if (item instanceof Weapon) {
            color = ConsoleColors.RED;
        } else if (item instanceof Food) {
            color = ConsoleColors.BLUE;
        } else {
            color = ConsoleColors.YELLOW;
        }
        return item.getLongName().replace(item.getShortName(), color + item.getShortName() + ConsoleColors.RESET);
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
                IO.println("- " + colorItemName(item));
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

        // Treasure kan ikke tages, så længe troll stadig er i rummet
        if (itemName.equals("treasure")
                && player.findEnemy("troll") != null) {

            IO.println("The troll guards the treasure. " + "You must defeat it first!");
            troldStraf("troll");

            return;
        }

        Item item = player.takeItem(itemName);

        if (item != null) {
            IO.println("You have taken " + item.getLongName());

        } else {
            IO.println("There is nothing like " + itemName + " to take around here");
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
                IO.println("Your health is now: " + player.getHealth());
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
        IO.println("You hit " + enemy.getLongName() + " with " +
                player.getEquippedWeaponLongName() + " for " + damage + " damage.");

        if (enemy.isDead()) {
            IO.println(enemy.getLongName() + " dies, dropping " + enemy.getWeaponLongName());
            return;
        }
        int enemyDamage = enemy.attack(player);
        IO.println(enemy.getLongName() + " attacks you for " + enemyDamage + " damage.");
        IO.println("Your health drops to: " + player.getHealth());
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

    //metode til at fortælle UI man har vundet
    public boolean hasWon() {
        return player.hasWon();
    }

    //metode til at straffe spilleren for at være doven
    private void troldStraf(String enemyName) {
        Enemy enemy = player.findEnemy(enemyName);
        int enemyDamage = enemy.attack(player);
        IO.println(enemy.getLongName() + " attacks you for " + enemyDamage + " damage.");
        IO.println("Your health drops to: " + player.getHealth());
    }
}