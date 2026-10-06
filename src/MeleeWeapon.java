public class MeleeWeapon extends Weapon {
    //subclass til Weapon
    //constructor
    public MeleeWeapon(String shortName, String longName, int damage) {
        super(shortName, longName, damage);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    //-1 som ubegrænset brug
    @Override
    public int use() {
        return getDamage();
    }
}
