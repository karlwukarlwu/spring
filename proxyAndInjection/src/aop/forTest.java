package aop;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
public class forTest {
    @Test
    public void test01() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans02.xml");
        UsbInterface camera = ioc.getBean("camera", UsbInterface.class);
        Camera camera1 = ioc.getBean("camera", Camera.class);
//        UsbInterface camera2 = ioc.getBean(Camera.class);
        camera.work();
//        Camera camera1 = (Camera) camera;
        System.out.println(camera1.getA());
        UsbInterface phone = ioc.getBean("phone", UsbInterface.class);
        phone.work();

    }
}
