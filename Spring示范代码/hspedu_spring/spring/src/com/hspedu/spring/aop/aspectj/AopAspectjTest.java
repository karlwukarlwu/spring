package com.hspedu.spring.aop.aspectj;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class AopAspectjTest {

    @Test
    public void smartDogTestByProxy() {

        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");
        //当开启注解以后 就不能直接获得bean了
//        开启了注解<aop:aspectj-autoproxy/>
//        float smartDog = ioc.getBean("smartDog", SmartDog.class).getSum(10, 2);
//        System.out.println(smartDog);

        //这里我们需要通过接口类型来获取到注入的SmartDog对象-就是代理对象
//        现在返回来的全部是实现了特定接口的代理对象 而不是原来的实现类（不是smartDog而是实现了SmartAnimalable接口的proxy ）
//          以后的获取全部要用接口类型来获得
//        原生的smartDog还是存在singletonObjects中 但是当我们调用的时候 其执行过程类似之前手写的proxy get返回代理对象，因此我们获取到的是代理对象
        //被代理的对象在加载的过程中放到了singletonObjects中

//        SmartAnimalable smartAnimalable =
//                ioc.getBean(SmartAnimalable.class);

//        这里也可以用名字来接受
        SmartAnimalable smartAnimalable =
                (SmartAnimalable)ioc.getBean("smartDog");

        smartAnimalable.getSum(10, 2);

        //System.out.println("smartAnimalable运行类型="
        //        + smartAnimalable.getClass());

        System.out.println("=============================");

        //smartAnimalable.getSub(100, 20);


    }

    @Test
    public void smartDogTestByProxy2() {

        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");

//        这里也可以用名字来接受 多个实现类的时候不能用接口类型来接受
        UsbInterface phone = (UsbInterface) ioc.getBean("phone");
        UsbInterface camera = (UsbInterface) ioc.getBean("camera");

        phone.work();

        System.out.println("==================");

        camera.work();

    }

    @Test
    public void test3() {
        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");
        //没实现接口就直接用类名 但是返回的还是代理类（cglib 代理 面向父类 有兴趣可以查查）
        Car car = ioc.getBean(Car.class);
        //com.hspedu.spring.aop.aspectj.Car$$EnhancerBySpringCGLIB$$8f69c3c9

        //说明: car对象仍然是代理对象
        System.out.println("car的运行类型=" + car.getClass());

        car.run();

    }

    @Test
    public void testDoAround() {
        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");


        SmartAnimalable smartAnimalable =
                ioc.getBean(SmartAnimalable.class);

        smartAnimalable.getSum(10, 2);
    }

}
