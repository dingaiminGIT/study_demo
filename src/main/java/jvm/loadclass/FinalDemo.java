package jvm.loadclass;

import java.util.UUID;

public class FinalDemo {
    public static final String str = UUID.randomUUID().toString();
    static {
        System.out.println("FinalDemo init");
    }
}
