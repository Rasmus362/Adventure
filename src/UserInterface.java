public class UserInterface {

    private Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    public void Directions() {
        adventure.run();
    }

    public String getDirection() {
        return IO.readln("Hvilken retning vil du gå? ");
    }

    public void showRoom(Room room) {
        IO.println(room.getName());
        IO.println(room.getDescription());
    }

    public void showNoExit() {
        IO.println("Du kan ikke gå den vej.");
    }
}