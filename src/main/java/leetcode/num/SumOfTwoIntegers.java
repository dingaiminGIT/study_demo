package leetcode.num;

/**
 * 两整数之和
 * https://leetcode-cn.com/problems/sum-of-two-integers/
 */
public class SumOfTwoIntegers {

    public static void main(String[] args) {
        System.out.println(getSum(12,99));
    }

    /**
     * 优秀解法 https://leetcode-cn.com/problems/sum-of-two-integers/solution/li-yong-wei-cao-zuo-shi-xian-liang-shu-qiu-he-by-p/
     *
     * 因为不能用 + 主要考察位运算
     * a + b 的问题拆分为 (a +b 的无进位结果) + (a + b 的进位结果)
     * 异或 ^ 无进位加法
     * 进位结果使用与运算和位移运算
     * 循环，直到进位为0
     *
     * @param a
     * @param b
     * @return
     */
    public static int getSum(int a, int b) {
        while (b != 0) {
            int temp = a^b;
            b = (a&b) << 1;
            a = temp;
        }
        return a;
    }

}
