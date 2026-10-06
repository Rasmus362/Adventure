public abstract class Weapon extends Item {

    private int damage;

    //constructor der kalder på forældre klassen Items (super)
    public Weapon(String shortName, String longName, int damage) {
        super(shortName, longName);
        this.damage = damage;
    }

    //getter til damage
    public int getDamage() {
        return damage;
    }
    //Fortæller om konkrete våben kan bruges
    public abstract boolean canUse ();

    //Gør så konkrete våben selv kan bestemme hvad der sker når de bruges
    public abstract int use ();
    }

