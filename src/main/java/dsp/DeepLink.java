package dsp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class DeepLink {

    private final static Object[] UTM_SOURCE = new Object[]{"58un", 1};

    public static void main(String[] args) {
        String deepLink = "wbmain://jump/job/list?pid=1461&params=%7B%22cateid%22%3A%2213913%22%2C%22title%22%3A%22%E4%BB%93%E5%82%A8%E7%89%A9%E6%B5%81%22%2C%22filterParams%22%3A%7B%22filtercate%22%3A%22zpwuliucangchu%22%7D%2C%22list_name%22%3A%22zpwuliucangchu%22%2C%22params%22%3A%7B%22cmcstitle%22%3A%22%E8%90%A5%E4%B8%9A%E5%91%98%22%2C%22pagefrom%22%3A%22daleiye%22%7D%2C%22meta_url%22%3A%22https%3A%2F%2Fapp.58.com%2Fapi%2Flist%22%7D";
        String spm = "a_1.b_2";
        String s = DeepLink.fillFlowIdentify(deepLink, spm);
        System.out.println(s);
    }

    public static String fillFlowIdentify(String deepLink, String flowIdentify) {
        try {
            int index1 = deepLink.indexOf("params=") + 7;
            int index2 = deepLink.indexOf("&", index1);

            String prefix;
            String params;
            String postfix;
            if (index2 == -1) {
                prefix = deepLink.substring(0, index1);
                params = deepLink.substring(index1);
                postfix = "";
            } else {
                prefix = deepLink.substring(0, index1);
                params = deepLink.substring(index1, index2);
                postfix = deepLink.substring(index2, deepLink.length());
            }
            String decodeParams = URLDecoder.decode(params, "UTF-8");
            JSONObject json = JSON.parseObject(decodeParams);
            json.put("spm", new Object[]{flowIdentify, 1});
            json.put("utm_source", UTM_SOURCE);
            String newParams = json.toString();
            String newDeepLink = prefix + URLEncoder.encode(newParams, "UTF-8") + postfix;
            return newDeepLink;
        } catch (Exception e) {
            return null;
        }
    }
}
