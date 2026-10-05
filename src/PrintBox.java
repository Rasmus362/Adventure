// Bemærk!
// at dette ikke er et nødvendigt krav til vores opgave, blot en måde at give spillet lidt stil.

// istedet for IO.println();
// kan man indsætte: PrintBox.printBox

public class PrintBox {


    public static void printBox(String text) {

        int boksLength = 62;
        String[] linjer = text.split("\n");
        IO.println("╔" + "═".repeat(boksLength) + "╗");
        for (String linje : linjer) {

            int mellemrum = boksLength - linje.length() - 1;

            IO.println("║ " + linje + " ".repeat(mellemrum) + "║");

        }
        IO.println("╚" + "═".repeat(boksLength) + "╝");

    }

    public static void printBoxHeader(String header, String text) {
        int boksLength = 62;
        int ledigPlads = boksLength - header.length()-2;
        int venstreStreger = ledigPlads /2;
        int hoejreStreger = ledigPlads - venstreStreger;


        String[] linjer = text.split("\n");

        //øverste del af boksen.
        IO.println("╔" +
                "═".repeat(venstreStreger) +
                " " + header + " "
                + "═".repeat(hoejreStreger) +
                "╗");

        for (String linje : linjer) {

            int mellemrum = boksLength - linje.length() - 1;

            //lodrette kanter.
            IO.println("║ " + linje + " ".repeat(mellemrum) + "║");
        }
        // nederste del af boksen.
        IO.println("╚" + "═".repeat(boksLength) + "╝");
    }

    public static void print(String text){
        printBox(text);
    }

}
