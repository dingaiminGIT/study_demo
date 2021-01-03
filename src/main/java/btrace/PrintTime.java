package btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class PrintTime {

    @TLS
    private static long startTime = 0;

    /**
     * 监控任意类
     * 监控visitWeb方法
     */
    @OnMethod(clazz = "/.+/",method = "/sleep")
    public static void startMethod() {
        startTime = timeMillis();
    }

    /**
     * 方法返回触发
     */
    @OnMethod(clazz = "/.+/", method = "/sleep", location = @Location(Kind.RETURN))
    public static void endMethod() {
        print(strcat(strcat(name(probeClass()), "."), probeMethod()));
        print(" [");
        print(strcat("Time taken :", str(timeMillis() - startTime)));
        print("]");
    }

}
