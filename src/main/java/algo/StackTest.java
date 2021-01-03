package algo;

public class StackTest {

    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        stackTest.mai();
    }

    private int  mai() {
        int ret = add(3,5);
        System.out.println(ret);
        return 0;
    }

    private int add(int x, int y) {
        int sum = 0;
        sum = x + y;
        return sum;
    }
}
