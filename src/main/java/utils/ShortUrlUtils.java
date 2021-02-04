package utils;

/**
 * 短链生成器
 * https://hufangyun.com/2017/short-url/
 * https://github.com/Coselding/ShortURL/blob/master/src/cn/coselding/shorturl/service/impl/ShortUrlGenerator.java
 *
 * @author: dingaimin
 * @date: 2021/1/27 14:49
 */
public class ShortUrlUtils {

    public static void main(String[] args) {
        String url = "https://time.geekbang.org/column/intro/100070801?code=7cLiLPktDpm7FM4vqLH7Wn3F%2FbkUp55kPW81nQL0%2Fps%3D";
        String[] shortUrl = shortUrl(url, "dddd", 6);
        for (int i = 0; i < shortUrl.length; i++) {
            System.out.print(i);
        }
    }

    public static String[] shortUrl(String url, String key,int length) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z" };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = MD5Encoder.md5Encode(key + url);
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
            // long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < length; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl;
    }
}
