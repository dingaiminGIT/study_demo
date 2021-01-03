package num;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void test() {
        BigDecimal bigDecimal = new BigDecimal(1.02);
        System.out.println(bigDecimal.toString());

        BigDecimal bigDecimal1 = new BigDecimal(0.01);
        BigDecimal subtract = bigDecimal.subtract(bigDecimal1);
        System.out.println(subtract);
    }

}
