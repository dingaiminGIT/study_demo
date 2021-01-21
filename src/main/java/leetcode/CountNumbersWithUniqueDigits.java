package leetcode;

/**
 * 计算各个位数不同的数字个数
 * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 *
 * @author: dingaimin
 * @date: 2021/1/21 20:26
 */
public class CountNumbersWithUniqueDigits {

    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 9;
        int min = Math.min(10, n);
        int res = 10;
        for (int i = 1; i < min; i++) {
            dp[i+1] = dp[i] * (10 - i);
            res += dp[i+1];
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/solution/dong-tai-gui-hua-la-by-jxjxhc-2/
     *
     * @param n
     * @return
     */
    public static int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) {
            return 1;
        }
        int res = 10, k = 9, temp = 9;
        for (int i = 2; i <= Math.min(n, 10); ++i){
            temp *= k;
            k--;
            res += temp;
        }
        return res;
    }
}
