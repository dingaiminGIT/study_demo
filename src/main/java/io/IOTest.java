package io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class IOTest {

    public static void main(String[] args) throws IOException {
        String path = "D:\\58\\需求\\万花筒项目(DSP支持动态创意)\\dsp-insert-dsp最新.sql";
        File file = new File(path);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);

        String line = "";
        line = br.readLine();
        int id =261;
        while (null != line) {
            String value = id + ", NOW";
            String now = line.replace("NOW", value);
            String a = now.replace("NO2W", "NOW");
            System.out.println(a);
            id ++;
            line = br.readLine();
        }


    }

    @Test
    public void test() {
        Integer a = 1;
        boolean f = 2 == a;
        System.out.println(f);
    }
}
