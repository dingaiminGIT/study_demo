package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {

    private static Pattern CATE_TOP_N = Pattern.compile("cate_top_([0-9]+)");

    public static String getTopNFromAbTest(String query) {
        String topN = "";
        Matcher m = CATE_TOP_N.matcher(query);
        if (m.find()) {
            topN = m.group(1);
        }
        return topN;
    }


    public static void main(String[] args) {
        String str = "cate_top_10";
        System.out.println(getTopNFromAbTest(str));
    }

}
