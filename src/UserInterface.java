public class UserInterface {

    public Adventure adventureGame;

    public UserInterface(Adventure adventureGame) {
        this.adventureGame = adventureGame;
    }

    public void run() {
        boolean running = true;
        while (running) {

            if (!adventureGame.isPlayerAlive()) {
                PrintBox.print("GAME OVER!");
                running = false;
                continue;
            }

            String command = IO.readln("> ");

            //Laver en String array, som modtager command "take" som får index nr. [0]
            // og selve commanden, som beskrevet i switch'en nedenfor, det får index nr. [1]
            String[] commandTokens = command.trim().toLowerCase().split(" ");

            //tilføjer så man kan samle items op

            switch (commandTokens[0]) {

                //Giver mulighed for at dræbe ens karakter og fremskynde "GAME OVER"
                case "kill":
                    adventureGame.takeDamage(999);
                    continue;


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
                    adventureGame.showCurrentRoom();
                    break;

                case "help":
                    adventureGame.showHelp();
                    break;

                case "go":
                    if (commandTokens.length > 1) {
                        adventureGame.showMove(commandTokens[1]);
                    } else {
                        IO.println("Go where?");
                    }
                    break;

                case "attack":
                    if (commandTokens.length > 1) {
                        String enemyName = commandTokens[1];
                        adventureGame.showAttack(enemyName);
                    } else {
                        adventureGame.showAttack(null);
                    }

                    break;

                case "equip":
                    if (commandTokens.length > 1) {
                        adventureGame.showEquipItem(commandTokens[1]);
                    }
                    break;

                case "exit":
                    PrintBox.printBox("Exitting");
                    running = false;
                    break;

                //Når man skriver inventory kan man se spilleren inventory
                case "inventory":
                    PrintBox.printBoxHeader(
                            "Inventory",
                            adventureGame.getInventoryText()
                    );
                    break;


                //Når man skriver health kan man se HP
                case "health":

                    int health = adventureGame.getHealth();

                    PrintBox.printBoxHeader("Health", "HP: " + health);
                    break;
            }

        }
    }

}
