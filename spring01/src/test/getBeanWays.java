package test;

import bean.BookStore;
import bean.Cat;
import bean.Emp;
import bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MemberServiceImpl;

/**
 * Karl Rules!
 * 2023/10/31
 * now File Encoding is UTF-8
 */
public class getBeanWays {
//    级联赋值
    @Test
    public void test02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        //老师解读，直接传入class对象/类型
        Emp bean = ioc.getBean(Emp.class);
        System.out.println("bean=" + bean);
//        bean=Emp{name='张三', dept=Dept{name='java'}}
    }

    //    util空间赋值
    @Test
    public void test01() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        //老师解读，直接传入class对象/类型
        BookStore bean = ioc.getBean(BookStore.class);
        bean.getBookList().forEach(System.out::println);
    }

    @Test void setBeanByRef2(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");
        //老师解读，直接传入class对象/类型
        MemberServiceImpl iocBean = ioc.getBean("memberService2", MemberServiceImpl.class);
        iocBean.add();
    }
    //通过bean的类型来获取bean
    @Test
    public void setBeanByRef(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");
        //老师解读，直接传入class对象/类型
        MemberServiceImpl iocBean = ioc.getBean("memberService", MemberServiceImpl.class);
        iocBean.add();

    }

    @Test
    public void getBeanByClass() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        //老师解读，直接传入class对象/类型
//        要求bean的配置中只有一个该类型的bean 配置两个monster的话就会报错
//        Monster bean = ioc.getBean(Monster.class);
        Cat bean2 = ioc.getBean(Cat.class);
        System.out.println("bean=" + bean2);
    }
}
