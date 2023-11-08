package ContextReader;

import bean.Monster;
import org.junit.jupiter.api.Test;

/**
 * Karl Rules!
 * 2023/10/31
 * now File Encoding is UTF-8
 */
public class Test01 {
    @Test
    public void test01(){
        ApplicationContext applicationContext = new ApplicationContext("beans.xml");
//        System.out.println("applicationContext=" + applicationContext);
        Monster monster = (Monster) applicationContext.getBean("monster01");
        System.out.println("monster=" + monster);
    }
}
