package com.hspedu.spring.component;

import com.hspedu.spring.annotation.*;




/**
 * @author 韩顺平
 * @version 1.0
 * 老师说明：SmartAnimalAspect当做一个切面类来使用
 * ,后面老师再分析如何做的更加灵活
 */
@Aspect //我们的注解
@Component //这是实现了
public class SmartAnimalAspect {

    @Before(value = "execution com.hspedu.spring.aop.aspectj.SmartDog getSum")
    public static void showBeginLog() {

        System.out.println("前置通知..");
    }

    @AfterReturning(value = "execution com.hspedu.spring.aop.aspectj.SmartDog getSum")
    public static void showSuccessLog() {

        System.out.println("返回通知..");
    }
}
