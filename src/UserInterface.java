import java.util.ArrayList;

public class UserInterface {

    public Adventure adventureGame;

    public UserInterface(Adventure adventureGame) {
        this.adventureGame = adventureGame;
    }

    public void run() {
        boolean running = true;
        while (running) {
            String command = IO.readln("> ");

            String[] commandTokens = command.trim().toLowerCase().split(" ");

            //tilføjer så man kan samle items op

            switch (commandTokens[0]) {
                case "take":
                    if (commandTokens.length > 1) {
                        showTakeItem(commandTokens[1]);
                    } else {
                        IO.println("Take what?");
                    }
                    break;

                case "drop":
                    if (commandTokens.length > 1) {
                        showDropItem(commandTokens[1]);
                    } else {
                        IO.println("Drop what?");
                    }

                case "eat":
                    if (commandTokens.length > 1) {
                        showEatItem(commandTokens[1]);
                    } else {
                        IO.println("Eat what");
                    }

                case "look":
                    showCurrentRoom();
                    break;

                case "help":
                    showHelp();
                    break;

                case "go north":
                    move("north");
                    break;

                case "go east":
                    move("east");
                    break;

                case "go south":
                    move("south");
                    break;

                case "go west":
                    move("west");
                    break;

                case "exit":
                    IO.println("Exitting");
                    running = false;
                    break;

                //Når man skriver inventory kan man se spilleren inventory
                case "inventory":
                    for (Item item : adventureGame.getInventory()) {
                        IO.println(item.getLongName());
                    }
                    break;

                //Når man skriver health kan man se HP
                case "health":
                    IO.println("Health: " + adventureGame.getHealth());
                    break;
            }

        }
    }

    private void move(String direction) {
        IO.println("Going " + direction);
        boolean moved = adventureGame.move(direction);
        if (!moved) {
            IO.println("you cannot go that way");
        }
    }

    //metode til at vise current room
    private void showCurrentRoom() {
        IO.println("You are in " + adventureGame.getCurrentRoomName());
        IO.println(adventureGame.getCurrentRoomDescription());
        //Så man kan se items i rummet, hvis der er nogle
        ArrayList<Item> items = adventureGame.getCurrentRoomItems();
        if (!items.isEmpty()) {
            IO.println("Here you see:");

            for (Item item : items) {
                IO.println("- " + item.getLongName());
            }
        }
    }

    private void showHelp() {
        IO.println("""
                Commands:
                - look
                - go north
                - go east
                - go south
                - go west
                - inventory (to see inventory)
                - take + item to take an item
                - drop + item to drop an item
                - eat + food item to eat item
                - exit
                """);
    }

    //metoden der bliver kaldt på når man skriver take ...
    private void showTakeItem(String itemName) {

        Item item = adventureGame.takeItem(itemName);

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

    //metoden der bliver kaldt på når man skriver eat
    private void showEatItem(String itemName) {

        EatResult result =
                adventureGame.eat(itemName);

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

    //metoden der bliver kaldt på når man skriver drop
    private void showDropItem(String itemName) {

        Item item = adventureGame.dropItem(itemName);

        if (item != null) {
            IO.println(
                    "You have dropped "
                            + item.getLongName()
            );
        } else {
            IO.println(
                    "You don't have anything like "
                            + itemName
                            + " in your inventory"
            );
        }
    }

}
