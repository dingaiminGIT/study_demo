package decode;

import java.io.UnsupportedEncodingException;

public class WifiPriceDecode {

    private static String str = "sid=11a75b962bc12e22&src_id=2381061&ad_id=&creative_id=&cpm=250&ip=61.129.119.185&ts=1464768697&ck=29bd2e6dd81dd66759cdd7a58d308f59c9539b39";
    private static String encode_str = "Jw94OIrQFIJmF84wX5E8XIE9X8u5JVE8Nw94OIuUB_r3g8r5FzKDdzkLE5gGYzcadNY9Nw94OlY8J1aLX8x3E593OIFQt8rGBljQXI4WXI-mEAKUOIrag8kVg8-wBIJ5FwCLX89vY_E9g5K4B_c4Y_FwgUxRFwK4gwrmB1kUX_T5gI98BIxUBzuUBk";

    private static String token = "test_test_test_test_test_test___";
    public static void encode() throws UnsupportedEncodingException {
        String encode1 = new String(com.bj58.vertxTest.Base64New.encodeBase64(str) , "gbk");
        System.out.println(encode1);
        System.out.println(encode1.equals(encode_str));
        System.out.println(new String(com.bj58.vertxTest.Base64New.decodeBase64(encode1.getBytes())));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        WifiPriceDecode.encode();
    }
}
