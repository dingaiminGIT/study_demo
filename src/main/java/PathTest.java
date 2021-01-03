import org.junit.jupiter.api.Test;

public class PathTest {

    @Test
    public void test() {
        String path = PathTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(path);
    }
}
