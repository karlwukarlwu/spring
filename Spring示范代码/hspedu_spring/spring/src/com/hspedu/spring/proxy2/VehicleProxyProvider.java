package com.hspedu.spring.proxy2;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 韩顺平
 * @version 1.0
 * VehicleProxyProvider 该类可以返回一个代理对象.
 */
public class VehicleProxyProvider {

    //定义一个属性
    //target_vehicle 表示真正要执行的对象
    //该对象实现了Vehicle接口
    private Vehicle target_vehicle;

    //构造器
    public VehicleProxyProvider(Vehicle target_vehicle) {
        this.target_vehicle = target_vehicle;
    }

    //编写一个方法，可以返回一个代理对象, 该代理对象可以通过反射机制调用到被代理对象的方法
    //老师解读
    //1. 这个方法非常重要， 理解有一定难度
    public Vehicle getProxy() {
//         public static Object new ProxyInstance(ClassLoader loader,
//                                          Class<?>[] interfaces,
//                                          InvocationHandler h)
//        需要三个参数 如何获得👇

        //得到类加载器
        //实际上这里也是多态  这里执行的是实际传进来的运行类型 也就是car和ship的getClass
        ClassLoader classLoader =
                target_vehicle.getClass().getClassLoader();
        System.out.println("classLoader=" + target_vehicle);

        //得到要代理的对象/被执行对象 的接口信息,底层是通过接口来完成调用
        Class<?>[] interfaces = target_vehicle.getClass().getInterfaces();


        //创建InvocationHandler 对象
        //因为 InvocationHandler 是接口，所以我们可以通过匿名对象的方式来创建该对象
        /**
         *
         * public interface InvocationHandler {
         *  public Object invoke(Object proxy, Method method, Object[] args)
         *         throws Throwable;
         * }
         * invoke 方法是将来执行我们的target_vehicle的方法时，会调用到
         *
         */

        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             * invoke 方法是将来执行我们的target_vehicle的方法时，会调用到
             * @param o 表示代理对象
             *              别管这个 需要调用方法的时候还是得使用属性target_vehicle进行反射
             * @param method 就是通过代理对象调用方法时的哪个方法 代理对象.目标方法()
             * @param args : 表示调用 代理对象.目标方法(xx) 传入的参数
             * @return 表示 代理对象.目标方法(xx) 执行后的结果.
             * @throws Throwable
             */
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {

                System.out.println("交通工具开始运行了....");

                //method.invoke(target_vehicle, args) 表示通过反射机制，调用target_vehicle的方法
                //method 是？: public abstract void com.hspedu.spring.proxy2.Vehicle.run()
                //target_vehicle 是? 传入的Ship、car对象
                //args 是? null 因为案例方法不要参数
                //这里通过反射+动态绑定机制，就会执行到被代理对象的方法
                //执行完毕就返回
                System.out.println(target_vehicle);
                System.out.println(o);
                Object result = method.invoke(target_vehicle, args);
                System.out.println("交通工具停止运行了....");
                return result;
            }
        };

        /*

          public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)

          老师解读
          1. Proxy.newProxyInstance() 可以返回一个代理对象
          2. ClassLoader loader: 类的加载器.
          3. Class<?>[] interfaces 就是将来要代理的对象的接口信息
                这里是一个数组 搭配多个接口用的
          4. InvocationHandler h 调用处理器/对象 有一个非常重要的方法invoke
         */
        Vehicle proxy =
                (Vehicle) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
//        (Vehicle) 实现的是一个强制类型转化 这种转换不改变对象的实际运行时类型，而是告诉编译器如何理解和处理这个对象
//        为什么可以这样转化 我们的代理对象实现了这个接口，根据参数的传入

//        Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
//        for (Class<?> anInterface : o.getClass().getInterfaces()) {
//            System.out.println("123= "+anInterface);
//        这里有vehicle接口 所以才能强行转化
////            123= interface com.hspedu.spring.proxy2.Vehicle
//        }

        System.out.println("123= " + proxy.getClass());
        return proxy;
    }
}
