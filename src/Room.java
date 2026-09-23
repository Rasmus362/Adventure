public class Room {
    private String name;
    private String descrption;

    //Rummene
    private Room north;
    private Room east;
    private Room south;
    private Room west;

    public Room(String name, String descrption) {
        this.name = name;
        this.descrption = descrption;
    }

    public String getDescrption() {
        return descrption;
    }

    //North
    public void setNorth(Room room) {
        north = room;
    }

    public Room getNorth() {
        return north;
    }

    //East
    public void setEast(Room room) {
        east= room;
    }

    public Room getEast() {
        return east;
    }

    //South
    public void setSouth(Room room) {
        south = room;
    }

    public Room getSouth() {
        return south;
    }
    //West
    public void setWest(Room room) {
        west = room;
    }

    public Room getWest() {
        return west;
    }
}