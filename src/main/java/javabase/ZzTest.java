package javabase;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 正则表达式
 */
public class ZzTest {

    @Test
    public void test1() {
        String url = "http://10.9.194.42:5678/log/showlog?&biz_sid=29019911349084160&showid=910895379f93479d81af68bae7743efe&showtime={timestamp}&creativepackageid=5041231538273808&creativeid=210849&dyanid=301000001&platid=1&mediaid=496&slotid=741&pos={pos}&userip=10.223.139.234&abtest=-&deviceid=-&deviceidmd5=a7870fd3b5bac75b9c21ff2a4cd6f6aa&adid=-&dispcate1=-&dispcate2=-&dispcate3=-&displocal1=1&displocal2=0&displocal3=-&sid=e4940cbdd3984a8bbf0b0b84ff780351&tess={}";
        long time = System.currentTimeMillis();
        String regin = "\\{[^\\}]+\\}";
        url = url.replace("{timestamp}", String.valueOf(time)).replaceAll(regin , "-");
        System.out.println(url);
    }

    @Test
    public void test2() {
        String a = null;
        System.out.println((Object) a);

    }

    @Test
    public void test3() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.clear();
    }
}
