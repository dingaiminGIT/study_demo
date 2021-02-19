package leetcode.num;

/**
 * 阿拉伯数字转换为汉字
 * 将阿拉伯数字翻译成汉字。例如：
 * 101 读作：一百零一
 * 123 读作：一百二十三
 * 1234546 读作：一百二十三万四千五百四十六
 *
 * 解题思路：
 * 1）用一个字符串数组保存数字对应的汉字，另一个字符串数组保存单位
 * 2）用字符串拼接的方式保存翻译后的汉字
 *
 * 去哪儿网一面
 *
 * @author: dingaimin
 * @date: 2021/1/18 20:42
 */
public class Num2Chinese {

    public static void main(String[] args) {
        String nums = "1234546";
        System.out.println(toChinese(nums));
    }

    public static String toChinese(String nums) {
        // 数字对应的汉字
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        // 单位
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

        String ret = "";
        int n = nums.length();
        for (int i = 0; i < n; i++) {
            int num = nums.charAt(i) - '0';
            if (i != n -1 && num != 0) {
                // 数字长度大于一位
                ret += s1[num] + s2[n - 2 - i];
            } else {
                // 一位数字
                ret += s1[num];
            }
        }
        return ret;
    }
}
