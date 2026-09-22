public class Room {
    private String navn;
    private String beskrivelse;

    //Rummene
    private Room nord;
    private Room øst;
    private Room syd;
    private Room vest;

    public Room(String navn, String beskrivelse) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    //Nord
    public void setNord(Room room) {
        nord = room;
    }

    public Room getNord() {
        return nord;
    }

    //Øst
    public void setØst(Room room) {
        øst = room;
    }

    public Room getØst() {
        return øst;
    }

    //Syd
    public void setSyd(Room room) {
        syd = room;
    }

    public Room getSyd() {
        return syd;
    }
    //Vest
    public void setVest(Room room) {
        vest = room;
    }

    public Room getVest() {
        return vest;
    }
}