package work;

import bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Karl Rules!
 * 2023/10/31
 * now File Encoding is UTF-8
 */
public class wk01 {
    @Test
    public void test01() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
//         不写id 默认全类名#0，#1 。。。这样配置
        Monster bean = ioc.getBean("bean.Monster#0", Monster.class);
        System.out.println("bean = " + bean);
    }

}
