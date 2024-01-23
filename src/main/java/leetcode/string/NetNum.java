package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 做一个简单的网站统计分析，规则如下
 * 输入若干字符串
 * "333 abc.yuanfudao.com"
 *
 * 各级域名的计数
 * 333 .com
 * 333 yuanfudao.com
 * 333 abc.yuanfudao.com
 *
 * input List<String>
 * 3 a.yuanfudao.com
 * 1 b.yuanfudao.com
 *
 * output List<String>
 * 3 a.yuanfudao.com
 * 1 b.yuanfudao.com
 * 4 com
 * 4 yuanfudao.com
 *
 * 猿辅导
 *
 * @author: dingaimin
 * @date: 2021/2/19 20:48
 */
public class NetNum {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("3 a.yuanfudao.com");
        input.add("1 b.yuanfudao.com");

        NetNum test = new NetNum();
        List<String> output = test.output(input);
        for(String str : output) {
            System.out.println(str);
        }
    }

    public List<String> output(List<String> input) {
        // 域名-> 访问次数的映射
        Map<String, Integer> map = new HashMap<>();

        // 遍历 input 解析计数和域名
        for(String str : input) {
            // 先根据空格拆分出技术和完整域名
            String[] numAndNet = str.split(" ");
            // 先获取数量
            Integer num = Integer.parseInt(numAndNet[0]);
            // 获取完整域名
            String net = numAndNet[1];

            // 拆分域名
            String[] nets =  net.split("\\.");
            int length = nets.length;
            //  拼接子域名，并放到域名List里
            List<String> netList = new ArrayList<>(length);
            StringBuilder strBuffer = new StringBuilder();
            for(int i = length - 1; i >= 0; i--) {
                // 为了保证域名的顺序的正确性，采用从头插入的方式
                if (i != length - 1) {
                    strBuffer.insert(0, nets[i] + ".");
                } else {
                    strBuffer.insert(0, nets[i]);
                }
                netList.add(strBuffer.toString());
            }
            helper(netList, num, map);
        }

        // 返回结果，遍历 map ，拼接返回结果
        List<String> res = new ArrayList<>();
        for(String key : map.keySet()) {
            String out = map.get(key) + " " + key;
            res.add(out);
        }
        return res;
    }

    public void helper(List<String> netList, Integer num, Map<String, Integer> map) {
        // 对域名进行计数的一个工具类
        for(String net : netList) {
            if(map.containsKey(net)) {
                // 存在进行累加
                Integer netNum = map.get(net);
                map.put(net, netNum + num);
            } else {
                // 不存在，要添加
                map.put(net, num);
            }
        }
    }
}
