public class UserInterface {

   public Adventure adventureGame;

    public UserInterface(Adventure adventureGame) {
        this.adventureGame = adventureGame;
    }

    public void run() {
        boolean running = true;
        while (running) {
            String command = IO.readln("> ");
            switch (command) {
                case "look":
                    showCurrentRoom();
                    break;

                    case "help":
                    showCurrentRoom();
                    break;

                    case "go north":
                    move("north");
                    break;

                    case "go east":
                    move("south");
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

    private void showCurrentRoom() {
        IO.println("You are in " + adventureGame.getCurrentRoomName());
        IO.println(adventureGame.getCurrentRoomDescription());
    }

    private void showHelp() {
        IO.println("""
                Commands:
                - look
                - go north
                - go east
                - go south
                - go west
                - exit
                """);
    }
}
