package work;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

/**
 * Karl Rules!
 * 2023/11/2
 * now File Encoding is UTF-8
 */
public class T1 {
    @Test
    public void test01() {
        H1 h1 = new H2();
        h1.m1();
        ((H2) h1).m2();
//        ((H3) h1).m3();


    }
}

class H1 {
    public void m1() {
        System.out.println("h1 m1");
    }
}


class H2 extends H1 {
    public void m2() {
        System.out.println("h2 m2");
    }
}

class H3 extends H2 {
    public void m3() {
        System.out.println("h3 m3");
    }
}
