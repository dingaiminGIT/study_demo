package leetcode;

import java.util.Scanner;

/**
 * 统计字符串字符出现次数
 *
 * @author: dingaimin
 * @date: 2021/1/8 13:12
 */
public class StringCharTotal {

    public static void main(String[] args) {
        System.out.println("请输入字符串");
        String str = new Scanner(System.in).nextLine();

        if (str == null || "".equals(str)) {
            System.out.println("字符串不能为空");
            return;
        }

        // 方法1：用map
        /*HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Integer count = map.get(c);
            if (null == count) {
                map.put(c, 1);
            } else {
                map.put(c, count+1);
            }
        }
        System.out.println(map);*/

        // 方法2，利用String的方法 replaceAll
        while (str.length() > 0) {
            // 获取字符串第一个字符
            char c = str.charAt(0);
            // 将获取的字符转换成字符串
            String s = String.valueOf(c);
            // 将原来字符串 s 全部替换为空，得到一个新的字符串
            String strNew = str.replaceAll(s, "");
            // 计算得到字符个数
            int count = str.length() - strNew.length();
            str = strNew;
            System.out.println(s + " 出现了 " + count + " 次");
        }
    }

}
