import Injection.PhoneService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import proxy.Bus;
import proxy.Car;
import proxy.Vehicle;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class forTest {
    @Test
    public void test1(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");
        PhoneService phoneService = ioc.getBean("phoneService", PhoneService.class);
        phoneService.save();

        System.out.println("ok");
    }
}
