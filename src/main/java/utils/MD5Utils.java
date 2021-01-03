package utils;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);

    public static String change2Md5Str(String str) {
        try {
            if (StringUtils.isBlank(str)) {
                return null;
            }
            str = str.toUpperCase();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("md5转化失败，str:{},errormsg:{}",str,e.getMessage());
            return "";
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("md5转化失败，str:{},errormsg:{}",str,e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        String secret = "dddd";
        System.out.println(MD5Utils.change2Md5Str(secret));
    }
}
