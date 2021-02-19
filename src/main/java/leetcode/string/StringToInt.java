package leetcode.string;

/**
 * 把字符串转化成整数
 *https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 *
 *需要考虑的四种字符
 * 1.首部空格：直接删除
 * 2.符号位：三种情况，即 "+"、"-"、"无符号";用一个变量保存符号位，返回前判断正负
 * 3.非数字字符：遇到首个非数字字符时，及时返回
 * 4.数字字符：
 *  ①字符转数字："此数字的ASCII码" - "0的ASCII码" 相减即可
 *  ②数字拼接：若从左到右遍历数字，设当前位字符为 c ，当前位数字为 x 数字结果为 res，拼接公式 如下
 *      x = ascii(c) - ascii(0)
 *      res = res * 10 + x
 * 5.数字越界处理
 *
 * @author: dingaimin
 * @date: 2021/1/15 13:28
 */
public class StringToInt {

    public static void main(String[] args) {
        String str = " -42";
        int num = str2Int2(str);
        System.out.println(num);

    }

    /**
     * 直接遍历字符串
     *
     * @param str
     * @return
     */
    public static int str2Int2(String str) {
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        int res = 0;
        int sign = 1;
        int i = 0;
        // 处理全是空串的情况
        while (str.charAt(i) == ' ') {
            if (++i == length) {
                return 0;
            }
        }
        if (str.charAt(i) == '-') {
            sign = -1;
        }
        // 下标过滤掉符号位
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            i++;
        }
        for (int j = i; j < length; j++) {
            // 非数字直接返回
            if (str.charAt(j) < '0' || str.charAt(j) > '9') {
                break;
            }
            int newRes = 10 * res + (str.charAt(j) - '0');
            if (newRes / 10 != res) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = newRes;
        }
        return sign * res;
    }

    /**
     * 通过 trim() 方法去掉 str 的空格，然后将剩下的字符串转成字符数组，这样空间复杂度高
     *
     * @param str
     * @return
     */
    public static int str2Int(String str) {
        char[] c = str.trim().toCharArray();
        if (c.length == 0) {
            return 0;
        }
        int res = 0;
        int length = c.length;
        // 下标
        int i = 1;
        // 符号位
        int sign = 1;
        if (c[0] == '-') {
            sign = -1;
        } else if (c[0] != '+') {
            i = 0;
        }

        for (int j = i; j < length; j++) {
            // 非数字返回
            if (c[j] < '0' || c[j] > '9') {
                break;
            }
            int newRes = res * 10 + (c[j] - '0');
            // 判断是否溢出：只要判断newRes数对10取整，是否等于上一个res就行
            if ((newRes / 10) != res) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = newRes;
        }
        return sign * res;
    }

    public static int convertToInt(String str) {
        int res = 0;
        for (int i = 0; i <= str.length() -1; i++) {
            String s = str.substring(i, i + 1);
            int i1 = Integer.parseInt(s);
            res = res * 10 + i1;
        }
        return res;
    }

}
