import java.util.ArrayList;
import java.util.Arrays;

public class UserInterface {

    public Adventure adventureGame;

    public UserInterface(Adventure adventureGame) {
        this.adventureGame = adventureGame;
    }

    public void run() {
        adventureGame.showIntro();
        boolean running = true;
        while (running) {
            String command = IO.readln("> ");

            //Laver en String array, som modtager command "take" som får index nr. [0]
            // og selve commanden, som beskrevet i switch'en nedenfor, det får index nr. [1]
            String[] commandTokens = command.trim().toLowerCase().split(" ");

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
                        EatResult result = adventureGame.showEatItem(commandTokens[1]);
                        switch (result) {
                            case NOT_FOUND:
                                IO.println("There is nothing like " + commandTokens[1] + " to eat around here");
                                break;

                            case NOT_FOOD:
                                IO.println("You cannot eat the " + commandTokens[1]);
                                break;

                            case EATEN:
                                IO.println("You have eaten the " + commandTokens[1]);
                                IO.println("Your health is now: " + adventureGame.getHealth());
                                break;
                        }
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
                        adventureGame.showAttack(commandTokens[1]);
                    } else {
                        adventureGame.showAttack();
                    }
                    break;

                case "equip":
                    if (commandTokens.length > 1) {
                        adventureGame.showEquipItem(commandTokens[1]);
                    } else {
                        IO.println("Equip what");
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
                    if (adventureGame.getEquippedWeaponLongName() != null) {
                        IO.println("Equipped: " + adventureGame.getEquippedWeaponLongName());
                    }
                    break;

                //Når man skriver health kan man se HP
                case "health":
                    IO.println("Health: " + adventureGame.getHealth());
                    break;
            }
            //Tjekker om spilleren er død
            if (!adventureGame.isPlayerAlive()) {
                IO.println("You died. Game over!");
                running = false;
            }
            if (adventureGame.hasWon()) {
                IO.println("""
            
            =========================================
                         VICTORY!
            =========================================
            
            After wandering through the dark halls
            of the forgotten castle, you finally
            claim its long-lost treasure.
            
            A hidden door opens, shinning light
            through it.
            
            Against all odds, you survived.
            
            You have won the game!
            =========================================
            
            """);

                running = false;
            }

        }
    }

}
