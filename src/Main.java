import java.util.*;

public class Main {
    public static void main(String[] args) {
        Codewars.startTimer.start();
        int[][] spiral = Codewars.spiralize(7);
        for (int[] row: spiral){
            for (int col: row)
                System.out.print(col + "" + col);
            System.out.println();
        }
        Codewars.startTimer.interrupt();
        System.out.println(Codewars.miliseconds + " ms");
    }
}