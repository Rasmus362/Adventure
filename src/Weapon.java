public abstract class Weapon extends Item {

    //constructor der kalder på forældre klassen Items (super)
    public Weapon(String shortName, String longName) {
        super(shortName, longName);
    }
    //Fortæller om konkrete våben kan bruges
    public abstract boolean canUse();
    //Gør så konkrete våben selv kan bestemme hvad der sker når de bruges
    public abstract int use();
}
