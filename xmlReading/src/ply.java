import org.junit.jupiter.api.Test;

/**
 * Karl Rules!
 * 2023/11/5
 * now File Encoding is UTF-8
 */
public class ply {
    @Test
    public void test01() {
        J1 j1 = new J1();
        H1.m1(j1);
        System.out.println(j1.i);
    }
}

class J1 {
    int i = 1;
}

class H1 {
    public static J1 m1(J1 o) {
        o.i = 2;
        return o;
    }
}
