package work;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class Factory <T>{
    private T t;
    public Factory(T t){
        this.t = t;
    }
    public T getInstance(){
        ClassLoader classLoader = t.getClass().getClassLoader();
        Class<?>[] interfaces = t.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = null;
                try {
                    String name = method.getName();
                    //这里从AOP看，就是一个横切关注点-前置通知
                    System.out.println("日志-方法名"+name+"参数"+args[0]+"  "+args[1]);
//                          这里可以加一个判断然后强转一下类型
                    invoke = method.invoke(t, args);
                    //从AOP看, 也是一个横切关注点-返回通知
                    System.out.println("日志-方法名"+name+"结果"+invoke);
                }catch (Exception e){
                    //从AOP看, 也是一个横切关注点-异常通知
                    System.out.println("异常"+e.getMessage());
                }finally {
                    //从AOP的角度看, 也是一个横切关注点-最终通知
                    System.out.println("finally");
                }

                return invoke;
            }
        };
        T t1=(T) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return t1;
    }
}
//接下来的需求 如果我想把打印语句的需求改成调用一个方法 怎么办？