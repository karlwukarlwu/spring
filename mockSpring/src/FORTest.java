import anno.CusSpringApplicationContext;
import anno.CusSpringConfig;
import org.junit.jupiter.api.Test;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class FORTest {
    @Test
    public void test01() {
        CusSpringApplicationContext ioc = new CusSpringApplicationContext(CusSpringConfig.class);
        System.out.println("ioc=" + ioc);

    }
}
