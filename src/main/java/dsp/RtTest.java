package dsp;

import org.junit.jupiter.api.Test;

public class RtTest {

    public static void main(String[] args) {

       int bf_rt_hit_dmp_after = 4620;
       int all_rt_hit_dmp_after = 7540;
       int bf_rt_hit_no_21 = 7150;
       int bf_rt_miss_21 = 1250;
       int with_rt_flow = 5870;
       int without_rt_flow = 4160;

        System.out.println((bf_rt_hit_dmp_after)*1.0 / (all_rt_hit_dmp_after - bf_rt_hit_dmp_after));
        System.out.println(with_rt_flow * 1.0 / without_rt_flow);
        System.out.println("bf_rt_hit_dmp_after + bf_rt_miss_21 = with_rt_flow");
        System.out.println(bf_rt_hit_dmp_after + bf_rt_miss_21);
        System.out.println(with_rt_flow);
        System.out.println(all_rt_hit_dmp_after - without_rt_flow);

        System.out.println((bf_rt_hit_dmp_after + bf_rt_miss_21)*1.0 / (all_rt_hit_dmp_after - bf_rt_hit_dmp_after));
    }

    @Test
    public void test() {
        double with_rt_flow = 651.16;
        double without_rt_flow = 329.06;
        System.out.println(with_rt_flow / without_rt_flow);
    }

    @Test
    public void test2() {
        int bf_rt_hit_dmp_after = 4620;
        int all_rt_hit_dmp_after = 7540;
        int bf_rt_hit_no_21 = 7150;
        int bf_rt_miss_21 = 1250;
        int with_rt_flow = 5870;
        int without_rt_flow = 4160;
    }
}
