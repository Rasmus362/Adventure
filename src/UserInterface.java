import java.util.ArrayList;
import java.util.Arrays;

public class UserInterface {

    public Adventure adventureGame;

    public UserInterface(Adventure adventureGame) {
        this.adventureGame = adventureGame;
    }

    public void run() {
        boolean running = true;
        while (running) {
            String command = IO.readln("> ");

            //Laver en String array, som modtager command "take" som får index nr. [0]
            // og selve commanden, som beskrevet i switch'en nedenfor, det får index nr. [1]
            String[] commandTokens = command.trim().toLowerCase().split(" ");

            //tilføjer så man kan samle items op

            switch (commandTokens[0]) {

                case "take":
                    if (commandTokens.length > 1) {
                        adventureGame.showTakeItem(commandTokens[1]);
                    } else {
                        IO.println("Take what?");
                    }
                    break;

                case "drop":
                    if (commandTokens.length > 1) {
                        adventureGame.showDropItem(commandTokens[1]);
                    } else {
                        IO.println("Drop what?");
                    }
                    break;

                case "eat":
                    if (commandTokens.length > 1) {
                        adventureGame.showEatItem(commandTokens[1]);
                    } else {
                        IO.println("Eat what");
                    }
                    break;

                case "look":
                    showCurrentRoom();
                    break;

                case "help":
                    showHelp();
                    break;

                case "go":
                    if (commandTokens.length > 1) {
                        move(commandTokens[1]);
                    } else {
                        IO.println("Go where?");
                    }
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
}
