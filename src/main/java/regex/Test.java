package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static final Pattern MULTI_OUTPUT_PATTERN = Pattern.compile("\\s*return\\s*\\{(?:\\s*(['\"])\\w+\\1\\s*:[^}]+?,?\\s*)+}\\s*$");
    private static final Pattern MULTI_OUTPUT_PATTERN2 = Pattern.compile("\\s*return\\s*\\{(?:\\s*(['\"])\\w+\\1\\s*:[^}]+?,?\\s*)+}\\s*$");

    private static final Pattern RECOGNIZE_RETURN_KEYS_PATTERN = Pattern.compile("[{,]\\s*(['\"])(\\w+)\\1\\s*:");

    public static void main(String[] args) {
        /**
         * def required_extra_input(self):
         *        return "a","b"
         */
        String str2 = "def required_extra_input(self):\n" +
                "       return \"a\",\"b\"";

        // \s*return\s*\{(?:\s*(['"])\w+\1\s*:[^}]+?,?\s*)+}\s*$
        /**
         * def eval(self, inputs, results):
         *       return  {
         *             "a": 1,
         *             "b": 2
         *         }
         */
        String str = "def eval(self, inputs, results):\n" +
                "      return  {\n" +
                "            \"a\": 1,\n" +
                "            \"b\": 2\n" +
                "        }";


        Matcher matcher = MULTI_OUTPUT_PATTERN.matcher(str);
        if (!matcher.find()) {
        }
        String group = matcher.group();
        System.out.println(group);
        System.out.println("----------");
        Matcher matcher1 = RECOGNIZE_RETURN_KEYS_PATTERN.matcher(group);
        List<String> list = new ArrayList<>();
        while (matcher1.find()) {
            System.out.println(matcher1.group(1));
            System.out.println(matcher1.group(2));
            list.add(matcher1.group(2));
        }

        //list.stream().forEach(item -> System.out.println(item));

    }
}
