import java.util.*;

public class Main {
    public static void main(String[] args) {
        Codewars.startTimer.start();
        System.out.println(Codewars.makeReadable(2));
        Codewars.startTimer.interrupt();
        System.out.println(Codewars.miliseconds + " ms");
    }
}