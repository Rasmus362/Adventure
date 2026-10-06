public class GameMap {
    //GameMaps opgave er at bygge mappen
    private final Room startRoom;

    public GameMap() {
        //Oprettelse af rummene
        Room room1 = new Room("Room 1", "Entry");
        Room room2 = new Room("Room 2", "Hallway");
        Room room3 = new Room("Room 3", "Toilet");
        Room room4 = new Room("Room 4", "Grand Hall");
        Room room5 = new Room("Room 5", "Treasure room");
        Room room6 = new Room("Room 6", "Master bedroom");
        Room room7 = new Room("Room 7", "Lounge");
        Room room8 = new Room("Room 8", "Guest room nr. 1");
        Room room9 = new Room("Room 9", "Guest room nr. 2");

        //oprettelse af items
        Item coin = new Item("coin", "a shiny gold coin");
        Item ring = new Item("ring", "old and dirty silver ring");
        Item boots = new Item("boots", "brand new pair of boots");
        Item pieceOfWood = new Item("wood", "looks like a tabel leg");
        Item sword = new Item("sword", "partially rusted sword");
        Item shield = new Item("shield", "scratched leather shield");
        Item goldTreasure = new Item("Treasure", "the keeps long hidden treasure");

        //tilføjer items, til de forskellige rum
        room1.addItem(coin);
        room1.addItem(ring);
        room2.addItem(boots);
        room2.addItem(pieceOfWood);
        room3.addItem(shield);
        room5.addItem(goldTreasure);
        room6.addItem(sword);


        //tilføjer mad til spillet (fra del 3) (minus betyder dårlig mad, der tager HP)
        Food bread = new Food("bread", "a loaf of stale old bread", -10);
        Food cake = new Food("cake", "tasty cake", 20);
        Food healthPotion = new Food("potion", "a potion of red liquid", 35);
        Food deadRat = new Food("rat", "rat carcass", -20);
        Food oldRations = new Food("rations", "small box of rations", 20);

        //tilføjer mad til rum
        room1.addItem(bread);
        room1.addItem(healthPotion);
        room2.addItem(cake);
        room3.addItem(deadRat);
        room7.addItem(oldRations);

        //Laver et melee våben for at teste
        MeleeWeapon mace = new MeleeWeapon("mace", "a rusty mace", 5);
        //tilføjer det til et rum
        room1.addItem(mace);

        //Laver et ranged våben for at teste
        RangedWeapon bow = new RangedWeapon("bow", "a simple bow", 3, 0);
        //tilføjer det til et rum
        room2.addItem(bow);

        // Alle de forskellige veje man kan tage i spillet.
//room 1
        room1.setEast(room2);
        room1.setSouth(room4);
//room 2
        room2.setWest(room1);
        room2.setEast(room3);
//room 3
        room3.setWest(room2);
        room3.setSouth(room6);
//room 4
        room4.setNorth(room1);
        room4.setSouth(room7);
//room 5
        room5.setSouth(room8);
//room 6
        room6.setNorth(room3);
        room6.setSouth(room9);
//room 7
        room7.setNorth(room4);
        room7.setEast(room8);
//room 8
        room8.setNorth(room5);
        room8.setWest(room7);
        room8.setEast(room9);
//room 9
        room9.setNorth(room6);
        room9.setWest(room8);

//Enemy kreation:
        MeleeWeapon club = new MeleeWeapon("club",
                "An old table leg, now serving as a club.", 5);
        Enemy skeletalWarrior = new Enemy("warrior",
                "Skeletal Warrior",
                "The sorry pile of bones barely keeps it self together," +
                        " as shambles towards you.", 15, club, room2);
        room2.addEnemy(skeletalWarrior);

//--------------------------------------------------------------------------------------------
//fortæller hvilket rum spillet starter i.
        startRoom = room1;
    }
    //Getter til startRoom
    public Room getStartRoom() {
        return startRoom;
    }
}
