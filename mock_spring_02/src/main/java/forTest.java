import component.UserAction;
import component.UserDAO;
import component.UserService;
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
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        UserAction userAction =(UserAction) ioc.getBean("userAction");
        UserAction userAction1 =(UserAction) ioc.getBean("userAction");
        System.out.println(userAction);
        System.out.println(userAction1);
        UserDAO userDAO = (UserDAO) ioc.getBean("userDAO");
        System.out.println("userDao=" + userDAO);

        UserService userService = (UserService) ioc.getBean("userService");
        System.out.println("userService=" + userService);
        System.out.println(forTest.class.getResource("/"));


    }

}
