package proxy;

import org.junit.jupiter.api.Test;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class forTest {
    @Test
    public void test1(){
        Vehicle v = new Car();
        v.run();
        Vehicle v2 = new Bus();
        v2.run();
    }
    @Test
    public void test2() {
        Vehicle v = new Bus();
        ProxyFactory<Vehicle> vehicleProxyFactory = new ProxyFactory<>(v);
        vehicleProxyFactory.getProxyInstance().run();


    }
}
