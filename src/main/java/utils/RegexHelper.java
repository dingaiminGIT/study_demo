package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {

    private static Pattern catePattern = Pattern.compile("[?&]cate=([^&]*)");
    private static Pattern localPattern = Pattern.compile("[?&]city=([^&]*)");
    private static Pattern opPattern = Pattern.compile("[?&]op=([^&]*)");
    private static Pattern targetPattern = Pattern.compile("[?&]target=([^&]*)");


    public static String getTargetFromUrl(String query) {
        String target = "";
        Matcher m = targetPattern.matcher(query);
        if (m.find()) {
            target = m.group(1);
        }
        return target;
    }

    public static String getOpFromUrl(String query) {
        String op = "";
        Matcher m = opPattern.matcher(query);
        if (m.find()) {
            op = m.group(1);
        }
        return op;
    }

    public static String getCateListNameFromUrl(String query) {
        String cateListName = "";
        Matcher m = catePattern.matcher(query);
        if (m.find()) {
            cateListName = m.group(1);
        }
        return cateListName;
    }

    public static String getLocalListNameFromUrl(String query) {
        String localListName = "";
        Matcher m = localPattern.matcher(query);
        if (m.find()) {
            localListName = m.group(1);
        }
        return localListName;
    }

    public static String getLv2(String fullPath) {
        String r = "";
        if (fullPath != null) {
            String[] cateids = StringUtils.split(fullPath, ",");
            if (cateids.length >= 2) {
                r = cateids[1];
            }
        }
        return r;
    }

    public static String getLv1(String fullPath) {
        String r = "";
        if (fullPath != null) {
            String[] cateids = StringUtils.split(fullPath, ",");
            if (cateids.length >= 1) {
                r = cateids[0];
            }
        }
        return r;
    }
}
