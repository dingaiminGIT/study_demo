package leetcode.num;

/**
 * 斐波那契数
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n)
 *
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 *
 * 示例 2：
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 *
 * 示例 3：
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 *  
 *
 * 提示：
 * 0 <= n <= 30
 *
 * @author: dingaimin
 * @date: 2021/1/16 16:08
 */
public class FibonacciNumber {


    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public static int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int fn1 = 0;
        int fn2 = 0;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            fn1 = fn2;
            fn2 = res;
            res = fn1 + fn2;
        }
        return res;
    }


    /**
     * 递归
     *递归的写法非常简洁，也最容易想到，但时间复杂度很高
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
