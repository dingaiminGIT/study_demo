package leetcode.num;

import java.math.BigInteger;

/**
 *  大数加法
 *  https://www.nowcoder.com/practice/11ae12e8c6fe48f883cad618c2e81475?tpId=117&&tqId=35277&&companyId=898&rp=1&ru=/company/home/code/898&qru=/ta/job-code-high/question-ranking
 *
 *  以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
 *
 * @author: dingaimin
 * @date: 2021/1/18 13:17
 */
public class BigNumAdd {

    public static void main(String[] args) {
        String s = "1999";
        String t = "288";
        String solve = solve3(s, t);
        System.out.println(solve);
    }

    /**
     * 直接用 BigInteger 来做
     *
     * @param s
     * @param t
     * @return
     */
    public static String solve (String s, String t) {
        BigInteger b1 = new BigInteger(s);
        BigInteger b2 = new BigInteger(t);
        return b1.add(b2).toString();
    }

    /**
     * 从尾部插入
     *
     * @param s
     * @param t
     * @return
     */
    public static String solve2 (String s, String t) {
        // 从尾部插入
        StringBuilder sb = new StringBuilder();
        int l1 = s.length() - 1;
        int l2 = t.length() - 1;
        // 表示进位
        int carry = 0;
        while (l1 >= 0 || l2 >= 0 || carry != 0) {
            int x = l1 < 0 ? 0 : s.charAt(l1--) - '0';
            int y = l2 < 0 ? 0 : t.charAt(l2--) - '0';
            int sum = x + y + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    /**
     * 从头部插入
     *
     * @param s
     * @param t
     * @return
     */
    public static String solve3 (String s, String t) {
        // 从头部插入
        StringBuilder sb = new StringBuilder();
        int l1 = s.length() - 1;
        int l2 = t.length() - 1;
        // 表示进位
        int carry = 0;
        while (l1 >= 0 || l2 >= 0 || carry != 0) {
            int x = l1 < 0 ? 0 : s.charAt(l1--) - '0';
            int y = l2 < 0 ? 0 : t.charAt(l2--) - '0';
            int sum = x + y + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
        }
        return sb.toString();
    }
}
