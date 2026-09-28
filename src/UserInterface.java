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
            //tilføjer så man kan samle items op
            if (command.startsWith("take ")) { //hvis kommando starter med take
                takeItem(command);
            } else if (command.startsWith("drop ")) {
                dropItem(command);
            } else {
                switch (command) {
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
                }
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
                - exit
                """);
    }
    //metoden der bliver kaldt på når man skriver take ...
    private void takeItem(String command) {
        String itemName = command.substring(5); //tager fra 5 , så 1. t 2. a 3. k osv.
        Item item = adventureGame.takeItem(itemName);
        if (item != null) {
            IO.println("You have taken " + item.getLongName());
        } else {
            IO.println("There is nothing like " + itemName + " to take around here");
        }
    }
    //metoden der bliver kaldt på når man skriver drop
    private void dropItem(String command) {
        String itemName = command.substring(5); //tager fra 5 , så 1. t 2. a 3. k osv.
        Item item = adventureGame.dropItem(itemName);
        if (item != null) {
            IO.println("You have dropped " + item.getLongName());
        } else {
            IO.println("You don't have anything like " + itemName + " in your inventory");
        }
    }
}
