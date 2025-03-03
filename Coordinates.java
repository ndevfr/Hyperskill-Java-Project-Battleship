package battleship;

public class Coordinates {
    private int y;
    private int x;
    private boolean valide = true;

    Coordinates(String reference) {
        if(reference.length() <= 3){
            int y = (int) reference.charAt(0) - 65;
            if(y >= 0 && y < 10){
                this.y = y;
            } else {
                this.valide = false;
            }
            int x = Integer.parseInt(reference.substring(1)) - 1;
            if(x >= 0 && x < 10){
                this.x = x;
            } else {
                this.valide = false;
            }
        }
    }

    boolean isValide() {
        return this.valide;
    }

    int getX() {
        return this.x;
    }

    int getY(){
        return this.y;
    }
}