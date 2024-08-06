package leetcode.bigdata;

import java.io.*;
import java.util.*;

/**
 * 场景：海量整型数据量，内存是放不下的，这些数据中少量重复的，怎么找到这些重复的值？
 * 思路：分治思想，按行读取这些海量数据，然后对一个数取模，把相同的数分到相同的文件中，然后再读取这些小文件，找到重复值
 */
public class FindDuplicates {

    private static final int NUM_BUCKETS = 100; // 分区数量
    private static final String BUCKET_PREFIX = "bucket_";

    public static void main(String[] args) throws IOException {
        // 假设 input.txt 是包含海量数据的文件
        String inputFile = "input.txt";
        List<String> bucketFiles = hashPartition(inputFile);

        Set<Integer> duplicates = new HashSet<>();
        for (String bucketFile : bucketFiles) {
            duplicates.addAll(findDuplicatesInBucket(bucketFile));
        }

        // 输出重复值
        for (int value : duplicates) {
            System.out.println(value);
        }
    }

    // 哈希分区
    private static List<String> hashPartition(String inputFile) throws IOException {
        List<BufferedWriter> writers = new ArrayList<>();
        for (int i = 0; i < NUM_BUCKETS; i++) {
            writers.add(new BufferedWriter(new FileWriter(BUCKET_PREFIX + i + ".txt")));
        }

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            int value = Integer.parseInt(line);
            int bucketIndex = value % NUM_BUCKETS;
            writers.get(bucketIndex).write(value + "\n");
        }
        reader.close();

        for (BufferedWriter writer : writers) {
            writer.close();
        }

        List<String> bucketFiles = new ArrayList<>();
        for (int i = 0; i < NUM_BUCKETS; i++) {
            bucketFiles.add(BUCKET_PREFIX + i + ".txt");
        }
        return bucketFiles;
    }

    // 查找分区中的重复值
    private static Set<Integer> findDuplicatesInBucket(String bucketFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(bucketFile));
        List<Integer> values = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            values.add(Integer.parseInt(line));
        }
        reader.close();

        Collections.sort(values);

        Set<Integer> duplicates = new HashSet<>();
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i).equals(values.get(i - 1))) {
                duplicates.add(values.get(i));
            }
        }
        return duplicates;
    }

}
