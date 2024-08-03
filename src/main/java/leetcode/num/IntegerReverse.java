package leetcode.num;

/**
 * 7. 整数反转  中等难度
 * 拿到末尾数字 * 10
 *
 * 关键点
 * 1.通过取余 % 拿到末尾的数字
 * 2.通过取模 / 得到去掉末尾数字的后数
 * 3.如何判断溢出
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 */
public class IntegerReverse {

    public static void main(String[] args) {
        int x = -146;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            // 获取末尾的数字
            int t = x % 10;
            int newRes = res * 10 + t;
            // 如果溢出，返回0
            // 只要判断反转后的数对10取整，是否等于前一轮的tem就行
            if ((newRes - t) / 10 != res) {
                return 0;
            }
            res = newRes;
            // 去掉末尾的数字
            x = x / 10;
        }
        return res;
    }

    public static int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            // x % 10 取末尾的数字
            res = res * 10 + x % 10;
            // x = x / 10 去掉末尾的数字
            x = x / 10;
        }
        // 判断是否逸出
        return (int) res == res ? (int)res : 0;
    }
}
