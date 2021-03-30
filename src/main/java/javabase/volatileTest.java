package javabase;

/**
 * @author: dingaimin
 * @date: 2021/2/22 15:15
 */
public class volatileTest {

    public static void main(String[] args) {
        A a = new A(1);
        System.out.println(a);

        a.setField(2);
        System.out.println(a);

        a = new A(3);
        System.out.println(a);

    }

    static class A {
        private int field;

        public A(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }
}
