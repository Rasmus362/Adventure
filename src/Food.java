//extends kalder på items, som er forældre klassen
public class Food extends Item {
    private int healthPoints;

    //constructor, super kalder på værdier i superklassen item
    public Food(String shortName, String longName, int healthPoints) {
        super(shortName, longName);
        this.healthPoints = healthPoints;
    }
    //getter til healtpoints
    public int getHealthPoints() {
        return healthPoints;
    }
}
