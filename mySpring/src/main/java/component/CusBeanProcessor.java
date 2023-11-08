package component;

import anno.Component;
import processor.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Karl Rules!
 * 2023/11/5
 * now File Encoding is UTF-8
 */
@Component
public class CusBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("CusBeanProcessor.postProcessBeforeInitialization" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("CusBeanProcessor.postProcessAfterInitialization");
        System.out.println(beanName);
//        这里模拟的切面类 他这里省略了 等有空二刷手写spring了
//        这里返回一个代理对象
        if ("smartDog".equals(beanName)) {
          Object proxyInstance =   Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println(method.getName());
                    Object invoke = null;
                    if("getSum".equals(method.getName())){
                       SmartAnimalAspect.showBefore();
                        invoke = method.invoke(bean, args);
                        SmartAnimalAspect.showAfter();
                    }else {
                        invoke = method.invoke(bean, args);
                    }
                    return invoke;
                }
            });
//          如果要代理 返回aop
            return proxyInstance;
        }
//        如果不要代理 返回bean
        return bean;
    }
}
