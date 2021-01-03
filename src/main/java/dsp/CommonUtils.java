package dsp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CommonUtils {

    public static void main(String[] args) {
        //String url = "pcp.58.com";
        String url = "https://pcp.58.com/n/d?fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_8589.s_86085058797277187.m_1173.t_25485.o_2847.x_u-2cbc34ryk3v43nkdq9g&request=siku53cbc24m6&ab=atspmn,limit_b,pass_o,ogbid,do_ctrmodel_b,LBIS_A,tt_req_applets,bid_o_a,PAY_TYPE_C,dspdl&ip=112.10.249.13&sign=a9263425399e635c170ca575b1def004&media=1173&slot=25485&creative=13608771&sid=86085058797277187&wb_dsp=&wb_dsp_creative=&local1=79&uid=48fa21a597f414acc55263cc08739d88&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=&wb_dsp_sid=&style=27&did=&order=2847&promotion=8589&info=&h_ip=__IP__&h_price=%%WIN_PRICE%%&h_cache_key=gdt_siku53cbc24m6";
        url = url.replace("https://pcp.58.com", "http://pcp.58dns.org");
        System.out.println(url);
        /*String s = safeUrlEncode(url);
        System.out.println(s);*/

    }

    public static String safeToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static String safeToString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return obj.toString();
    }

    public static String safeUrlEncode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return url;
    }
}
