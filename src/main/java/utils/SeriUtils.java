package utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class SeriUtils {

    private static final ObjectMapper mapper = new ObjectMapper();


    public static <T> T readValue(String src, TypeReference<T> typeReference) throws Exception {
        T result = null;
        try {
            result = mapper.readValue(src, typeReference);
        } catch (Exception e) {
            throw new Exception("反序列化错误 src = ", e);
        }
        return result;
    }

    public static <T> T readValue(String src) throws Exception {
        T result = null;
        try {
            result = mapper.readValue(src, new TypeReference<T>() {
            });
        } catch (Exception e) {
            throw new Exception("反序列化错误 src = ", e);
        }
        return result;
    }

    public static <T> T readValue(String src, Class<T> klass) throws Exception {
        T result = null;
        try {
            result = mapper.readValue(src, klass);
        } catch (Exception e) {
            throw new Exception("反序列化错误 src = ", e);
        }
        return result;
    }

    public static String writeObject(Object obj) throws Exception {
        String result = "";
        try {
            result = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new Exception("序列化错误 class = " + obj.getClass().getName(), e);
        }
        return result;
    }

    /**
     * 调用方需保证传参不会抛出异常
     *
     * @param obj
     * @return
     */
    public static String safeWriteObject(Object obj) {
        String result = "";
        try {
            result = mapper.writeValueAsString(obj);
        } catch (Exception e) {
        }
        return result;
    }

}