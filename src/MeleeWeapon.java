public class MeleeWeapon extends Weapon {
    //subclass til Weapon
    //constructor
    public MeleeWeapon(String shortName, String longName) {
        super(shortName, longName);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    //-1 som ubegrænset brug
    @Override
    public int use() {
        return -1;
    }
}
