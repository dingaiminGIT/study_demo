package math;

public class MathUtil {

    public static void main(String[] args) {
        double weight = 0d;
        for (double i = 0; i < 100; i++) {
            weight = (1 / (1 + (Math.exp(-i))));
            System.out.println(weight);
        }
    }
}
