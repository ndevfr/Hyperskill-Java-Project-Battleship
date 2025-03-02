package battleship;

class Grid {
    private char[][] grid = {
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'}
    };

    public void setValue(String reference, char value) {
        Coordinates coordinates = new Coordinates(reference);
        int y = coordinates.getY();
        int x = coordinates.getX();
        this.setValue(y, x, value);
    }

    public void setValue(int y, int x, char value) {
        this.grid[y][x] = value;
    }

    public char getValue(int y, int x) {
        return this.grid[y][x];
    }

    public boolean isAvailable(String reference) {
        Coordinates coordinates = new Coordinates(reference);
        int y = coordinates.getY();
        int x = coordinates.getX();
        if(this.grid[y][x] != '~'){
            return false;
        }
        if(y+1 < 10 && this.grid[y+1][x] != '~'){
            return false;
        }
        if(y-1 >= 0 && this.grid[y-1][x] != '~'){
            return false;
        }
        if(x+1 < 10 && this.grid[y][x+1] != '~'){
            return false;
        }
        if(x-1 >= 0 && this.grid[y][x-1] != '~'){
            return false;
        }
        return true;
    }

    public boolean placeShip(String[] references) {
        for (String reference : references){
            if(!isAvailable(reference)){
                return false;
            }
        }
        for (String reference : references){
            this.setValue(reference, 'O');
        }
        return true;
    }

    public int shot(String reference) {
        Coordinates coordinates = new Coordinates(reference);
        if(!coordinates.isValide()){
            return -1;
        } else {
            int y = coordinates.getY();
            int x = coordinates.getX();
            char value = this.getValue(y, x);
            if (value == 'O') {
                this.setValue(y, x, 'X');
                return 2;
            } else if  (value == 'X') {
                return 1;
            } else if (value == '~' || value == 'M') {
                this.setValue(y, x, 'M');
                return 0;
            }
        }
        return -1;
    }

    public void print(boolean fog) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for(int i=0; i<10; i++){
            char letter = (char)(65+i);
            StringBuilder sb = new StringBuilder("" + letter);
            for(int k=0; k<10; k++){
                sb.append(" ").append(this.grid[i][k]);
            }
            String out = sb.toString();
            if(fog){
                out = out.replace('O', '~');
            }
            System.out.println(out);
        }
    }
}
