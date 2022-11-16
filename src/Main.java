import java.util.*;

public class Main {
    public static void main(String[] args) {
        Codewars.startTimer.start();
        System.out.println(Arrays.toString(Codewars.findEmirp(2000000)));
        Codewars.startTimer.interrupt();
        System.out.println(Codewars.miliseconds + " ms");
    }
}