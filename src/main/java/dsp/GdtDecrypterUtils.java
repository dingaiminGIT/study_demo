package dsp;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class GdtDecrypterUtils {
    private static final byte[] secretKey = new byte[16];
    static{
        String tokenStr = "cc7e067c1d4b6f76c0b015ffea42c8c7";
        byte[] tokenByte = new byte[0];
        try {
            tokenByte = tokenStr.getBytes("UTF-8");
            System.arraycopy(tokenByte , 0 , secretKey , 0, 16);
        } catch (UnsupportedEncodingException e) {
        }
    }

    public static void main(String[] args) throws Exception {
        //String path = "D:\\58\\需求\\DSP对接广点通CPC出价\\price.txt";
        //String path = "D:\\58\\需求\\DSP对接广点通CPC出价\\validPrice.txt";
        String path = "D:\\58\\需求\\DSP对接广点通CPC出价\\sumPrice.txt";
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Double sum = 0d;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Long price = GdtDecrypterUtils.getPrice(line);
            Double price1 = new Double(price);
            sum = sum + price1;
        }

        System.out.println(sum);

    }

    /**
     * 解密
     * @param content 待解密内容
     * @param secretKeyBytes 解密密钥
     * @return
     */
    private static String decrypt(String content, byte[] secretKeyBytes) throws Exception {
        SecretKey key=new SecretKeySpec(secretKeyBytes, "AES");
        Cipher cipher=Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte [] byte_content= Base64.getUrlDecoder().decode(content);
        /*
         * 解密
         */
        byte [] byte_decode=cipher.doFinal(byte_content);
        String AES_decode=new String(byte_decode,"utf-8");
        return AES_decode;

    }

    public static long getPrice(String content) throws Exception {
        return Long.parseLong(decrypt(content , secretKey).trim());
    }

}
