package test;

import bean.BookStore;
import bean.Cat;
import bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * Karl Rules!
 * 2023/10/30
 * now File Encoding is UTF-8
 */
public class SpringTest {
//    级联赋值


    @Test
    public void CatName() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Cat cat01 = ioc.getBean("cat01", Cat.class);
        System.out.println("cat01 = " + cat01);
    }

    @Test
    public void getMonster() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        //这里需要对应beans.xml中的id
//        直接获取是object，但是运行类型是Monster 也可以通过直接指定告诉他
//        Monster monster = (Monster) context.getBean("monster01");
        Monster monster = ioc.getBean("monster01", Monster.class);
        System.out.println(monster);

        //6. 查看容器注入了哪些bean对象,会输出bean的id
        System.out.println("================================");
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName=" + beanDefinitionName);
        }
        System.out.println("================================");

        System.out.println("ok~~~");


    }

    //beans的路径问题
    @Test
    public void classPath() {
//        file=C:\Users\23584\Desktop\IDEAUtf-8\spring\spring01\out\production\spring01
//        实际运行的时候是这个路径，beans拿到的是out的beans
        File file = new File(this.getClass().getResource("/").getPath());
        //看到类的加载路径
        System.out.println("file=" + file);
    }
}
