package battleship;

class Player {
    private String name;
    private Ship[] ships;
    private Grid grid;
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

    public Ship getShip(int i) {
        return this.ships[i];
    }

    public Grid getGrid() {
        return this.grid;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasLosed() {
        return this.lose;
    }

    public void printGrid(boolean fog) {
        this.grid.print(fog);
    }

    public boolean shot(String reference) {
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

    public boolean refreshShips(String reference){
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
