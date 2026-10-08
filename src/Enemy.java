public class Enemy {

    private String shortName;
    private String longName;
    private String description;
    private int health;
    private Weapon weapon;
    private Room currentRoom;

    //Constructor til enemy
    public Enemy(String shortName, String longName, String description, int health, Weapon weapon, Room currentRoom) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;
    }
//---------------------------------- Getters til enemy ---------------------------------------------------

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public String getWeaponLongName() {
        return weapon.getLongName();
    }

    //boolean til at siger om enemy er død
    public boolean isDead() {
        return health <= 0;
    }
    //metode til ramme enemy
    public void hit(int damage) {
        health -= damage;

        if (health <= 0) {
            currentRoom.addItem(weapon);
            currentRoom.removeEnemy(this);
        }
    }
    //metode til at enemy kan angribe spilleren
    public int attack(Player player) {
        if (!weapon.canUse()) {
            return 0;
        }
        int damage = weapon.getDamage();
        weapon.use();
        player.hit(damage);
        return damage;
    }
}

