package battleship;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        initPlayer(player1, scanner);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        initPlayer(player2, scanner);
        int i = 0;

        while(true){
            nextPlayer(scanner);
            Player player = i % 2 == 0 ? player1 : player2;
            Player opponent = i % 2 == 0 ? player2 : player1;
            dualGrids(player, opponent);
            System.out.println(player.getName() + ", it's your turn:");

            boolean goodShot = false;
            while(!goodShot){
                String reference = scanner.nextLine();
                goodShot = opponent.shot(reference);
            }

            if(opponent.hasLosed()){
                System.out.println(player.getName() + ", you win!");
                break;
            }

            i++;
        }
    }

    public static void dualGrids(Player player1, Player player2) {
        player2.printGrid(true);
        System.out.println("---------------------");
        player1.printGrid(false);
    }

    public static void nextPlayer(Scanner scanner) {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    public static void initPlayer(Player player, Scanner scanner) {
        System.out.println(player.getName() + ", place your ships on the game field");
        player.printGrid(false);
        for(int i=0; i<5; i++) {
            //System.out.println(ships[i].getLength());
            placeShip(scanner, player.getShip(i), player.getGrid(), false);
            player.printGrid(false);
        }
    }

    public static void placeShip(Scanner scanner, Ship ship, Grid grid, boolean autofill) {
        String[] automated = {"F3 F7", "A1 D1", "J10 J8", "B9 D9", "I2 J2"};
        System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):");
        boolean isFinish = false;
        int s = 0;
        while(!isFinish) {
            String line;
            if(!autofill){
                line = scanner.nextLine();
            } else {
                line = automated[s];
                s++;
            }
            String pattern = "([A-J]{1})([0-9]{1,2}) ([A-J]{1})([0-9]{1,2})";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(line);
            if (!m.find()) {
                System.out.println("Error! Wrong coordinates! Try again:");
                continue;
            }
            char l1 = m.group(1).toCharArray()[0];
            int x1 = Integer.parseInt(m.group(2));
            char l2 = m.group(3).toCharArray()[0];
            int x2 = Integer.parseInt(m.group(4));
            if (x1 < 1 || x2 < 1 || x1 > 10 || x2 > 10 || l1 < 'A' || l1 > 'J' || l2 < 'A' || l2 > 'J') {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }
            if (x2 != x1 && l2 != l1) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }
            int length = Math.abs(l2 - l1) + Math.abs(x2 - x1) + 1;
            if (length != ship.getLength()) {
                System.out.println("Error! Wrong length of the " + ship.getName() + "! Try again:");
                continue;
            }
            int incX = x1 > x2 ? -1 : +1;
            int incY = l1 > l2 ? -1 : +1;
            String[] parts = new String[length];
            for (int i = 0; i < length; i++) {
                if (x1 == x2) {
                    char letter = (char) (l1 + i * incY);
                    parts[i] = letter + String.valueOf(x1);
                } else {
                    int number = x1 + i * incX;
                    parts[i] = l1 + String.valueOf(number);
                }
            }
            if (!grid.placeShip(parts)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            } else {
                ship.setParts(parts);
            }
            isFinish = true;
        }
    }

}