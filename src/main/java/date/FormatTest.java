package date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FormatTest {

    public static void main(String[] args) {
        String locationFormat = "http://webhdfs.58corp.com/webhdfs/v1/home/hdp_lbg_ectech/warehouse/hdp_lbg_ectech_lmdb/t_lm_dsp_basic_funnel/dt=%s/000000_0_0.lzo?op=OPENDECOMPRESS&user.name=hdp_lbg_ectech";
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dt = format.format(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // 昨天
        String location = String.format(locationFormat, dt);
        System.out.println(location);

    }
}
