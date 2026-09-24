public class Adventure {

    private Map map;
    private Player player;
    private UserInterface userInterface;

    public Adventure() {
        map = new Map();
        player = new Player(map.getStartRoom());
        userInterface = new UserInterface(this);
    }

    public void run() {

        userInterface.showRoom(player.getCurrentRoom());

        while (true) {

            String direction = userInterface.getDirection();

            if (direction.equals("north")) {
                player.moveNorth();
                userInterface.showRoom(player.getCurrentRoom());

            } else if (direction.equals("south")) {
                player.moveSouth();
                userInterface.showRoom(player.getCurrentRoom());

            } else if (direction.equals("east")) {
                player.moveEast();
                userInterface.showRoom(player.getCurrentRoom());

            } else if (direction.equals("west")) {
                player.moveWest();
                userInterface.showRoom(player.getCurrentRoom());
            }
        }
    }
}