package btrace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HoldNetTask implements Runnable {

    public void visitWeb(String strUrl) {
        URL url = null;
        URLConnection connection = null;
        InputStream is = null;
        try {
            url = new URL(strUrl);
            connection = url.openConnection();
            is = connection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();
            String l = null;
            while ((l = buffer.readLine()) != null) {
                sb.append(l).append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            visitWeb("http://www.sina.com.cn");
        }
    }
}
