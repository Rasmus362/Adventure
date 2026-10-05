public class Enemy {

    String shortName;
    String longName;
    String description;
    int health;
    Weapon weapon;
    Room currentRoom;


    Enemy(String shortName, String longName, String description, int health, Weapon weapon, Room currentRoom) {

        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;

    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }
}
