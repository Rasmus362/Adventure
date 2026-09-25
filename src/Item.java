public class Item {

    final String itemNavn;
    final String heleItemNavn;

    public Item(String itemNavn, String heleItemNavn){

        this.itemNavn = itemNavn;
        this.heleItemNavn = heleItemNavn;

    }

    public String toString(){
        return String.format("%s, %s", itemNavn, heleItemNavn);
    }

}
