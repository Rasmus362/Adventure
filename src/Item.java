public class Item {

    //laver et kort og langt navn, får at gøre det lette at kalde på kun det korte i consol
    private String shortName;
    private String longName;

    public item(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }
    //Laver getters til både det korte og lange navn
    public String getShortName() {
        return shortName;
    }
    public String getLongName() {
        return longName;
    }
}
