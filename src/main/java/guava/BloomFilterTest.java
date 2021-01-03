package guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import org.apache.commons.codec.Charsets;
import org.junit.jupiter.api.Test;
import utils.MD5Utils;

import java.math.RoundingMode;
import java.time.Instant;
import java.util.stream.IntStream;

public class BloomFilterTest {



    public static void main(String[] args) {

        int expectedInsertions = 1000_000_000;
        double fpp = 0.0001d;
        // 插入
        BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), expectedInsertions, fpp);
        /*long start = Instant.now().toEpochMilli();
        IntStream.rangeClosed(1, expectedInsertions).forEach(num -> filter.put(MD5Utils.change2Md5Str(String.valueOf(num))));
        long end = Instant.now().toEpochMilli();
        System.out.println("写入数据耗时：" + (end - start));

        // 查询
        long start1 = Instant.now().toEpochMilli();
        IntStream.rangeClosed(1, expectedInsertions).forEach((num) -> filter.mightContain(MD5Utils.change2Md5Str(String.valueOf(num))));
        long end2 = Instant.now().toEpochMilli();
        System.out.println("读取耗时：" + (end2 - start1));*/
    }

    @Test
    public void math() {
        long expectedNum = 200000000;
        double fpp = 0.001d;
        System.out.println("预估数据量 ：" + expectedNum + "\n误判率：" + fpp);
        // 根据预估数据量 n 和 误判率 fpp 计算bit数组
        // m = -n*ln(fpp)/(log2)^2
        long m = (long) (-expectedNum * Math.log(fpp) / (Math.log(2) * Math.log(2)));
        System.out.println("bit数组长度：" + m);

        // 根据预估数据量 n 和 bit 数组长度 m 计算哈希函数的个数 k = m/n * ln2
        System.out.println("哈希函数个数：" + Math.max(1, (int) Math.round((double)m / expectedNum * Math.log(2))));

        // BloomFilter 使用 long 型数组
        int longNum = Ints.checkedCast(LongMath.divide(m, 64, RoundingMode.CEILING));
        System.out.println("long数组大小:" + longNum);
        int size = longNum * 8 / 1024 / 1024;
        System.out.println("内存占用大小：" + size);


        // Guava BloomFilter 使用 MurmurHash3 计算哈希值
    }


}
