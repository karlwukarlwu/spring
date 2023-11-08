package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class ProxyFactory<T> {
    //定义一个属性
    //target 表示真正要执行的对象
    //实现了T接口的对象
    private T target;

    public ProxyFactory(T target) {
        //这里拿到的是一个实现了T接口的对象
        this.target = target;
    }

    public T getProxyInstance() {
        //多态 虽然是T接口 但是实际上是一个实现了T接口的对象，执行的都是子类的方法
        ClassLoader classLoader = target.getClass().getClassLoader();
//        拿到实现了T接口的对象的所有接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //对需要加工的方法进行加工
        InvocationHandler invocationHandler = new InvocationHandler() {
            //第一个参数是代理对象
            //  不要管 反射用传进来的target
            /**
             * invoke 方法是将来执行我们的target_vehicle的方法时，会调用到
             * @param proxy 表示代理对象
             *              别管这个 需要调用方法的时候还是得使用属性target_vehicle进行反射
             * @param method 就是通过代理对象调用方法时的哪个方法 代理对象.目标方法()
             * @param args : 表示调用 代理对象.目标方法(xx) 传入的参数
             * @return 表示 代理对象.目标方法(xx) 执行后的结果.
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                //method.invoke(target, args) 表示通过反射机制，调用target的方法
//                //method 是？: 接口中有的方法 但是实现的是子类的方法 ublic abstract void com.hspedu.spring.proxy2.Vehicle.run()
//                            如果仅仅是子类的方法？ 不会被看到 因为向上转型了 只会执行接口中有的方法
//                //target 是? 传入的实现接口的子类对象
//                //args 是? null 因为案例方法不要参数
//                //这里通过反射+动态绑定机制，就会执行到被代理对象的方法
//                //执行完毕就返回
                System.out.println("start");
                Object invoke = method.invoke(target, args);
                System.out.println("end");
                return invoke;

            }
        };
        //我们最终需要实现这个方法来进行返回
        //我们的需求是得到这个方法的三个参数
        //因为返回的Proxy instance实现了interfaces的所有接口
        Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
//        因此这里可以强制转型
        return (T) o;
    }
}
