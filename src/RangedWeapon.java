public class RangedWeapon extends Weapon {
    //int da ranged våben skal have ammo
    private int ammunition;

    //constructor hvor super refere til weapon klassen
    public RangedWeapon(String shortName, String longName, int damage, int ammunition) {
        super(shortName, longName, damage);
        this.ammunition = ammunition;
    }

    //overrider, da ranged weapon kan løbe tør for ammo
    @Override
    public boolean canUse() {
        return ammunition > 0;
    }

    //Gør så når våben bliver brugt tager den en ammo
    @Override
    public int use() {
        if (canUse()) {
            ammunition--;
        }
        return ammunition;
    }
}
