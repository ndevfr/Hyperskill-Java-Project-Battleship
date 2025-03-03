package battleship;

public class Player {
    private final String name;
    private final Ship[] ships;
    private final Grid grid;
    private boolean lose = false;

    Player(int i) {
        this.name = "Player " + i;
        this.grid = new Grid();
        this.ships = new Ship[]{
                new Aircraft(),
                new Battleship(),
                new Submarine(),
                new Cruiser(),
                new Destroyer()
        };
    }

    Ship getShip(int i) {
        return this.ships[i];
    }

    Grid getGrid() {
        return this.grid;
    }

    String getName() {
        return this.name;
    }

    boolean hasLosed() {
        return this.lose;
    }

    void printGrid(boolean fog) {
        this.grid.print(fog);
    }

    boolean shot(String reference) {
        int result = this.grid.shot(reference);
        switch(result) {
            case -1:
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                return false;
            case 0:
                System.out.println("You missed!");
                return true;
            case 1:
                System.out.println("You hit a ship!");
            case 2:
                boolean alive = this.refreshShips(reference);
                if(alive){
                    System.out.println("You hit a ship!");
                } else {
                    if(this.hasLosed()){
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    } else {
                        System.out.println("You sank a ship!");
                    }
                }
                return true;
            default:
                return false;
        }
    }

    boolean refreshShips(String reference){
        boolean allDead = true;
        boolean alive = true;
        for(Ship ship : this.ships){
            if(ship.includes(reference)){
                ship.incTouched();
                alive = ship.getAlive();
                allDead = allDead && !alive;
            } else {
                allDead = allDead && !ship.getAlive();
            }
        }
        if(allDead){
            this.lose = true;
        }
        return alive;
    }
}