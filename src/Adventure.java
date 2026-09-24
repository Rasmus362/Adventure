public class Adventure {

    private GameMap gameMap;
    private Player player;


    public Adventure() {
        gameMap = new GameMap();
        player = new Player(gameMap.getStartRoom());
    }
    public boolean move(String direction) {
        return player.move(direction);
    }
    public String getCurrentRoomName() {
        return player.getCurrentRoomName();
    }
    public String getCurrentRoomDescription() {
        return player.getCurrentRoomDescription();
    }
}
