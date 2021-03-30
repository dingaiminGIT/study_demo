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
     * 利用位操作实现加法
     * 首先看十进制是如何做的： 5+7=12，三步走
     *
     * 第一步：相加各位的值，不算进位，得到2。
     * 第二步：计算进位值，得到10. 如果这一步的进位值为0，那么第一步得到的值就是最终结果。
     * 第三步：重复上述两步，只是相加的值变成上述两步的得到的结果2和10，得到12。
     * 同样我们可以用三步走的方式计算二进制值相加： 5---101，7---111
     *
     * 第一步：相加各位的值，不算进位，得到010，二进制每位相加就相当于各位做异或操作，101^111。
     * 第二步：计算进位值，得到1010，相当于各位进行与操作得到101，再向左移一位得到1010，(101&111)<<1。
     * 第三步重复上述两步，各位相加 010^1010=1000，进位值为100=(010 & 1010)<<1。
     * 继续重复上述两步：1000^100 = 1100，进位值为0，跳出循环，1100为最终结果。
     * 结束条件：进位为0，即a为最终的求和结果。
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
