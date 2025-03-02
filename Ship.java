package battleship;

class Ship {
    private String name;
    private String[] parts;
    private int length;
    private int touched;
    private boolean alive;

    public Ship(String name, int length){
        this.name = name;
        this.parts = new String[length];
        this.length = length;
        this.touched = 0;
        this.alive = true;
    }

    public String getName(){
        return this.name;
    }

    public void setParts(String[] parts){
        this.parts = parts;
    }

    public int getLength(){
        return this.length;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public void incTouched() {
        this.touched++;
        if(this.touched == this.length){
            this.alive = false;
        }
    }

    public boolean includes(String reference){
        for (String part : this.parts) {
            if (part.equals(reference)) {
                return true;
            }
        }
        return false;
    }
}

class Aircraft extends Ship {
    public Aircraft(){
        super("Aircraft Carrier", 5);
    }
}

class Battleship extends Ship {
    public Battleship(){
        super("Battleship", 4);
    }
}

class Submarine extends Ship {
    public Submarine(){
        super("Submarine", 3);
    }
}

class Cruiser extends Ship {
    public Cruiser(){
        super("Cruiser", 3);
    }
}

class Destroyer extends Ship {
    public Destroyer(){
        super("Destroyer", 2);
    }
}