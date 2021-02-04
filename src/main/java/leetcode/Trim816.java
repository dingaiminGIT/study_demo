package leetcode;

/**
 * 给定一串数字字符串,迭代消除其中的连续的816
 * 【输入描述】
 * 比如: 818816168166  消除后是空
 * 818166
 * 816
 *
 * 比如: 81818166  消除后是81
 * 【输出描述】
 * 要求: 写出完整的代码实现,对足够极端的情况考虑尽可能优化的算法处理
 * 我用是递归实现的，面试官说可以用栈来实现
 *
 *
 * @author: dingaimin
 * @date: 2021/1/27 21:44
 */
public class Trim816 {

    public static String trim(String str) {
        int i = str.indexOf("8");
        while(i != -1) {
            // 判断 8 后面是不是16
            char c1 = str.charAt(i+1);
            char c2 = str.charAt(i+2);
            if(c1 == '1' && c2 == '6') {
                // 找到了816,要删掉这个816，然后用新字符串递归调用trim方法
                if(i == 0) {
                    // 说明816在字符串首
                    String preStr = str.substring(i+3, str.length());
                    trim(preStr);
                } else {
                    // 说明816前面还有字符，需要拼接前后两个部分
                    String preStr = str.substring(i-1, c2);
                    String postStr = str.substring(i+3, str.length());
                    String strNew = preStr + postStr;
                    trim(strNew);
                }

            } else {
                // 往后找816
                String strNew = str.substring(i+3, str.length());
                trim(strNew);
            }
        }
        // 没有816
        return str;

    }
}
