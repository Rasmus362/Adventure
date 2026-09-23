public class UserInterface {

    Adventure adventureGame;

    UserInterface(Adventure adventureGame) {

        this.adventureGame = adventureGame;
    }

    public void Directions() {
        boolean running = true;
        while (running) {
            String command = IO.readln("> ");
            switch (command) {
                case "look":
                    IO.println("You are in " + adventureGame.getCurrentRoom().getName());
                    IO.println(adventureGame.getCurrentRoom().getDescription());
                    break;
                case "help":
                    IO.println("Commands:" +
                            "\n- look"+
                            "\n- go north" +
                            "\n- go east" +
                            "\n- go south" +
                            "\n- go west" +
                            "\n- exit");
                    break;
                case "go north":
                    IO.println("Going north");
                    break;
                case "go east":
                    IO.println("Going east");
                    break;
                case "go south":
                    IO.println("Going south");
                    break;
                case "go west":
                    IO.println("Going west");
                    break;
                case "exit":
                    IO.println("Exitting");
                    running = false;
                    break;
            }
        }

    }


}
