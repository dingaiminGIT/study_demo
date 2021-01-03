package dsp;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by 58 on 2017/7/17.
 */
public class SignUtils {

    /**
     * 签名生成算法
     *
     * @param params 请求参数集，所有参数必须已转换为字符串类型
     * @param secret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(Map<String, Object> params, String secret) {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue() + "");
        }
        basestring.append(secret);

        // 使用MD5对待签名串求签
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        if (bytes != null) {
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(bytes[i] & 0xFF);
                if (hex.length() == 1) {
                    sign.append("0");
                }
                sign.append(hex);
            }
        }
        return sign.toString();
    }


    public static String getSignature(String paramsStr, String secret) {
        if (StringUtils.isEmpty(paramsStr)) {
            return "";
        }
        List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(paramsStr, Charset.forName("UTF-8"));
        Map<String, Object> params = Maps.newHashMap();
        for (NameValuePair nameValuePair : nameValuePairs) {
            if (!nameValuePair.toString().contains("h_")) {
                params.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        return SignUtils.getSignature(params, secret);
    }

    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("adid", "1539");
        paramMap.put("sid", "114173230540324864");
        paramMap.put("rid", "kgigokAJWYPvycOnCfofwkTw");
        paramMap.put("tid", "1");
        paramMap.put("cateId", "13901");
        paramMap.put("localId", "1163");
        paramMap.put("fid", "u-Lx4Y3m-2J9tMcKG.mfc_toutiao.a_15396.s_114173230540324864.m_1.t_1.o_16.x_u-Lx4Y3m-2J9tMcKG");
        paramMap.put("sysType", "2");
        //paramMap.put("dspIds", "17,28");
        paramMap.put("ip", "59.108.138.234");
        //String phoneUrl = "https://jumpluna.58.com/assgin?cateid=" + 13901 + "&localid=" + 1163 + "&fid=" + "u-Lx4Y3m-2J9tMcKG.mfc_toutiao.a_15396.s_114173230540324864.m_1.t_1.o_16.x_u-Lx4Y3m-2J9tMcKG" + "&adid=" + 1539 + "&sid=" + "114173230540324864" + "&rid=" + "kgigokAJWYPvycOnCfofwkTw" + "&tid=" + 1 + "&sysType=" + 2 + "&ip=" + "59.108.138.234" + "&dspIds=" + "17,28" + "&sign=" + SignUtils.getSignature(paramMap, "u-Lx4Y3m-2J9tMcKG");
        String phoneUrl = "https://jumpluna.58.com/assgin?cateid=" + 13901 + "&localid=" + 1163 + "&fid=" + "u-Lx4Y3m-2J9tMcKG.mfc_toutiao.a_15396.s_114173230540324864.m_1.t_1.o_16.x_u-Lx4Y3m-2J9tMcKG" + "&adid=" + 1539 + "&sid=" + "114173230540324864" + "&rid=" + "kgigokAJWYPvycOnCfofwkTw" + "&tid=" + 1 + "&sysType=" + 2 + "&ip=" + "59.108.138.234" + "&dspIds=" + "17,28" + "&sign=" + SignUtils.getSignature(paramMap, "u-Lx4Y3m-2J9tMcKG");
        System.out.println(phoneUrl);
    }
}
