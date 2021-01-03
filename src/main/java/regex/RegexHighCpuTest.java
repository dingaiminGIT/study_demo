package regex;

import org.junit.jupiter.api.Test;

public class RegexHighCpuTest {

    /**
     *
     * 发生回溯，CPU占用高
     * 问题：1回溯问题  2url中有_ % 但正则表达式中没有
     *
     */
    @Test
    public void test1() {
        // 第一部分匹配http或https协议
        // 第二部分匹配www.字符
        // 第三部分匹配许多字符
        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        String bugUrl = "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        if (bugUrl.matches(badRegex)) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }
    }

    /**
     * 针对上面的问题，在正则表达式中加上 _ %
     */
    @Test
    public void test2() {
        // 第一部分匹配http或https协议
        // 第二部分匹配www.字符
        // 第三部分匹配许多字符
        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~_%\\\\/])+$";
        String bugUrl = "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        if (bugUrl.matches(badRegex)) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }
    }
}
