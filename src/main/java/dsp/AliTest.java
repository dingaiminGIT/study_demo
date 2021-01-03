package dsp;



import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;

public class AliTest {

    @Test
    public void assignGirlFriend() {
        GirlFriend 小花儿 = new GirlFriend("小花儿", 18);

    }

    class GirlFriend {
        String name;
        int age;

        public GirlFriend(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "GirlFriend{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        String json = "{\n" +
                "  \"id\": \"80ce30c53c16e6ede735f123ef6e32361bfc7b22\",\n" +
                "  \"device\": {\n" +
                "    \"ua\": \"Mozila Firefox\",\n" +
                "    \"ip\": \"123.125.250.165\",\n" +
                "    \"model\": \"iPhone\",\n" +
                "    \"os\": \"iOS\",\n" +
                "    \"osv\": \"9.5.2\",\n" +
                "    \"connectiontype\": 2,\n" +
                "    \"carrier\": \"ChinaMobile\",\n" +
                "    \"ifa\": \"60ce30c53c16e6ede735f\"\n" +
                "  },\n" +
                "  \"app\": {\n" +
                "    \"name\": \"UC Browser\",\n" +
                "    \"bundle\": \"com.uc.ucbrowser\"\n" +
                "  },\n" +
                "  \"imp\": [\n" +
                "    {\n" +
                "      \"id\": \"51\",\n" +
                "      \"ext\": {\n" +
                "        \"templateid\": [\n" +
                "          1,\n" +
                "          2,\n" +
                "          3\n" +
                "        ]\n" +
                "      },\n" +
                "      \"native\": {\n" +
                "        \"request\": {\n" +
                "          \"assets\": [\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"title\": {\n" +
                "                \"len\": 20\n" +
                "              }\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 2,\n" +
                "              \"img\": {\n" +
                "                \"type\": 3,\n" +
                "                \"w\": 200,\n" +
                "                \"wmin\": 100,\n" +
                "                \"h\": 400,\n" +
                "                \"hmin\": 200\n" +
                "              }\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String api = "http://127.0.0.1:8187/api/r?spm=u-2ctjauyke97pnc46zg";

        HttpURLConnection conn = (HttpURLConnection) new URL(api).openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Content-Type", "application/octet-stream;charset=utf-8");
        System.out.println("before");
        conn.connect();
        System.out.println("after");
        conn.getOutputStream().write(json.getBytes("UTF-8"));
        conn.getOutputStream().flush();
        conn.getOutputStream().close();
        if (conn.getResponseCode() == 200) {
            String resp = IOUtils.toString(conn.getInputStream(), "UTF-8");
            System.out.println(resp);
            conn.getInputStream().close();
        } else {
            System.out.println(conn.getResponseCode());
            System.out.println(IOUtils.toString(conn.getInputStream()));
            conn.getInputStream().close();
        }
    }
}
