package num;

import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    public void test() {
        String dt = "2020-10-29";
        String startHour = "01";
        String endHour = "10";

        dt = dt.replaceAll("-", "");
        int start = Integer.parseInt(startHour);
        int end = Integer.parseInt(endHour);

        for (int i = start; i <= end; i++) {
            if (i < 10) {
                System.out.println(dt + "0" + i);
            } else {
                System.out.println(dt + i);
            }
        }
    }
}
